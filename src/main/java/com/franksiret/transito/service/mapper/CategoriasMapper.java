package com.franksiret.transito.service.mapper;

import com.franksiret.transito.domain.Categorias;
import com.franksiret.transito.service.dto.CategoriasDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categorias} and its DTO {@link CategoriasDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoriasMapper extends EntityMapper<CategoriasDTO, Categorias> {}
