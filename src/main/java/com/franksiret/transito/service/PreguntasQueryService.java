package com.franksiret.transito.service;

import com.franksiret.transito.domain.*; // for static metamodels
import com.franksiret.transito.domain.Preguntas;
import com.franksiret.transito.repository.PreguntasRepository;
import com.franksiret.transito.service.criteria.PreguntasCriteria;
import com.franksiret.transito.service.dto.PreguntasDTO;
import com.franksiret.transito.service.mapper.PreguntasMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Preguntas} entities in the database.
 * The main input is a {@link PreguntasCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PreguntasDTO} or a {@link Page} of {@link PreguntasDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PreguntasQueryService extends QueryService<Preguntas> {

    private final Logger log = LoggerFactory.getLogger(PreguntasQueryService.class);

    private final PreguntasRepository preguntasRepository;

    private final PreguntasMapper preguntasMapper;

    public PreguntasQueryService(PreguntasRepository preguntasRepository, PreguntasMapper preguntasMapper) {
        this.preguntasRepository = preguntasRepository;
        this.preguntasMapper = preguntasMapper;
    }

    /**
     * Return a {@link List} of {@link PreguntasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PreguntasDTO> findByCriteria(PreguntasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Preguntas> specification = createSpecification(criteria);
        return preguntasMapper.toDto(preguntasRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PreguntasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PreguntasDTO> findByCriteria(PreguntasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Preguntas> specification = createSpecification(criteria);
        return preguntasRepository.findAll(specification, page).map(preguntasMapper::toDtoId);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PreguntasCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Preguntas> specification = createSpecification(criteria);
        return preguntasRepository.count(specification);
    }

    /**
     * Function to convert {@link PreguntasCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Preguntas> createSpecification(PreguntasCriteria criteria) {
        Specification<Preguntas> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getNro() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNro(), Preguntas_.id));
            }
            if (criteria.getTexto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTexto(), Preguntas_.texto));
            }
            if (criteria.getResp1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResp1(), Preguntas_.resp1));
            }
            if (criteria.getResp2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResp2(), Preguntas_.resp2));
            }
            if (criteria.getResp3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResp3(), Preguntas_.resp3));
            }
            if (criteria.getCorrecta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCorrecta(), Preguntas_.correcta));
            }
            if (criteria.getUsada() != null) {
                specification = specification.and(buildSpecification(criteria.getUsada(), Preguntas_.usada));
            }
            if (criteria.getPuesto() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPuesto(), Preguntas_.puesto));
            }
            if (criteria.getFotoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getFotoId(), root -> root.join(Preguntas_.foto, JoinType.LEFT).get(Fotos_.id))
                    );
            }
            if (criteria.getTematicaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTematicaId(),
                            root -> root.join(Preguntas_.tematica, JoinType.LEFT).get(Tematicas_.id)
                        )
                    );
            }
            if (criteria.getArtincId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getArtincId(), root -> root.join(Preguntas_.artinc, JoinType.LEFT).get(Artinc_.id))
                    );
            }
        }
        return specification;
    }

    @Transactional(readOnly = true)
    public List<PreguntasDTO> findTest() {
        log.debug("find test");
        return preguntasMapper.toDto(preguntasRepository.findByTematicaRand());
    }
}
