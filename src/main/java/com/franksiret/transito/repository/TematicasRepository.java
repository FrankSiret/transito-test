package com.franksiret.transito.repository;

import com.franksiret.transito.domain.Tematicas;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Tematicas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TematicasRepository extends JpaRepository<Tematicas, Integer>, JpaSpecificationExecutor<Tematicas> {}
