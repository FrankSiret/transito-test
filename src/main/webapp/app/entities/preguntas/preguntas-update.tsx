import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IFotos } from 'app/shared/model/fotos.model';
import { getEntities as getFotos } from 'app/entities/fotos/fotos.reducer';
import { ITematicas } from 'app/shared/model/tematicas.model';
import { getEntities as getTematicas } from 'app/entities/tematicas/tematicas.reducer';
import { IArtinc } from 'app/shared/model/artinc.model';
import { getEntities as getArtincs } from 'app/entities/artinc/artinc.reducer';
import { getEntity, updateEntity, createEntity, reset } from './preguntas.reducer';
import { IPreguntas } from 'app/shared/model/preguntas.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PreguntasUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const fotos = useAppSelector(state => state.fotos.entities);
  const tematicas = useAppSelector(state => state.tematicas.entities);
  const artincs = useAppSelector(state => state.artinc.entities);
  const preguntasEntity = useAppSelector(state => state.preguntas.entity);
  const loading = useAppSelector(state => state.preguntas.loading);
  const updating = useAppSelector(state => state.preguntas.updating);
  const updateSuccess = useAppSelector(state => state.preguntas.updateSuccess);
  const handleClose = () => {
    props.history.push('/preguntas' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getTematicas({}));
    dispatch(getArtincs({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...preguntasEntity,
      ...values,
      foto: fotos.find(it => it.nro.toString() === values.foto.toString()),
      tematica: tematicas.find(it => it.id.toString() === values.tematica.toString()),
      artinc: artincs.find(it => it.artinc.toString() === values.artinc.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...preguntasEntity,
          foto: preguntasEntity?.foto?.nro,
          tematica: preguntasEntity?.tematica?.id,
          artinc: preguntasEntity?.artinc?.artinc,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="transitoApp.preguntas.home.createOrEditLabel" data-cy="PreguntasCreateUpdateHeading">
            <Translate contentKey="transitoApp.preguntas.home.createOrEditLabel">Create or edit a Preguntas</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField label={translate('transitoApp.preguntas.nro')} id="preguntas-nro" name="nro" data-cy="nro" type="text" />
              ) : null}
              <ValidatedField
                label={translate('transitoApp.preguntas.texto')}
                id="preguntas-texto"
                name="texto"
                data-cy="texto"
                type="text"
              />
              <ValidatedField
                label={translate('transitoApp.preguntas.resp1')}
                id="preguntas-resp1"
                name="resp1"
                data-cy="resp1"
                type="text"
              />
              <ValidatedField
                label={translate('transitoApp.preguntas.resp2')}
                id="preguntas-resp2"
                name="resp2"
                data-cy="resp2"
                type="text"
              />
              <ValidatedField
                label={translate('transitoApp.preguntas.resp3')}
                id="preguntas-resp3"
                name="resp3"
                data-cy="resp3"
                type="text"
              />
              <ValidatedField
                label={translate('transitoApp.preguntas.correcta')}
                id="preguntas-correcta"
                name="correcta"
                data-cy="correcta"
                type="text"
              />
              <ValidatedField
                label={translate('transitoApp.preguntas.usada')}
                id="preguntas-usada"
                name="usada"
                data-cy="usada"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('transitoApp.preguntas.puesto')}
                id="preguntas-puesto"
                name="puesto"
                data-cy="puesto"
                type="text"
              />
              <ValidatedField
                id="preguntas-foto"
                name="foto"
                data-cy="foto"
                label={translate('transitoApp.preguntas.foto')}
                type="select"
              >
                <option value="" key="0" />
                {fotos
                  ? fotos.map(otherEntity => (
                      <option value={otherEntity.nro} key={otherEntity.nro}>
                        {otherEntity.nro}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="preguntas-tematica"
                name="tematica"
                data-cy="tematica"
                label={translate('transitoApp.preguntas.tematica')}
                type="select"
              >
                <option value="" key="0" />
                {tematicas
                  ? tematicas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="preguntas-artinc"
                name="artinc"
                data-cy="artinc"
                label={translate('transitoApp.preguntas.artinc')}
                type="select"
              >
                <option value="" key="0" />
                {artincs
                  ? artincs.map(otherEntity => (
                      <option value={otherEntity.artinc} key={otherEntity.artinc}>
                        {otherEntity.artinc}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/preguntas" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PreguntasUpdate;
