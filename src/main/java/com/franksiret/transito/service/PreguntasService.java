package com.franksiret.transito.service;

import com.franksiret.transito.domain.Preguntas;
import com.franksiret.transito.repository.PreguntasRepository;
import com.franksiret.transito.service.dto.PreguntasDTO;
import com.franksiret.transito.service.mapper.PreguntasMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Preguntas}.
 */
@Service
@Transactional
public class PreguntasService {

    private final Logger log = LoggerFactory.getLogger(PreguntasService.class);

    private final PreguntasRepository preguntasRepository;

    private final PreguntasMapper preguntasMapper;

    public PreguntasService(PreguntasRepository preguntasRepository, PreguntasMapper preguntasMapper) {
        this.preguntasRepository = preguntasRepository;
        this.preguntasMapper = preguntasMapper;
    }

    /**
     * Save a preguntas.
     *
     * @param preguntasDTO the entity to save.
     * @return the persisted entity.
     */
    public PreguntasDTO save(PreguntasDTO preguntasDTO) {
        log.debug("Request to save Preguntas : {}", preguntasDTO);
        Preguntas preguntas = preguntasMapper.toEntity(preguntasDTO);
        preguntas = preguntasRepository.save(preguntas);
        return preguntasMapper.toDto(preguntas);
    }

    /**
     * Partially update a preguntas.
     *
     * @param preguntasDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PreguntasDTO> partialUpdate(PreguntasDTO preguntasDTO) {
        log.debug("Request to partially update Preguntas : {}", preguntasDTO);

        return preguntasRepository
            .findById(preguntasDTO.getNro())
            .map(existingPreguntas -> {
                preguntasMapper.partialUpdate(existingPreguntas, preguntasDTO);

                return existingPreguntas;
            })
            .map(preguntasRepository::save)
            .map(preguntasMapper::toDto);
    }

    /**
     * Get all the preguntas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PreguntasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Preguntas");
        return preguntasRepository.findAll(pageable).map(preguntasMapper::toDtoId);
    }

    /**
     * Get one preguntas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PreguntasDTO> findOne(Integer id) {
        log.debug("Request to get Preguntas : {}", id);
        return preguntasRepository.findById(id).map(preguntasMapper::toDto);
    }

    /**
     * Delete the preguntas by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Integer id) {
        log.debug("Request to delete Preguntas : {}", id);
        preguntasRepository.deleteById(id);
    }
}
