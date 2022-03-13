package com.franksiret.transito.repository;

import com.franksiret.transito.domain.Categorias;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Categorias entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer>, JpaSpecificationExecutor<Categorias> {}
