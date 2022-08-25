package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.DetalleOrden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DetalleOrden entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetalleOrdenRepository extends MongoRepository<DetalleOrden, String> {}
