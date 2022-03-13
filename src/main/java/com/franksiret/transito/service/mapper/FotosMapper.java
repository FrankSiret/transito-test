package com.franksiret.transito.service.mapper;

import com.franksiret.transito.domain.Fotos;
import com.franksiret.transito.service.dto.FotosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fotos} and its DTO {@link FotosDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FotosMapper extends EntityMapper<FotosDTO, Fotos> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "nro", source = "nro")
    FotosDTO toDtoId(Fotos fotos);
}
