import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './tematicas.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TematicasDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const tematicasEntity = useAppSelector(state => state.tematicas.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tematicasDetailsHeading">
          <Translate contentKey="transitoApp.tematicas.detail.title">Tematicas</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="transitoApp.tematicas.id">Id</Translate>
            </span>
          </dt>
          <dd>{tematicasEntity.id}</dd>
          <dt>
            <span id="descrip">
              <Translate contentKey="transitoApp.tematicas.descrip">Descrip</Translate>
            </span>
          </dt>
          <dd>{tematicasEntity.descrip}</dd>
          <dt>
            <span id="cantidad">
              <Translate contentKey="transitoApp.tematicas.cantidad">Cantidad</Translate>
            </span>
          </dt>
          <dd>{tematicasEntity.cantidad}</dd>
        </dl>
        <Button tag={Link} to="/tematicas" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tematicas/${tematicasEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TematicasDetail;
