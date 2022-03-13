package com.franksiret.transito.web.rest;

import com.franksiret.transito.service.PreguntasQueryService;
import com.franksiret.transito.service.PreguntasService;
import com.franksiret.transito.service.criteria.PreguntasCriteria;
import com.franksiret.transito.service.dto.PreguntasDTO;
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
 * REST controller for managing {@link com.franksiret.transito.domain.Preguntas}.
 */
@RestController
@RequestMapping("/api")
public class PreguntasResource {

    private final Logger log = LoggerFactory.getLogger(PreguntasResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PreguntasService preguntasService;

    private final PreguntasQueryService preguntasQueryService;

    public PreguntasResource(
        PreguntasService preguntasService,
        PreguntasQueryService preguntasQueryService
    ) {
        this.preguntasService = preguntasService;
        this.preguntasQueryService = preguntasQueryService;
    }

    // /**
    //  * {@code POST  /preguntas} : Create a new preguntas.
    //  *
    //  * @param preguntasDTO the preguntasDTO to create.
    //  * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new preguntasDTO, or with status {@code 400 (Bad Request)} if the preguntas has already an ID.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PostMapping("/preguntas")
    // public ResponseEntity<PreguntasDTO> createPreguntas(@RequestBody PreguntasDTO preguntasDTO) throws URISyntaxException {
    //     log.debug("REST request to save Preguntas : {}", preguntasDTO);
    //     if (preguntasDTO.getNro() != null) {
    //         throw new BadRequestAlertException("A new preguntas cannot already have an ID", ENTITY_NAME, "idexists");
    //     }
    //     PreguntasDTO result = preguntasService.save(preguntasDTO);
    //     return ResponseEntity
    //         .created(new URI("/api/preguntas/" + result.getNro()))
    //         .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getNro().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PUT  /preguntas/:id} : Updates an existing preguntas.
    //  *
    //  * @param id the id of the preguntasDTO to save.
    //  * @param preguntasDTO the preguntasDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated preguntasDTO,
    //  * or with status {@code 400 (Bad Request)} if the preguntasDTO is not valid,
    //  * or with status {@code 500 (Internal Server Error)} if the preguntasDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PutMapping("/preguntas/{id}")
    // public ResponseEntity<PreguntasDTO> updatePreguntas(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody PreguntasDTO preguntasDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to update Preguntas : {}, {}", id, preguntasDTO);
    //     if (preguntasDTO.getNro() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, preguntasDTO.getNro())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!preguntasRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     PreguntasDTO result = preguntasService.save(preguntasDTO);
    //     return ResponseEntity
    //         .ok()
    //         .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, preguntasDTO.getNro().toString()))
    //         .body(result);
    // }

    // /**
    //  * {@code PATCH  /preguntas/:id} : Partial updates given fields of an existing preguntas, field will ignore if it is null
    //  *
    //  * @param id the id of the preguntasDTO to save.
    //  * @param preguntasDTO the preguntasDTO to update.
    //  * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated preguntasDTO,
    //  * or with status {@code 400 (Bad Request)} if the preguntasDTO is not valid,
    //  * or with status {@code 404 (Not Found)} if the preguntasDTO is not found,
    //  * or with status {@code 500 (Internal Server Error)} if the preguntasDTO couldn't be updated.
    //  * @throws URISyntaxException if the Location URI syntax is incorrect.
    //  */
    // @PatchMapping(value = "/preguntas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    // public ResponseEntity<PreguntasDTO> partialUpdatePreguntas(
    //     @PathVariable(value = "id", required = false) final Integer id,
    //     @RequestBody PreguntasDTO preguntasDTO
    // ) throws URISyntaxException {
    //     log.debug("REST request to partial update Preguntas partially : {}, {}", id, preguntasDTO);
    //     if (preguntasDTO.getNro() == null) {
    //         throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //     }
    //     if (!Objects.equals(id, preguntasDTO.getNro())) {
    //         throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //     }

    //     if (!preguntasRepository.existsById(id)) {
    //         throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //     }

    //     Optional<PreguntasDTO> result = preguntasService.partialUpdate(preguntasDTO);

    //     return ResponseUtil.wrapOrNotFound(
    //         result,
    //         HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, preguntasDTO.getNro().toString())
    //     );
    // }

    /**
     * {@code GET  /preguntas} : get all the preguntas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of preguntas in body.
     */
    @GetMapping("/preguntas")
    public ResponseEntity<List<PreguntasDTO>> getAllPreguntas(
        PreguntasCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Preguntas by criteria: {}", criteria);
        Page<PreguntasDTO> page = preguntasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /preguntas/count} : count all the preguntas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/preguntas/count")
    public ResponseEntity<Long> countPreguntas(PreguntasCriteria criteria) {
        log.debug("REST request to count Preguntas by criteria: {}", criteria);
        return ResponseEntity.ok().body(preguntasQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /preguntas/:id} : get the "id" preguntas.
     *
     * @param id the id of the preguntasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the preguntasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/preguntas/{id}")
    public ResponseEntity<PreguntasDTO> getPreguntas(@PathVariable Integer id) {
        log.debug("REST request to get Preguntas : {}", id);
        Optional<PreguntasDTO> preguntasDTO = preguntasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preguntasDTO);
    }

    // /**
    //  * {@code DELETE  /preguntas/:id} : delete the "id" preguntas.
    //  *
    //  * @param id the id of the preguntasDTO to delete.
    //  * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
    //  */
    // @DeleteMapping("/preguntas/{id}")
    // public ResponseEntity<Void> deletePreguntas(@PathVariable Integer id) {
    //     log.debug("REST request to delete Preguntas : {}", id);
    //     preguntasService.delete(id);
    //     return ResponseEntity
    //         .noContent()
    //         .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
    //         .build();
    // }
}
