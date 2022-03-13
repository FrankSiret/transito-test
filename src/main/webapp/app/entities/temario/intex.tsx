/* eslint-disable no-console */
import { Alert, Button, Image, Radio, Space, Spin, Tag, notification, Divider, Select } from 'antd';
import Text from 'antd/lib/typography/Text';
import Title from 'antd/lib/typography/Title';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import React, { useEffect, useState } from 'react';
import { byteSize, openFile } from 'react-jhipster';
import { toast } from 'react-toastify';
import { getEntities, getEntity } from '../preguntas/preguntas.reducer';
import { getEntities as getTematicas } from '../tematicas/tematicas.reducer';
import { ArrowLeftOutlined, ArrowRightOutlined } from '@ant-design/icons';
import './temario.scss';
import { IPreguntas } from 'app/shared/model/preguntas.model';
import { shuffle } from 'lodash';

const Temario = () => {
  const dispatch = useAppDispatch();
  const tematicas = useAppSelector(state => state.tematicas.entities);
  const [tematica, setTematica] = useState('');
  const [preguntas, setPreguntas] = useState([]);
  const pregunta = useAppSelector(state => state.preguntas.entity);
  const loading = useAppSelector(state => state.preguntas.loading);
  const [index, setIndex] = useState<number>();
  const [id, setId] = useState<number>();
  const [answer, setAnswer] = useState<number>(null);
  const [showSol, setShowSol] = useState(false);

  useEffect(() => {
    dispatch(getTematicas({ page: 0, size: 50, sort: 'id,asc' }));
  }, []);

  useEffect(() => {
    dispatch(
      getEntities({ page: 0, size: 1000, sort: 'id,asc', query: `usada.equals=true${tematica ? `&tematicaId.equals=${tematica}` : ''}` })
    ).then((data: any) => {
      const p: IPreguntas[] = data.payload.data;
      setPreguntas(shuffle(p));
    });
  }, [tematica]);

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

  const handleTematica = v => {
    setTematica(v);
  };

  return (
    <Space direction="vertical" className="temario">
      {loading || !pregunta.nro ? (
        <Spin>
          <Alert message="Loading..." />
        </Spin>
      ) : (
        <>
          <Space align="center">
            <Select onChange={handleTematica} className="select" value={tematica}>
              <Select.Option value={''}>ALL</Select.Option>
              {tematicas.map(t => (
                <Select.Option key={t.id} value={t.id}>
                  {t.descrip}
                </Select.Option>
              ))}
            </Select>
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
  );
};

export default Temario;
