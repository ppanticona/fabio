package com.ppanticona.fabio.repository;

import com.ppanticona.fabio.domain.MovimientoProducto;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the MovimientoProducto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimientoProductoRepository extends MongoRepository<MovimientoProducto, String> {
    @Aggregation(pipeline = { "{$match: { 'producto.id': ?0, 'tip_movimiento' : ?1}}", "{$group: { _id: '', 'total': {$sum: '$cnt'}}}" })
    public String sumCantByProductoAndTipMovimiento(String productoid, String tipMovimiento);
}
