package com.franksiret.transito.service;

import com.franksiret.transito.domain.Tematicas;
import com.franksiret.transito.repository.TematicasRepository;
import com.franksiret.transito.service.dto.TematicasDTO;
import com.franksiret.transito.service.mapper.TematicasMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Tematicas}.
 */
@Service
@Transactional
public class TematicasService {

    private final Logger log = LoggerFactory.getLogger(TematicasService.class);

    private final TematicasRepository tematicasRepository;

    private final TematicasMapper tematicasMapper;

    public TematicasService(TematicasRepository tematicasRepository, TematicasMapper tematicasMapper) {
        this.tematicasRepository = tematicasRepository;
        this.tematicasMapper = tematicasMapper;
    }

    /**
     * Save a tematicas.
     *
     * @param tematicasDTO the entity to save.
     * @return the persisted entity.
     */
    public TematicasDTO save(TematicasDTO tematicasDTO) {
        log.debug("Request to save Tematicas : {}", tematicasDTO);
        Tematicas tematicas = tematicasMapper.toEntity(tematicasDTO);
        tematicas = tematicasRepository.save(tematicas);
        return tematicasMapper.toDto(tematicas);
    }

    /**
     * Partially update a tematicas.
     *
     * @param tematicasDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TematicasDTO> partialUpdate(TematicasDTO tematicasDTO) {
        log.debug("Request to partially update Tematicas : {}", tematicasDTO);

        return tematicasRepository
            .findById(tematicasDTO.getId())
            .map(existingTematicas -> {
                tematicasMapper.partialUpdate(existingTematicas, tematicasDTO);

                return existingTematicas;
            })
            .map(tematicasRepository::save)
            .map(tematicasMapper::toDto);
    }

    /**
     * Get all the tematicas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TematicasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tematicas");
        return tematicasRepository.findAll(pageable).map(tematicasMapper::toDto);
    }

    /**
     * Get one tematicas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TematicasDTO> findOne(Integer id) {
        log.debug("Request to get Tematicas : {}", id);
        return tematicasRepository.findById(id).map(tematicasMapper::toDto);
    }

    /**
     * Delete the tematicas by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Integer id) {
        log.debug("Request to delete Tematicas : {}", id);
        tematicasRepository.deleteById(id);
    }
}
