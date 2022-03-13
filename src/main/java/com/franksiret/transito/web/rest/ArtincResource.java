package com.franksiret.transito.web.rest;

import com.franksiret.transito.service.ArtincQueryService;
import com.franksiret.transito.service.ArtincService;
import com.franksiret.transito.service.criteria.ArtincCriteria;
import com.franksiret.transito.service.dto.ArtincDTO;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.franksiret.transito.domain.Artinc}.
 */
@RestController
@RequestMapping("/api")
public class ArtincResource {

    private final Logger log = LoggerFactory.getLogger(ArtincResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArtincService artincService;

    private final ArtincQueryService artincQueryService;

    public ArtincResource(ArtincService artincService, ArtincQueryService artincQueryService) {
        this.artincService = artincService;
        this.artincQueryService = artincQueryService;
    }

    // /**
    //  * {@code POST  /artincs} : Create a new artinc.
    //  *
    //  * @param artincDTO the artincDTO to create.
    //  * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new artincDTO, or with status {@code 400 (Bad Request)} if the artinc has already an ID.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PostMapping("/artincs")
    // public ResponseEntity<ArtincDTO> createArtinc(@RequestBody ArtincDTO artincDTO) throws URISyntaxException {
    //     log.debug("REST request to save Artinc : {}", artincDTO);
    //     if (artincDTO.getArtinc() != null) {
    //         throw new BadRequestAlertException("A new artinc cannot already have an ID", ENTITY_NAME, "idexists");
    //     }
    //     ArtincDTO result = artincService.save(artincDTO);
    //     return ResponseEntity
    //         .created(new URI("/api/artincs/" + result.getArtinc()))
    //         .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getArtinc().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PUT  /artincs/:id} : Updates an existing artinc.
    //  *
    //  * @param id the id of the artincDTO to save.
    //  * @param artincDTO the artincDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated artincDTO,
    //  * or with status {@code 400 (Bad Request)} if the artincDTO is not valid,
    //  * or with status {@code 500 (Internal Server Error)} if the artincDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PutMapping("/artincs/{id}")
    // public ResponseEntity<ArtincDTO> updateArtinc(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody ArtincDTO artincDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to update Artinc : {}, {}", id, artincDTO);
    //     if (artincDTO.getArtinc() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, artincDTO.getArtinc())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!artincRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     ArtincDTO result = artincService.save(artincDTO);
    //     return ResponseEntity
    //         .ok()
    //         .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, artincDTO.getArtinc().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PATCH  /artincs/:id} : Partial updates given fields of an existing artinc, field will ignore if it is null
    //  *
    //  * @param id the id of the artincDTO to save.
    //  * @param artincDTO the artincDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated artincDTO,
    //  * or with status {@code 400 (Bad Request)} if the artincDTO is not valid,
    //  * or with status {@code 404 (Not Found)} if the artincDTO is not found,
    //  * or with status {@code 500 (Internal Server Error)} if the artincDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PatchMapping(value = "/artincs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    // public ResponseEntity<ArtincDTO> partialUpdateArtinc(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody ArtincDTO artincDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to partial update Artinc partially : {}, {}", id, artincDTO);
    //     if (artincDTO.getArtinc() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, artincDTO.getArtinc())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!artincRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     Optional<ArtincDTO> result = artincService.partialUpdate(artincDTO);

    //     return ResponseUtil.wrapOrNotFound(
    //         result,
    //         HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, artincDTO.getArtinc().toString())
    //     );
    // }

    /**
     * {@code GET  /artincs} : get all the artincs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of artincs in body.
     */
    @GetMapping("/artincs")
    public ResponseEntity<List<ArtincDTO>> getAllArtincs(
        ArtincCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Artincs by criteria: {}", criteria);
        Page<ArtincDTO> page = artincQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /artincs/count} : count all the artincs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/artincs/count")
    public ResponseEntity<Long> countArtincs(ArtincCriteria criteria) {
        log.debug("REST request to count Artincs by criteria: {}", criteria);
        return ResponseEntity.ok().body(artincQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /artincs/:id} : get the "id" artinc.
     *
     * @param id the id of the artincDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the artincDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/artincs/{id}")
    public ResponseEntity<ArtincDTO> getArtinc(@PathVariable Integer id) {
        log.debug("REST request to get Artinc : {}", id);
        Optional<ArtincDTO> artincDTO = artincService.findOne(id);
        return ResponseUtil.wrapOrNotFound(artincDTO);
    }

    // /**
    //  * {@code DELETE  /artincs/:id} : delete the "id" artinc.
    //  *
    //  * @param id the id of the artincDTO to delete.
    //  * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
    //  */
    // @DeleteMapping("/artincs/{id}")
    // public ResponseEntity<Void> deleteArtinc(@PathVariable Integer id) {
    //     log.debug("REST request to delete Artinc : {}", id);
    //     artincService.delete(id);
    //     return ResponseEntity
    //         .noContent()
    //         .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
    //         .build();
    // }
}
