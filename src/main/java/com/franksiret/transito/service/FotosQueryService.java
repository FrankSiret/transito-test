package com.franksiret.transito.service;

import com.franksiret.transito.domain.*; // for static metamodels
import com.franksiret.transito.domain.Fotos;
import com.franksiret.transito.repository.FotosRepository;
import com.franksiret.transito.service.criteria.FotosCriteria;
import com.franksiret.transito.service.dto.FotosDTO;
import com.franksiret.transito.service.mapper.FotosMapper;
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
 * Service for executing complex queries for {@link Fotos} entities in the database.
 * The main input is a {@link FotosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FotosDTO} or a {@link Page} of {@link FotosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FotosQueryService extends QueryService<Fotos> {

    private final Logger log = LoggerFactory.getLogger(FotosQueryService.class);

    private final FotosRepository fotosRepository;

    private final FotosMapper fotosMapper;

    public FotosQueryService(FotosRepository fotosRepository, FotosMapper fotosMapper) {
        this.fotosRepository = fotosRepository;
        this.fotosMapper = fotosMapper;
    }

    /**
     * Return a {@link List} of {@link FotosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FotosDTO> findByCriteria(FotosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Fotos> specification = createSpecification(criteria);
        return fotosMapper.toDto(fotosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FotosDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FotosDTO> findByCriteria(FotosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Fotos> specification = createSpecification(criteria);
        return fotosRepository.findAll(specification, page).map(fotosMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FotosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Fotos> specification = createSpecification(criteria);
        return fotosRepository.count(specification);
    }

    /**
     * Function to convert {@link FotosCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Fotos> createSpecification(FotosCriteria criteria) {
        Specification<Fotos> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getNro() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNro(), Fotos_.id));
            }
            if (criteria.getPreguntaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getPreguntaId(), root -> root.join(Fotos_.preguntas, JoinType.LEFT).get(Preguntas_.id))
                    );
            }
        }
        return specification;
    }
}
