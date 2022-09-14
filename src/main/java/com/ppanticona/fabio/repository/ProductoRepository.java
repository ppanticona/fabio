package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Producto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    Page<Producto> findAllByIndDelAndEstado(boolean indDel, String estado, Pageable pageable);
    Page<Producto> findAllByDescripcionRegexAndIndDelIsFalseAndEstado(String descripcion, String estado, Pageable pageable);
    Page<Producto> findAllByCodProductoAndEstadoAndIndDelIsFalse(String codProd, String estado, Pageable pageable);
}
