package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Orden entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdenRepository extends MongoRepository<Orden, String> {
    Page<Orden> findAllByTipOrdenAndEstadoAndIndDelIsFalse(String tipOrden, String estado, Pageable pageable);
}
