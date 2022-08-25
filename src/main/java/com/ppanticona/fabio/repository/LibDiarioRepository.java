package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.LibDiario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the LibDiario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibDiarioRepository extends MongoRepository<LibDiario, String> {}
