package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.OperaContable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OperaContable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperaContableRepository extends MongoRepository<OperaContable, String> {
    OperaContable findByDescOpera(String descOpera);
}
