import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/artinc">
      <Translate contentKey="global.menu.entities.artinc" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/categorias">
      <Translate contentKey="global.menu.entities.categorias" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/fotos">
      <Translate contentKey="global.menu.entities.fotos" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tematicas">
      <Translate contentKey="global.menu.entities.tematicas" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/preguntas">
      <Translate contentKey="global.menu.entities.preguntas" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
