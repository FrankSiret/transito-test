import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale from './locale';

// prettier-ignore
import artinc from 'app/entities/artinc/artinc.reducer';
// prettier-ignore
import categorias from 'app/entities/categorias/categorias.reducer';
// prettier-ignore
import fotos from 'app/entities/fotos/fotos.reducer';
// prettier-ignore
import tematicas from 'app/entities/tematicas/tematicas.reducer';
// prettier-ignore
import preguntas from 'app/entities/preguntas/preguntas.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  locale,
  artinc,
  categorias,
  fotos,
  tematicas,
  preguntas,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
