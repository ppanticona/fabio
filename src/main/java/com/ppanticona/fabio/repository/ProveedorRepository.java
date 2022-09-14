package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.Proveedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Proveedor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProveedorRepository extends MongoRepository<Proveedor, String> {
    Proveedor findBytipDocProvAndNroDocProv(String tipDocProv, String nroDocProv);
}
