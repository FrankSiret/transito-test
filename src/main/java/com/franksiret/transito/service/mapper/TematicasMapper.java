package com.franksiret.transito.service.mapper;

import com.franksiret.transito.domain.Tematicas;
import com.franksiret.transito.service.dto.TematicasDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tematicas} and its DTO {@link TematicasDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TematicasMapper extends EntityMapper<TematicasDTO, Tematicas> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TematicasDTO toDtoId(Tematicas tematicas);
}
