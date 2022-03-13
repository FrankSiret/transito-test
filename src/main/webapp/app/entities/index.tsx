import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Artinc from './artinc';
import Categorias from './categorias';
import Fotos from './fotos';
import Tematicas from './tematicas';
import Preguntas from './preguntas';
import Temario from './temario/intex';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}artinc`} component={Artinc} />
      <ErrorBoundaryRoute path={`${match.url}categorias`} component={Categorias} />
      <ErrorBoundaryRoute path={`${match.url}fotos`} component={Fotos} />
      <ErrorBoundaryRoute path={`${match.url}tematicas`} component={Tematicas} />
      <ErrorBoundaryRoute path={`${match.url}preguntas`} component={Preguntas} />
      <ErrorBoundaryRoute path={`${match.url}temario`} component={Temario} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
