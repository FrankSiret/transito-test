import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Preguntas from './preguntas';
import PreguntasDetail from './preguntas-detail';
import PreguntasUpdate from './preguntas-update';
import PreguntasDeleteDialog from './preguntas-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PreguntasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PreguntasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PreguntasDetail} />
      <ErrorBoundaryRoute path={match.url} component={Preguntas} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PreguntasDeleteDialog} />
  </>
);

export default Routes;
