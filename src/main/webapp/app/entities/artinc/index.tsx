import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Artinc from './artinc';
import ArtincDetail from './artinc-detail';
import ArtincUpdate from './artinc-update';
import ArtincDeleteDialog from './artinc-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ArtincUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ArtincUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ArtincDetail} />
      <ErrorBoundaryRoute path={match.url} component={Artinc} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ArtincDeleteDialog} />
  </>
);

export default Routes;
