package com.franksiret.transito.service;

import com.franksiret.transito.domain.Fotos;
import com.franksiret.transito.repository.FotosRepository;
import com.franksiret.transito.service.dto.FotosDTO;
import com.franksiret.transito.service.mapper.FotosMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Fotos}.
 */
@Service
@Transactional
public class FotosService {

    private final Logger log = LoggerFactory.getLogger(FotosService.class);

    private final FotosRepository fotosRepository;

    private final FotosMapper fotosMapper;

    public FotosService(FotosRepository fotosRepository, FotosMapper fotosMapper) {
        this.fotosRepository = fotosRepository;
        this.fotosMapper = fotosMapper;
    }

    /**
     * Save a fotos.
     *
     * @param fotosDTO the entity to save.
     * @return the persisted entity.
     */
    public FotosDTO save(FotosDTO fotosDTO) {
        log.debug("Request to save Fotos : {}", fotosDTO);
        Fotos fotos = fotosMapper.toEntity(fotosDTO);
        fotos = fotosRepository.save(fotos);
        return fotosMapper.toDto(fotos);
    }

    /**
     * Partially update a fotos.
     *
     * @param fotosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FotosDTO> partialUpdate(FotosDTO fotosDTO) {
        log.debug("Request to partially update Fotos : {}", fotosDTO);

        return fotosRepository
            .findById(fotosDTO.getNro())
            .map(existingFotos -> {
                fotosMapper.partialUpdate(existingFotos, fotosDTO);

                return existingFotos;
            })
            .map(fotosRepository::save)
            .map(fotosMapper::toDto);
    }

    /**
     * Get all the fotos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FotosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fotos");
        return fotosRepository.findAll(pageable).map(fotosMapper::toDto);
    }

    /**
     * Get one fotos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FotosDTO> findOne(Integer id) {
        log.debug("Request to get Fotos : {}", id);
        return fotosRepository.findById(id).map(fotosMapper::toDto);
    }

    /**
     * Delete the fotos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Integer id) {
        log.debug("Request to delete Fotos : {}", id);
        fotosRepository.deleteById(id);
    }
}
