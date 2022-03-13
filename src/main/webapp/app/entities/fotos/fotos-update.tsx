import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './fotos.reducer';
import { IFotos } from 'app/shared/model/fotos.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FotosUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const fotosEntity = useAppSelector(state => state.fotos.entity);
  const loading = useAppSelector(state => state.fotos.loading);
  const updating = useAppSelector(state => state.fotos.updating);
  const updateSuccess = useAppSelector(state => state.fotos.updateSuccess);
  const handleClose = () => {
    props.history.push('/fotos' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...fotosEntity,
      ...values,
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
          ...fotosEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="transitoApp.fotos.home.createOrEditLabel" data-cy="FotosCreateUpdateHeading">
            <Translate contentKey="transitoApp.fotos.home.createOrEditLabel">Create or edit a Fotos</Translate>
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
                <ValidatedField label={translate('transitoApp.fotos.nro')} id="fotos-nro" name="nro" data-cy="nro" type="text" />
              ) : null}
              <ValidatedBlobField
                label={translate('transitoApp.fotos.foto')}
                id="fotos-foto"
                name="foto"
                data-cy="foto"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedBlobField
                label={translate('transitoApp.fotos.foto1')}
                id="fotos-foto1"
                name="foto1"
                data-cy="foto1"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedBlobField
                label={translate('transitoApp.fotos.foto2')}
                id="fotos-foto2"
                name="foto2"
                data-cy="foto2"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedBlobField
                label={translate('transitoApp.fotos.foto3')}
                id="fotos-foto3"
                name="foto3"
                data-cy="foto3"
                openActionLabel={translate('entity.action.open')}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fotos" replace color="info">
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

export default FotosUpdate;
