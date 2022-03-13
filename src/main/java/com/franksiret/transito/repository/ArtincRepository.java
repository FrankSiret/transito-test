package com.franksiret.transito.repository;

import com.franksiret.transito.domain.Artinc;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Artinc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtincRepository extends JpaRepository<Artinc, Integer>, JpaSpecificationExecutor<Artinc> {}
