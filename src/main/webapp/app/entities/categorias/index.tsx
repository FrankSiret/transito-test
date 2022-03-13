import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Categorias from './categorias';
import CategoriasDetail from './categorias-detail';
import CategoriasUpdate from './categorias-update';
import CategoriasDeleteDialog from './categorias-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CategoriasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CategoriasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CategoriasDetail} />
      <ErrorBoundaryRoute path={match.url} component={Categorias} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CategoriasDeleteDialog} />
  </>
);

export default Routes;
