package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.Secuencias;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Secuencias entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecuenciasRepository extends MongoRepository<Secuencias, String> {
    Secuencias findBySeqid(String nameSeq);
}
