package com.franksiret.transito.service;

import com.franksiret.transito.domain.Categorias;
import com.franksiret.transito.repository.CategoriasRepository;
import com.franksiret.transito.service.dto.CategoriasDTO;
import com.franksiret.transito.service.mapper.CategoriasMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Categorias}.
 */
@Service
@Transactional
public class CategoriasService {

    private final Logger log = LoggerFactory.getLogger(CategoriasService.class);

    private final CategoriasRepository categoriasRepository;

    private final CategoriasMapper categoriasMapper;

    public CategoriasService(CategoriasRepository categoriasRepository, CategoriasMapper categoriasMapper) {
        this.categoriasRepository = categoriasRepository;
        this.categoriasMapper = categoriasMapper;
    }

    /**
     * Save a categorias.
     *
     * @param categoriasDTO the entity to save.
     * @return the persisted entity.
     */
    public CategoriasDTO save(CategoriasDTO categoriasDTO) {
        log.debug("Request to save Categorias : {}", categoriasDTO);
        Categorias categorias = categoriasMapper.toEntity(categoriasDTO);
        categorias = categoriasRepository.save(categorias);
        return categoriasMapper.toDto(categorias);
    }

    /**
     * Partially update a categorias.
     *
     * @param categoriasDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CategoriasDTO> partialUpdate(CategoriasDTO categoriasDTO) {
        log.debug("Request to partially update Categorias : {}", categoriasDTO);

        return categoriasRepository
            .findById(categoriasDTO.getCodigo())
            .map(existingCategorias -> {
                categoriasMapper.partialUpdate(existingCategorias, categoriasDTO);

                return existingCategorias;
            })
            .map(categoriasRepository::save)
            .map(categoriasMapper::toDto);
    }

    /**
     * Get all the categorias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CategoriasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categorias");
        return categoriasRepository.findAll(pageable).map(categoriasMapper::toDto);
    }

    /**
     * Get one categorias by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategoriasDTO> findOne(Integer id) {
        log.debug("Request to get Categorias : {}", id);
        return categoriasRepository.findById(id).map(categoriasMapper::toDto);
    }

    /**
     * Delete the categorias by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Integer id) {
        log.debug("Request to delete Categorias : {}", id);
        categoriasRepository.deleteById(id);
    }
}
