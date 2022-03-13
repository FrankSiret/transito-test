package com.franksiret.transito.service;

import com.franksiret.transito.domain.*; // for static metamodels
import com.franksiret.transito.domain.Artinc;
import com.franksiret.transito.repository.ArtincRepository;
import com.franksiret.transito.service.criteria.ArtincCriteria;
import com.franksiret.transito.service.dto.ArtincDTO;
import com.franksiret.transito.service.mapper.ArtincMapper;
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
 * Service for executing complex queries for {@link Artinc} entities in the database.
 * The main input is a {@link ArtincCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ArtincDTO} or a {@link Page} of {@link ArtincDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ArtincQueryService extends QueryService<Artinc> {

    private final Logger log = LoggerFactory.getLogger(ArtincQueryService.class);

    private final ArtincRepository artincRepository;

    private final ArtincMapper artincMapper;

    public ArtincQueryService(ArtincRepository artincRepository, ArtincMapper artincMapper) {
        this.artincRepository = artincRepository;
        this.artincMapper = artincMapper;
    }

    /**
     * Return a {@link List} of {@link ArtincDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ArtincDTO> findByCriteria(ArtincCriteria criteria) {
        criteria.descrip().setSpecified(true);
        log.debug("find by criteria : {}", criteria);
        final Specification<Artinc> specification = createSpecification(criteria);
        return artincMapper.toDto(artincRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ArtincDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ArtincDTO> findByCriteria(ArtincCriteria criteria, Pageable page) {
        criteria.descrip().setSpecified(true);
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Artinc> specification = createSpecification(criteria);
        return artincRepository.findAll(specification, page).map(artincMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ArtincCriteria criteria) {
        criteria.descrip().setSpecified(true);
        log.debug("count by criteria : {}", criteria);
        final Specification<Artinc> specification = createSpecification(criteria);
        return artincRepository.count(specification);
    }

    /**
     * Function to convert {@link ArtincCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Artinc> createSpecification(ArtincCriteria criteria) {
        Specification<Artinc> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getArtinc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArtinc(), Artinc_.id));
            }
            if (criteria.getPelig() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPelig(), Artinc_.pelig));
            }
            if (criteria.getDescrip() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescrip(), Artinc_.descrip));
            }
            if (criteria.getPreguntaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPreguntaId(),
                            root -> root.join(Artinc_.preguntas, JoinType.LEFT).get(Preguntas_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
