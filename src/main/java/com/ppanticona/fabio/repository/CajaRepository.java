package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.Caja;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Caja entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CajaRepository extends MongoRepository<Caja, String> {}
