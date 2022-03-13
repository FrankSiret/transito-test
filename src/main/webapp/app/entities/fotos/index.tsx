import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Fotos from './fotos';
import FotosDetail from './fotos-detail';
import FotosUpdate from './fotos-update';
import FotosDeleteDialog from './fotos-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FotosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FotosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FotosDetail} />
      <ErrorBoundaryRoute path={match.url} component={Fotos} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FotosDeleteDialog} />
  </>
);

export default Routes;
