package com.ppanticona.fabio.component;

import com.ppanticona.fabio.domain.Producto;
import com.ppanticona.fabio.model.ProductoInventarioModel;
import org.springframework.stereotype.Component;

@Component("productoInventarioConverter")
public class ProductoInventarioConverter {

    public Producto convertModel2Entity(ProductoInventarioModel productoInventarioModel) {
        Producto producto = new Producto();

        producto.setId(productoInventarioModel.getId());
        producto.setCodProducto(productoInventarioModel.getCodProducto());
        producto.setCodQr(productoInventarioModel.getCodQr());
        producto.setCodBarra(productoInventarioModel.getCodBarra());
        producto.setDescripcion(productoInventarioModel.getDescripcion());
        producto.setDetalleDesc(productoInventarioModel.getDetalleDesc());
        producto.setValor(productoInventarioModel.getValor());
        producto.setFecIniVig(productoInventarioModel.getFecIniVig());
        producto.setFecFinVig(productoInventarioModel.getFecFinVig());
        producto.setCostoProd(productoInventarioModel.getCostoProd());
        producto.setUrlImage(productoInventarioModel.getUrlImage());
        producto.setEstado(productoInventarioModel.getEstado());
        producto.setVersion(productoInventarioModel.getVersion());
        producto.setIndDel(productoInventarioModel.getIndDel());
        producto.setFecCrea(productoInventarioModel.getFecCrea());
        producto.setUsuCrea(productoInventarioModel.getUsuCrea());
        producto.setIpCrea(productoInventarioModel.getIpCrea());
        producto.setFecModif(productoInventarioModel.getFecModif());
        producto.setUsuModif(productoInventarioModel.getUsuModif());
        producto.setIpModif(productoInventarioModel.getIpModif());

        return producto;
    }

    public ProductoInventarioModel convertEntity2Model(Producto producto) {
        ProductoInventarioModel productoInventarioModel = new ProductoInventarioModel();

        productoInventarioModel.setId(producto.getId());
        productoInventarioModel.setCodProducto(producto.getCodProducto());
        productoInventarioModel.setCodQr(producto.getCodQr());
        productoInventarioModel.setCodBarra(producto.getCodBarra());
        productoInventarioModel.setDescripcion(producto.getDescripcion());
        productoInventarioModel.setDetalleDesc(producto.getDetalleDesc());
        productoInventarioModel.setValor(producto.getValor());
        productoInventarioModel.setFecIniVig(producto.getFecIniVig());
        productoInventarioModel.setFecFinVig(producto.getFecFinVig());
        productoInventarioModel.setCostoProd(producto.getCostoProd());
        productoInventarioModel.setUrlImage(producto.getUrlImage());
        productoInventarioModel.setEstado(producto.getEstado());
        productoInventarioModel.setVersion(producto.getVersion());
        productoInventarioModel.setIndDel(producto.getIndDel());
        productoInventarioModel.setFecCrea(producto.getFecCrea());
        productoInventarioModel.setUsuCrea(producto.getUsuCrea());
        productoInventarioModel.setIpCrea(producto.getIpCrea());
        productoInventarioModel.setFecModif(producto.getFecModif());
        productoInventarioModel.setUsuModif(producto.getUsuModif());
        productoInventarioModel.setIpModif(producto.getIpModif());

        return productoInventarioModel;
    }
}
