import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './artinc.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ArtincDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const artincEntity = useAppSelector(state => state.artinc.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="artincDetailsHeading">
          <Translate contentKey="transitoApp.artinc.detail.title">Artinc</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="artinc">
              <Translate contentKey="transitoApp.artinc.artinc">Artinc</Translate>
            </span>
          </dt>
          <dd>{artincEntity.artinc}</dd>
          <dt>
            <span id="pelig">
              <Translate contentKey="transitoApp.artinc.pelig">Pelig</Translate>
            </span>
          </dt>
          <dd>{artincEntity.pelig}</dd>
          <dt>
            <span id="descrip">
              <Translate contentKey="transitoApp.artinc.descrip">Descrip</Translate>
            </span>
          </dt>
          <dd>{artincEntity.descrip}</dd>
        </dl>
        <Button tag={Link} to="/artinc" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/artinc/${artincEntity.artinc}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ArtincDetail;
