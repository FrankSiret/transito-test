import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Tematicas from './tematicas';
import TematicasDetail from './tematicas-detail';
import TematicasUpdate from './tematicas-update';
import TematicasDeleteDialog from './tematicas-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TematicasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TematicasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TematicasDetail} />
      <ErrorBoundaryRoute path={match.url} component={Tematicas} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TematicasDeleteDialog} />
  </>
);

export default Routes;
