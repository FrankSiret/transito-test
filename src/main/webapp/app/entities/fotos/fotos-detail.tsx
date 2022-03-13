import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './fotos.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FotosDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const fotosEntity = useAppSelector(state => state.fotos.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fotosDetailsHeading">
          <Translate contentKey="transitoApp.fotos.detail.title">Fotos</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nro">
              <Translate contentKey="transitoApp.fotos.nro">Nro</Translate>
            </span>
          </dt>
          <dd>{fotosEntity.nro}</dd>
          <dt>
            <span id="foto">
              <Translate contentKey="transitoApp.fotos.foto">Foto</Translate>
            </span>
          </dt>
          <dd>
            {fotosEntity.foto ? (
              <div>
                <a onClick={openFile('image/png', fotosEntity.foto)}>
                  <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                </a>
                <span>
                  {'image/png'}, {byteSize(fotosEntity.foto)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="foto1">
              <Translate contentKey="transitoApp.fotos.foto1">Foto 1</Translate>
            </span>
          </dt>
          <dd>
            {fotosEntity.foto1 ? (
              <div>
                <a onClick={openFile('image/png', fotosEntity.foto1)}>
                  <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                </a>
                <span>
                  {'image/png'}, {byteSize(fotosEntity.foto1)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="foto2">
              <Translate contentKey="transitoApp.fotos.foto2">Foto 2</Translate>
            </span>
          </dt>
          <dd>
            {fotosEntity.foto2 ? (
              <div>
                <a onClick={openFile('image/png', fotosEntity.foto2)}>
                  <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                </a>
                <span>
                  {'image/png'}, {byteSize(fotosEntity.foto2)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="foto3">
              <Translate contentKey="transitoApp.fotos.foto3">Foto 3</Translate>
            </span>
          </dt>
          <dd>
            {fotosEntity.foto3 ? (
              <div>
                <a onClick={openFile('image/png', fotosEntity.foto3)}>
                  <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                </a>
                <span>
                  {'image/png'}, {byteSize(fotosEntity.foto3)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/fotos" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fotos/${fotosEntity.nro}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FotosDetail;
