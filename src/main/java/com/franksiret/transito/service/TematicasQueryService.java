package com.franksiret.transito.service;

import com.franksiret.transito.domain.*; // for static metamodels
import com.franksiret.transito.domain.Tematicas;
import com.franksiret.transito.repository.TematicasRepository;
import com.franksiret.transito.service.criteria.TematicasCriteria;
import com.franksiret.transito.service.dto.TematicasDTO;
import com.franksiret.transito.service.mapper.TematicasMapper;
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
 * Service for executing complex queries for {@link Tematicas} entities in the database.
 * The main input is a {@link TematicasCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TematicasDTO} or a {@link Page} of {@link TematicasDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TematicasQueryService extends QueryService<Tematicas> {

    private final Logger log = LoggerFactory.getLogger(TematicasQueryService.class);

    private final TematicasRepository tematicasRepository;

    private final TematicasMapper tematicasMapper;

    public TematicasQueryService(TematicasRepository tematicasRepository, TematicasMapper tematicasMapper) {
        this.tematicasRepository = tematicasRepository;
        this.tematicasMapper = tematicasMapper;
    }

    /**
     * Return a {@link List} of {@link TematicasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TematicasDTO> findByCriteria(TematicasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Tematicas> specification = createSpecification(criteria);
        return tematicasMapper.toDto(tematicasRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TematicasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TematicasDTO> findByCriteria(TematicasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Tematicas> specification = createSpecification(criteria);
        return tematicasRepository.findAll(specification, page).map(tematicasMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TematicasCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Tematicas> specification = createSpecification(criteria);
        return tematicasRepository.count(specification);
    }

    /**
     * Function to convert {@link TematicasCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Tematicas> createSpecification(TematicasCriteria criteria) {
        Specification<Tematicas> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Tematicas_.id));
            }
            if (criteria.getDescrip() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescrip(), Tematicas_.descrip));
            }
            if (criteria.getCantidad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCantidad(), Tematicas_.cantidad));
            }
            if (criteria.getPreguntaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPreguntaId(),
                            root -> root.join(Tematicas_.preguntas, JoinType.LEFT).get(Preguntas_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
