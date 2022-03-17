/* eslint-disable no-console */
import React, { useCallback, useEffect, useRef, useState } from 'react';
import { Alert, Button, Image, Radio, Space, Spin, Tag, notification, Divider, Steps, Progress } from 'antd';
import Title from 'antd/lib/typography/Title';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { toast } from 'react-toastify';
import { getEntity, getTest } from '../preguntas/preguntas.reducer';
import { getEntities as getTematicas } from '../tematicas/tematicas.reducer';
import { ArrowLeftOutlined, ArrowRightOutlined, StopOutlined, SyncOutlined } from '@ant-design/icons';
import './transito-test.scss';
import { IPreguntas } from 'app/shared/model/preguntas.model';
import moment from 'moment';

const TransitoTest = () => {
  const dispatch = useAppDispatch();
  const tematicas = useAppSelector(state => state.tematicas.entities);
  const [preguntas, setPreguntas] = useState([]);
  const pregunta = useAppSelector(state => state.preguntas.entity);
  const loading = useAppSelector(state => state.preguntas.loading);
  const [index, setIndex] = useState<number>();
  const [id, setId] = useState<number>();
  const [showSol, setShowSol] = useState(false);
  const [qList, setQList] = useState<{ answer: number; correcta: number }[]>();

  const divRef: React.Ref<HTMLDivElement> = useRef();

  const [startTime, setStartTime] = useState(Date.now());
  const [time, setTime] = useState(0);
  const [time2, setTime2] = useState(null);
  const interval = useRef<number>();

  const restart = () => {
    dispatch(getTematicas({ page: 0, size: 50, sort: 'id,asc' }));
    dispatch(getTest()).then((data: any) => {
      const p: IPreguntas[] = data.payload.data;
      setPreguntas(p);
      setQList([...p.map(pp => ({ answer: 0, correcta: pp.correcta }))]);
      setTime2(null);
      setShowSol(false);
      setStartTime(Date.now());
    });
  };

  useEffect(() => {
    restart();
  }, []);

  useEffect(() => {
    divRef.current?.focus();
  }, [divRef.current]);

  useEffect(() => {
    if (preguntas.length > 0) {
      setIndex(0);
      setId(preguntas[0].nro);
      dispatch(getEntity(preguntas[0].nro));
    }
  }, [preguntas]);

  useEffect(() => {
    if (index >= 0) {
      setId(preguntas[index].nro);
    }
  }, [index]);

  useEffect(() => {
    if (id) {
      dispatch(getEntity(id));
    }
  }, [id]);

  const next = () => {
    console.log(index);
    index < preguntas.length - 1 && setIndex(index + 1);
  };

  const prev = () => {
    console.log(index);
    index > 0 && setIndex(index - 1);
  };

  const setAnswer = ind => e => {
    const ans = e.target.value;
    if (!showSol && ans > 0) {
      const q = [...qList];
      q[ind] = {
        ...q[ind],
        answer: ans,
      };
      setQList(q);
      next();
      divRef.current.focus();
    }
  };

  const openNotificationWithIcon = type => {
    notification[type]({
      message: `Artículo ${pregunta?.artinc?.artinc}`,
      description: pregunta?.artinc?.descrip,
    });
  };

  const handleStep = c => {
    setIndex(c);
  };

  const endTest = () => {
    setShowSol(true);
    setTime2(time);
    const t = qList.reduce((acc, curr) => (acc += curr.answer === curr.correcta ? 5 : 0), 0);
    if (t >= 70) {
      notification['success']({
        message: `Success`,
        description: `Congratulation you pass, ${t} point in the test`,
      });
    } else {
      notification['error']({
        message: `Fail`,
        description: `Sorry you failed, ${t} point in the test`,
      });
    }
  };

  const handleKeyPress = useCallback(
    event => {
      if (event.key === 'ArrowRight') next();
      else if (event.key === 'ArrowLeft') prev();
    },
    [index]
  );

  useEffect(() => {
    document.addEventListener('keydown', handleKeyPress);
    return () => {
      document.removeEventListener('keydown', handleKeyPress);
    };
  }, [handleKeyPress]);

  useEffect(() => {
    interval.current = setInterval(() => {
      !showSol && setTime(Date.now());
    }, 1000);
    return () => {
      clearInterval(interval.current);
    };
  }, [showSol]);

  useEffect(() => {
    if (!showSol && time - startTime > 1000 * 60 * 45) {
      endTest();
    }
  }, [time]);

  const humanizeDuration = input => {
    if (input <= 0) return '00:00';
    const q = [];
    const m = Math.floor(input / (1000 * 60));
    let ms = `${m}`;
    if (m < 10) ms = `0${m}`;
    q.push(ms);
    const s = Math.floor((input / 1000) % 60);
    let ss = `${s}`;
    if (s < 10) ss = `0${s}`;
    q.push(ss);
    return q.join(':');
  };

  const format = (percent, successPercent) => {
    if (successPercent) return 'Time over!';
    return humanizeDuration(time - startTime);
  };

  return (
    <Space direction="vertical" className="transito-test">
      {!pregunta.nro ? (
        <Spin>
          <Alert message="Loading..." />
        </Spin>
      ) : (
        <>
          <Progress percent={(time - startTime) / (45 * 60 * 10)} format={format} />
          <Steps current={index} onChange={handleStep} className="steps" size="small">
            {qList &&
              tematicas.map((t, i) => (
                <Steps.Step
                  key={t.id}
                  className={
                    showSol && qList[i]?.answer === qList[i]?.correcta
                      ? 'colored-success'
                      : showSol && qList[i]?.answer > 0
                      ? 'colored-error'
                      : ''
                  }
                  status={i === index ? 'process' : qList[i]?.answer > 0 ? 'finish' : 'wait'}
                />
              ))}
          </Steps>
          <Space align="center">
            {!!pregunta?.artinc?.artinc && <Tag color="magenta">Artículo (inciso): {pregunta.artinc.artinc}</Tag>}
            {!!pregunta?.artinc?.pelig && <Tag color="error">Peligrosidad: {pregunta.artinc.pelig}</Tag>}
            {!!pregunta?.artinc?.descrip && (
              <Tag color="default" onClick={() => openNotificationWithIcon('info')}>
                Descripción
              </Tag>
            )}
            {!!pregunta?.tematica?.descrip && <Tag color="cyan">Temática: {pregunta.tematica.descrip}</Tag>}
          </Space>
          <Divider />
          {qList && (
            <Space align="start" className="space space-media">
              <Space direction="vertical">
                <Title level={5}>{pregunta.texto}</Title>
                <Radio.Group name={`test-${index}`} className="radio-group" value={qList[index]?.answer} onChange={setAnswer(index)}>
                  <Radio
                    value={1}
                    className={`${
                      showSol && pregunta.correcta === 1 ? 'radio-success' : showSol && qList[index]?.answer === 1 ? 'radio-error' : ''
                    }`}
                  >
                    {pregunta.resp1}
                  </Radio>
                  <Radio
                    value={2}
                    className={`${
                      showSol && pregunta.correcta === 2 ? 'radio-success' : showSol && qList[index]?.answer === 2 ? 'radio-error' : ''
                    }`}
                  >
                    {pregunta.resp2}
                  </Radio>
                  <Radio
                    value={3}
                    className={`${
                      showSol && pregunta.correcta === 3 ? 'radio-success' : showSol && qList[index]?.answer === 3 ? 'radio-error' : ''
                    }`}
                  >
                    {pregunta.resp3}
                  </Radio>
                </Radio.Group>
              </Space>
              {pregunta.foto?.foto && <Image width={200} className="image" src={`data:image/png;base64,${pregunta.foto.foto}`} />}
            </Space>
          )}
          <Divider />
          <Space>
            <Button onClick={prev} icon={<ArrowLeftOutlined />}>
              Prev
            </Button>
            <Button onClick={restart} icon={<SyncOutlined />} type="dashed">
              Refresh
            </Button>
            <Button onClick={endTest} icon={<StopOutlined />} type="primary">
              End test
            </Button>
            <Button ref={divRef} onClick={next} icon={<ArrowRightOutlined />}>
              Next
            </Button>
          </Space>
        </>
      )}
    </Space>
  );
};

export default TransitoTest;
