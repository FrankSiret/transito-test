import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './preguntas.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PreguntasDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const preguntasEntity = useAppSelector(state => state.preguntas.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="preguntasDetailsHeading">
          <Translate contentKey="transitoApp.preguntas.detail.title">Preguntas</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nro">
              <Translate contentKey="transitoApp.preguntas.nro">Nro</Translate>
            </span>
          </dt>
          <dd>{preguntasEntity.nro}</dd>
          <dt>
            <span id="texto">
              <Translate contentKey="transitoApp.preguntas.texto">Texto</Translate>
            </span>
          </dt>
          <dd>{preguntasEntity.texto}</dd>
          <dt>
            <span id="resp1">
              <Translate contentKey="transitoApp.preguntas.resp1">Resp 1</Translate>
            </span>
          </dt>
          <dd>{preguntasEntity.resp1}</dd>
          <dt>
            <span id="resp2">
              <Translate contentKey="transitoApp.preguntas.resp2">Resp 2</Translate>
            </span>
          </dt>
          <dd>{preguntasEntity.resp2}</dd>
          <dt>
            <span id="resp3">
              <Translate contentKey="transitoApp.preguntas.resp3">Resp 3</Translate>
            </span>
          </dt>
          <dd>{preguntasEntity.resp3}</dd>
          <dt>
            <span id="correcta">
              <Translate contentKey="transitoApp.preguntas.correcta">Correcta</Translate>
            </span>
          </dt>
          <dd>{preguntasEntity.correcta}</dd>
          <dt>
            <span id="usada">
              <Translate contentKey="transitoApp.preguntas.usada">Usada</Translate>
            </span>
          </dt>
          <dd>{preguntasEntity.usada ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="transitoApp.preguntas.tematica">Tematica</Translate>
          </dt>
          <dd>{preguntasEntity.tematica ? preguntasEntity.tematica.id : ''}</dd>
          <dt>
            <Translate contentKey="transitoApp.preguntas.artinc">Artinc</Translate>
          </dt>
          <dd>{preguntasEntity.artinc ? preguntasEntity.artinc.artinc : ''}</dd>
        </dl>
        <Button tag={Link} to="/preguntas" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/preguntas/${preguntasEntity.nro}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PreguntasDetail;
