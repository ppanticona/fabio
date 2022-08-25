package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.PlanContable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the PlanContable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanContableRepository extends MongoRepository<PlanContable, String> {}
