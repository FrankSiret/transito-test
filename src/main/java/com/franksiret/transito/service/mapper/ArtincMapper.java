package com.franksiret.transito.service.mapper;

import com.franksiret.transito.domain.Artinc;
import com.franksiret.transito.service.dto.ArtincDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Artinc} and its DTO {@link ArtincDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArtincMapper extends EntityMapper<ArtincDTO, Artinc> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "artinc", source = "artinc")
    ArtincDTO toDtoId(Artinc artinc);
}
