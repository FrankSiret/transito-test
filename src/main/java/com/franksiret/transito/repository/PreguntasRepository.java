package com.franksiret.transito.repository;

import com.franksiret.transito.domain.Preguntas;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Preguntas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Integer>, JpaSpecificationExecutor<Preguntas> {}
