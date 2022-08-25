package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.MovimientoCaja;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the MovimientoCaja entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimientoCajaRepository extends MongoRepository<MovimientoCaja, String> {}
