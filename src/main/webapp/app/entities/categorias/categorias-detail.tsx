import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './categorias.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CategoriasDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const categoriasEntity = useAppSelector(state => state.categorias.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoriasDetailsHeading">
          <Translate contentKey="transitoApp.categorias.detail.title">Categorias</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codigo">
              <Translate contentKey="transitoApp.categorias.codigo">Codigo</Translate>
            </span>
          </dt>
          <dd>{categoriasEntity.codigo}</dd>
          <dt>
            <span id="tipo">
              <Translate contentKey="transitoApp.categorias.tipo">Tipo</Translate>
            </span>
          </dt>
          <dd>{categoriasEntity.tipo}</dd>
          <dt>
            <span id="descrip">
              <Translate contentKey="transitoApp.categorias.descrip">Descrip</Translate>
            </span>
          </dt>
          <dd>{categoriasEntity.descrip}</dd>
        </dl>
        <Button tag={Link} to="/categorias" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/categorias/${categoriasEntity.codigo}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CategoriasDetail;
