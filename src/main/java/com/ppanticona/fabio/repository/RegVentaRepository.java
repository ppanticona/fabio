package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.RegVenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the RegVenta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegVentaRepository extends MongoRepository<RegVenta, String> {}
