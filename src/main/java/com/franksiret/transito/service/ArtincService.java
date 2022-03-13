package com.franksiret.transito.service;

import com.franksiret.transito.domain.Artinc;
import com.franksiret.transito.repository.ArtincRepository;
import com.franksiret.transito.service.dto.ArtincDTO;
import com.franksiret.transito.service.mapper.ArtincMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Artinc}.
 */
@Service
@Transactional
public class ArtincService {

    private final Logger log = LoggerFactory.getLogger(ArtincService.class);

    private final ArtincRepository artincRepository;

    private final ArtincMapper artincMapper;

    public ArtincService(ArtincRepository artincRepository, ArtincMapper artincMapper) {
        this.artincRepository = artincRepository;
        this.artincMapper = artincMapper;
    }

    /**
     * Save a artinc.
     *
     * @param artincDTO the entity to save.
     * @return the persisted entity.
     */
    public ArtincDTO save(ArtincDTO artincDTO) {
        log.debug("Request to save Artinc : {}", artincDTO);
        Artinc artinc = artincMapper.toEntity(artincDTO);
        artinc = artincRepository.save(artinc);
        return artincMapper.toDto(artinc);
    }

    /**
     * Partially update a artinc.
     *
     * @param artincDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ArtincDTO> partialUpdate(ArtincDTO artincDTO) {
        log.debug("Request to partially update Artinc : {}", artincDTO);

        return artincRepository
            .findById(artincDTO.getArtinc())
            .map(existingArtinc -> {
                artincMapper.partialUpdate(existingArtinc, artincDTO);

                return existingArtinc;
            })
            .map(artincRepository::save)
            .map(artincMapper::toDto);
    }

    /**
     * Get all the artincs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ArtincDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Artincs");
        return artincRepository.findAll(pageable).map(artincMapper::toDto);
    }

    /**
     * Get one artinc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ArtincDTO> findOne(Integer id) {
        log.debug("Request to get Artinc : {}", id);
        return artincRepository.findById(id).map(artincMapper::toDto);
    }

    /**
     * Delete the artinc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Integer id) {
        log.debug("Request to delete Artinc : {}", id);
        artincRepository.deleteById(id);
    }
}
