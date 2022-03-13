package com.franksiret.transito.repository;

import com.franksiret.transito.domain.Fotos;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Fotos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FotosRepository extends JpaRepository<Fotos, Integer>, JpaSpecificationExecutor<Fotos> {}
