package com.franksiret.transito.web.rest;

import com.franksiret.transito.service.TematicasQueryService;
import com.franksiret.transito.service.TematicasService;
import com.franksiret.transito.service.criteria.TematicasCriteria;
import com.franksiret.transito.service.dto.TematicasDTO;
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
 * REST controller for managing {@link com.franksiret.transito.domain.Tematicas}.
 */
@RestController
@RequestMapping("/api")
public class TematicasResource {

    private final Logger log = LoggerFactory.getLogger(TematicasResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TematicasService tematicasService;

    private final TematicasQueryService tematicasQueryService;

    public TematicasResource(
        TematicasService tematicasService,
        TematicasQueryService tematicasQueryService
    ) {
        this.tematicasService = tematicasService;
        this.tematicasQueryService = tematicasQueryService;
    }

    // /**
    //  * {@code POST  /tematicas} : Create a new tematicas.
    //  *
    //  * @param tematicasDTO the tematicasDTO to create.
    //  * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tematicasDTO, or with status {@code 400 (Bad Request)} if the tematicas has already an ID.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PostMapping("/tematicas")
    // public ResponseEntity<TematicasDTO> createTematicas(@RequestBody TematicasDTO tematicasDTO) throws URISyntaxException {
    //     log.debug("REST request to save Tematicas : {}", tematicasDTO);
    //     if (tematicasDTO.getId() != null) {
    //         throw new BadRequestAlertException("A new tematicas cannot already have an ID", ENTITY_NAME, "idexists");
    //     }
    //     TematicasDTO result = tematicasService.save(tematicasDTO);
    //     return ResponseEntity
    //         .created(new URI("/api/tematicas/" + result.getId()))
    //         .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PUT  /tematicas/:id} : Updates an existing tematicas.
    //  *
    //  * @param id the id of the tematicasDTO to save.
    //  * @param tematicasDTO the tematicasDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tematicasDTO,
    //  * or with status {@code 400 (Bad Request)} if the tematicasDTO is not valid,
    //  * or with status {@code 500 (Internal Server Error)} if the tematicasDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PutMapping("/tematicas/{id}")
    // public ResponseEntity<TematicasDTO> updateTematicas(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody TematicasDTO tematicasDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to update Tematicas : {}, {}", id, tematicasDTO);
    //     if (tematicasDTO.getId() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, tematicasDTO.getId())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!tematicasRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     TematicasDTO result = tematicasService.save(tematicasDTO);
    //     return ResponseEntity
    //         .ok()
    //         .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tematicasDTO.getId().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PATCH  /tematicas/:id} : Partial updates given fields of an existing tematicas, field will ignore if it is null
    //  *
    //  * @param id the id of the tematicasDTO to save.
    //  * @param tematicasDTO the tematicasDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tematicasDTO,
    //  * or with status {@code 400 (Bad Request)} if the tematicasDTO is not valid,
    //  * or with status {@code 404 (Not Found)} if the tematicasDTO is not found,
    //  * or with status {@code 500 (Internal Server Error)} if the tematicasDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PatchMapping(value = "/tematicas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    // public ResponseEntity<TematicasDTO> partialUpdateTematicas(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody TematicasDTO tematicasDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to partial update Tematicas partially : {}, {}", id, tematicasDTO);
    //     if (tematicasDTO.getId() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, tematicasDTO.getId())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!tematicasRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     Optional<TematicasDTO> result = tematicasService.partialUpdate(tematicasDTO);

    //     return ResponseUtil.wrapOrNotFound(
    //         result,
    //         HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tematicasDTO.getId().toString())
    //     );
    // }

    /**
     * {@code GET  /tematicas} : get all the tematicas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tematicas in body.
     */
    @GetMapping("/tematicas")
    public ResponseEntity<List<TematicasDTO>> getAllTematicas(
        TematicasCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Tematicas by criteria: {}", criteria);
        Page<TematicasDTO> page = tematicasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tematicas/count} : count all the tematicas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/tematicas/count")
    public ResponseEntity<Long> countTematicas(TematicasCriteria criteria) {
        log.debug("REST request to count Tematicas by criteria: {}", criteria);
        return ResponseEntity.ok().body(tematicasQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tematicas/:id} : get the "id" tematicas.
     *
     * @param id the id of the tematicasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tematicasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tematicas/{id}")
    public ResponseEntity<TematicasDTO> getTematicas(@PathVariable Integer id) {
        log.debug("REST request to get Tematicas : {}", id);
        Optional<TematicasDTO> tematicasDTO = tematicasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tematicasDTO);
    }

    // /**
    //  * {@code DELETE  /tematicas/:id} : delete the "id" tematicas.
    //  *
    //  * @param id the id of the tematicasDTO to delete.
    //  * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
    //  */
    // @DeleteMapping("/tematicas/{id}")
    // public ResponseEntity<Void> deleteTematicas(@PathVariable Integer id) {
    //     log.debug("REST request to delete Tematicas : {}", id);
    //     tematicasService.delete(id);
    //     return ResponseEntity
    //         .noContent()
    //         .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
    //         .build();
    // }
}
