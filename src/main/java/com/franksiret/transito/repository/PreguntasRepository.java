package com.franksiret.transito.repository;

import com.franksiret.transito.domain.Preguntas;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Preguntas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Integer>, JpaSpecificationExecutor<Preguntas> {
    @Query(
        nativeQuery = true,
        value = "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '1') as p1 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '2') as p2 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '3') as p3 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '4') as p4 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '5') as p5 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '6') as p6 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '7') as p7 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '8') as p8 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '9') as p9 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '10') as p10 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '11') as p11 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '12') as p12 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '13') as p13 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '14') as p14 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '15') as p15 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '16') as p16 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '17') as p17 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '18') as p18 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '19') as p19 order by random() limit 1) union\n" +
        "(select * from (select * from preguntas p where p.usada = true and p.tematica_id = '20') as p20 order by random() limit 1)\n" +
        "order by tematica_id "
    )
    List<Preguntas> findByTematicaRand();
}
