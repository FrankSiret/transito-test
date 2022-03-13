import { Alert, Button, Image, Radio, Space, Spin, Tag, notification, Divider } from 'antd';
import Text from 'antd/lib/typography/Text';
import Title from 'antd/lib/typography/Title';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import React, { useEffect, useState } from 'react';
import { byteSize, openFile } from 'react-jhipster';
import { toast } from 'react-toastify';
import { getEntities, getEntity } from '../preguntas/preguntas.reducer';
import { ArrowLeftOutlined, ArrowRightOutlined } from '@ant-design/icons'
import './temario.scss';

const Temario = () => {
	const dispatch = useAppDispatch();
	const preguntas = useAppSelector(state => state.preguntas.entities)
	const pregunta = useAppSelector(state => state.preguntas.entity)
	const loading = useAppSelector(state => state.preguntas.loading)
	const [index, setIndex] = useState<number>();
	const [id, setId] = useState<number>();
	const [answer, setAnswer] = useState<number>(null);
	const [showSol, setShowSol] = useState(false);

	useEffect(() => {
		dispatch(getEntities({ page: 0, size: 1000, sort: 'id,asc', query: `usada.equals=true` }))
	}, [])

	useEffect(() => {
		if (preguntas.length > 0) {
			setIndex(0)
		}
	}, [preguntas])

	useEffect(() => {
		if (index >= 0) {
			setId(preguntas[index].nro)
		}
	}, [index])

	useEffect(() => {
		if (id) {
			dispatch(getEntity(id))
		}
	}, [id])

	const next = () => {
		setShowSol(false)
		setAnswer(null)
		index < preguntas.length - 1 && setIndex(i => i + 1)
	}

	const prev = () => {
		setShowSol(false)
		setAnswer(null)
		index > 0 && setIndex(i => i - 1)
	}

	const show = () => {
		setShowSol(true);
	}

	const check = () => {
		if (answer === pregunta.correcta)
			toast.success('Corrent answer')
		else toast.error('Invalid answer')

		show()
	}

	const openNotificationWithIcon = type => {
		notification[type]({
			message: `Artículo ${pregunta?.artinc?.artinc}`,
			description: pregunta?.artinc?.descrip
		});
	};

	return (
		<Space direction="vertical" className='space' >
			{(loading || !pregunta.nro) ? (
				<Spin>
					<Alert message="Loading..." />
				</Spin>
			) : (
				<>
					<Space>
						{pregunta?.artinc?.artinc && <Tag color='magenta'>Artículo (inciso): {pregunta.artinc.artinc}</Tag>}
						{pregunta?.artinc?.pelig && <Tag color='error'>Peligrosidad: {pregunta.artinc.pelig}</Tag>}
						{pregunta?.artinc?.descrip && <Tag color='default' onClick={() => openNotificationWithIcon('info')}>Descripción</Tag>}
						{pregunta?.tematica?.descrip && <Tag color='cyan'>Temática: {pregunta.tematica.descrip}</Tag>}
					</Space>
					<Divider />
					<Space align="start" className='space'>
						<Space direction="vertical">
							<Title level={5}>{pregunta.texto}</Title>
							<Radio.Group name="test" className='radio-group' >
								<Radio
									value={1}
									onClick={() => setAnswer(1)}
									className={`${showSol && pregunta.correcta === 1 ? 'radio-success' : showSol && answer !== null && answer === 1 ? 'radio-error' : ''}`}
								>
									{pregunta.resp1}
								</Radio>
								<Radio
									value={2}
									onClick={() => setAnswer(2)}
									className={`${showSol && pregunta.correcta === 2 ? 'radio-success' : showSol && answer !== null && answer === 2 ? 'radio-error' : ''}`}
								>
									{pregunta.resp2}
								</Radio>
								<Radio
									value={3}
									onClick={() => setAnswer(3)}
									className={`${showSol && pregunta.correcta === 3 ? 'radio-success' : showSol && answer !== null && answer === 3 ? 'radio-error' : ''}`}
								>
									{pregunta.resp3}
								</Radio>
							</Radio.Group>
						</Space>
						{pregunta.foto?.foto && <Image width={200} className="image" src={`data:image/png;base64,${pregunta.foto.foto}`} />}
					</Space>
					<Divider />
					<Space>
						<Button onClick={prev} icon={<ArrowLeftOutlined />}>Prev</Button>
						<Button onClick={show} type='dashed'>Show solution</Button>
						<Button onClick={check} type='primary'>Check</Button>
						<Button onClick={next} icon={<ArrowRightOutlined />}>Next</Button>
					</Space>
				</>
			)}
		</Space>
	)
}

export default Temario;