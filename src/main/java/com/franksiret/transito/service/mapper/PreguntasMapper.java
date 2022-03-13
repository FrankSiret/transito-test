package com.franksiret.transito.service.mapper;

import com.franksiret.transito.domain.Preguntas;
import com.franksiret.transito.service.dto.PreguntasDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Preguntas} and its DTO {@link PreguntasDTO}.
 */
@Mapper(componentModel = "spring", uses = { FotosMapper.class, TematicasMapper.class, ArtincMapper.class })
public interface PreguntasMapper extends EntityMapper<PreguntasDTO, Preguntas> {
    @Mapping(target = "foto", source = "foto")
    @Mapping(target = "tematica", source = "tematica")
    @Mapping(target = "artinc", source = "artinc")
    PreguntasDTO toDto(Preguntas s);

    @Named("id")
    @Mapping(target = "foto", source = "foto", ignore = true)
    @Mapping(target = "tematica", source = "tematica", qualifiedByName = "id")
    @Mapping(target = "artinc", source = "artinc", ignore = true)
    PreguntasDTO toDtoId(Preguntas s);
}
