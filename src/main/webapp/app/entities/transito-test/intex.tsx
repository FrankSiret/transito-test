/* eslint-disable no-console */
import { Alert, Button, Image, Radio, Space, Spin, Tag, notification, Divider, Steps } from 'antd';
import Title from 'antd/lib/typography/Title';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import React, { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import { getEntity, getTest } from '../preguntas/preguntas.reducer';
import { getEntities as getTematicas } from '../tematicas/tematicas.reducer';
import { ArrowLeftOutlined, ArrowRightOutlined } from '@ant-design/icons';
import './transito-test.scss';
import { IPreguntas } from 'app/shared/model/preguntas.model';
import { Shortcuts } from 'react-shortcuts';

const TransitoTest = () => {
  const dispatch = useAppDispatch();
  const tematicas = useAppSelector(state => state.tematicas.entities);
  const [preguntas, setPreguntas] = useState([]);
  const pregunta = useAppSelector(state => state.preguntas.entity);
  const loading = useAppSelector(state => state.preguntas.loading);
  const [index, setIndex] = useState<number>();
  const [id, setId] = useState<number>();
  const [answer, setAnswer] = useState<number>(null);
  const [showSol, setShowSol] = useState(false);
  const [qList, setQList] = useState<boolean[]>(new Array(20).fill(false));

  useEffect(() => {
    dispatch(getTematicas({ page: 0, size: 50, sort: 'id,asc' }));
    dispatch(getTest()).then((data: any) => {
      const p: IPreguntas[] = data.payload.data;
      setPreguntas(p);
    });
  }, []);

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
    setShowSol(false);
    setAnswer(null);
    index < preguntas.length - 1 && setIndex(i => i + 1);
  };

  const prev = () => {
    setShowSol(false);
    setAnswer(null);
    index > 0 && setIndex(i => i - 1);
  };

  const show = () => {
    setShowSol(true);
  };

  const check = () => {
    if (answer === pregunta.correcta) toast.success('Corrent answer');
    else toast.error('Invalid answer');

    show();
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

  const handleShortcuts = (action, event) => {
    switch (action) {
      case 'MOVE_LEFT':
        next();
        break;
      case 'MOVE_RIGHT':
        prev();
        break;
      case 'ENTER':
        check();
        break;
      default:
        break;
    }
  };

  return (
    <Shortcuts name="TEST-SHORTCUTS" handler={handleShortcuts}>
      <Space direction="vertical" className="transito-test">
        {loading || !pregunta.nro ? (
          <Spin>
            <Alert message="Loading..." />
          </Spin>
        ) : (
          <>
            <Steps current={index} onChange={handleStep} className="steps" size="small">
              {tematicas.map((t, i) => (
                <Steps.Step key={t.id} status={i === index ? 'process' : qList[i] ? 'finish' : 'wait'} />
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
            <Space align="start" className="space space-media">
              <Space direction="vertical">
                <Title level={5}>{pregunta.texto}</Title>
                <Radio.Group name="test" className="radio-group">
                  <Radio
                    value={1}
                    onClick={() => setAnswer(1)}
                    className={`${
                      showSol && pregunta.correcta === 1 ? 'radio-success' : showSol && answer !== null && answer === 1 ? 'radio-error' : ''
                    }`}
                  >
                    {pregunta.resp1}
                  </Radio>
                  <Radio
                    value={2}
                    onClick={() => setAnswer(2)}
                    className={`${
                      showSol && pregunta.correcta === 2 ? 'radio-success' : showSol && answer !== null && answer === 2 ? 'radio-error' : ''
                    }`}
                  >
                    {pregunta.resp2}
                  </Radio>
                  <Radio
                    value={3}
                    onClick={() => setAnswer(3)}
                    className={`${
                      showSol && pregunta.correcta === 3 ? 'radio-success' : showSol && answer !== null && answer === 3 ? 'radio-error' : ''
                    }`}
                  >
                    {pregunta.resp3}
                  </Radio>
                </Radio.Group>
              </Space>
              {pregunta.foto?.foto && <Image width={200} className="image" src={`data:image/png;base64,${pregunta.foto.foto}`} />}
            </Space>
            <Divider />
            <Space>
              <Button onClick={prev} icon={<ArrowLeftOutlined />}>
                Prev
              </Button>
              <Button onClick={show} type="dashed">
                Show solution
              </Button>
              <Button onClick={check} type="primary">
                Check
              </Button>
              <Button onClick={next} icon={<ArrowRightOutlined />}>
                Next
              </Button>
            </Space>
          </>
        )}
      </Space>
    </Shortcuts>
  );
};

export default TransitoTest;
