package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.RegCompras;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the RegCompras entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegComprasRepository extends MongoRepository<RegCompras, String> {}
