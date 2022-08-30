package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.DetalleOperaContable;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DetalleOperaContable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetalleOperaContableRepository extends MongoRepository<DetalleOperaContable, String> {
    List<DetalleOperaContable> findByOperaContableId(String id);
}
