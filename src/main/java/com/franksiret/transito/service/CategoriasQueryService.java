package com.franksiret.transito.service;

import com.franksiret.transito.domain.*; // for static metamodels
import com.franksiret.transito.domain.Categorias;
import com.franksiret.transito.repository.CategoriasRepository;
import com.franksiret.transito.service.criteria.CategoriasCriteria;
import com.franksiret.transito.service.dto.CategoriasDTO;
import com.franksiret.transito.service.mapper.CategoriasMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Categorias} entities in the database.
 * The main input is a {@link CategoriasCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CategoriasDTO} or a {@link Page} of {@link CategoriasDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CategoriasQueryService extends QueryService<Categorias> {

    private final Logger log = LoggerFactory.getLogger(CategoriasQueryService.class);

    private final CategoriasRepository categoriasRepository;

    private final CategoriasMapper categoriasMapper;

    public CategoriasQueryService(CategoriasRepository categoriasRepository, CategoriasMapper categoriasMapper) {
        this.categoriasRepository = categoriasRepository;
        this.categoriasMapper = categoriasMapper;
    }

    /**
     * Return a {@link List} of {@link CategoriasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CategoriasDTO> findByCriteria(CategoriasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Categorias> specification = createSpecification(criteria);
        return categoriasMapper.toDto(categoriasRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CategoriasDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CategoriasDTO> findByCriteria(CategoriasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Categorias> specification = createSpecification(criteria);
        return categoriasRepository.findAll(specification, page).map(categoriasMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CategoriasCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Categorias> specification = createSpecification(criteria);
        return categoriasRepository.count(specification);
    }

    /**
     * Function to convert {@link CategoriasCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Categorias> createSpecification(CategoriasCriteria criteria) {
        Specification<Categorias> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getCodigo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCodigo(), Categorias_.id));
            }
            if (criteria.getTipo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipo(), Categorias_.tipo));
            }
            if (criteria.getDescrip() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescrip(), Categorias_.descrip));
            }
        }
        return specification;
    }
}
