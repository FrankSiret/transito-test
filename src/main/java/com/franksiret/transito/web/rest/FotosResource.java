package com.franksiret.transito.web.rest;

import com.franksiret.transito.service.FotosQueryService;
import com.franksiret.transito.service.FotosService;
import com.franksiret.transito.service.criteria.FotosCriteria;
import com.franksiret.transito.service.dto.FotosDTO;
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
 * REST controller for managing {@link com.franksiret.transito.domain.Fotos}.
 */
@RestController
@RequestMapping("/api")
public class FotosResource {

    private final Logger log = LoggerFactory.getLogger(FotosResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FotosService fotosService;

    private final FotosQueryService fotosQueryService;

    public FotosResource(FotosService fotosService, FotosQueryService fotosQueryService) {
        this.fotosService = fotosService;
        this.fotosQueryService = fotosQueryService;
    }

    // /**
    //  * {@code POST  /fotos} : Create a new fotos.
    //  *
    //  * @param fotosDTO the fotosDTO to create.
    //  * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fotosDTO, or with status {@code 400 (Bad Request)} if the fotos has already an ID.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PostMapping("/fotos")
    // public ResponseEntity<FotosDTO> createFotos(@RequestBody FotosDTO fotosDTO) throws URISyntaxException {
    //     log.debug("REST request to save Fotos : {}", fotosDTO);
    //     if (fotosDTO.getNro() != null) {
    //         throw new BadRequestAlertException("A new fotos cannot already have an ID", ENTITY_NAME, "idexists");
    //     }
    //     FotosDTO result = fotosService.save(fotosDTO);
    //     return ResponseEntity
    //         .created(new URI("/api/fotos/" + result.getNro()))
    //         .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getNro().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PUT  /fotos/:id} : Updates an existing fotos.
    //  *
    //  * @param id the id of the fotosDTO to save.
    //  * @param fotosDTO the fotosDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fotosDTO,
    //  * or with status {@code 400 (Bad Request)} if the fotosDTO is not valid,
    //  * or with status {@code 500 (Internal Server Error)} if the fotosDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PutMapping("/fotos/{id}")
    // public ResponseEntity<FotosDTO> updateFotos(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody FotosDTO fotosDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to update Fotos : {}, {}", id, fotosDTO);
    //     if (fotosDTO.getNro() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, fotosDTO.getNro())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!fotosRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     FotosDTO result = fotosService.save(fotosDTO);
    //     return ResponseEntity
    //         .ok()
    //         .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fotosDTO.getNro().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PATCH  /fotos/:id} : Partial updates given fields of an existing fotos, field will ignore if it is null
    //  *
    //  * @param id the id of the fotosDTO to save.
    //  * @param fotosDTO the fotosDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fotosDTO,
    //  * or with status {@code 400 (Bad Request)} if the fotosDTO is not valid,
    //  * or with status {@code 404 (Not Found)} if the fotosDTO is not found,
    //  * or with status {@code 500 (Internal Server Error)} if the fotosDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PatchMapping(value = "/fotos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    // public ResponseEntity<FotosDTO> partialUpdateFotos(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody FotosDTO fotosDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to partial update Fotos partially : {}, {}", id, fotosDTO);
    //     if (fotosDTO.getNro() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, fotosDTO.getNro())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!fotosRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     Optional<FotosDTO> result = fotosService.partialUpdate(fotosDTO);

    //     return ResponseUtil.wrapOrNotFound(
    //         result,
    //         HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fotosDTO.getNro().toString())
    //     );
    // }

    /**
     * {@code GET  /fotos} : get all the fotos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fotos in body.
     */
    @GetMapping("/fotos")
    public ResponseEntity<List<FotosDTO>> getAllFotos(
        FotosCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Fotos by criteria: {}", criteria);
        Page<FotosDTO> page = fotosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fotos/count} : count all the fotos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/fotos/count")
    public ResponseEntity<Long> countFotos(FotosCriteria criteria) {
        log.debug("REST request to count Fotos by criteria: {}", criteria);
        return ResponseEntity.ok().body(fotosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /fotos/:id} : get the "id" fotos.
     *
     * @param id the id of the fotosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fotosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fotos/{id}")
    public ResponseEntity<FotosDTO> getFotos(@PathVariable Integer id) {
        log.debug("REST request to get Fotos : {}", id);
        Optional<FotosDTO> fotosDTO = fotosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fotosDTO);
    }

    // /**
    //  * {@code DELETE  /fotos/:id} : delete the "id" fotos.
    //  *
    //  * @param id the id of the fotosDTO to delete.
    //  * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
    //  */
    // @DeleteMapping("/fotos/{id}")
    // public ResponseEntity<Void> deleteFotos(@PathVariable Integer id) {
    //     log.debug("REST request to delete Fotos : {}", id);
    //     fotosService.delete(id);
    //     return ResponseEntity
    //         .noContent()
    //         .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
    //         .build();
    // }
}
