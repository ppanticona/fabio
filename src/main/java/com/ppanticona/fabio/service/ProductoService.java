package com.ppanticona.fabio.service;

import com.ppanticona.fabio.domain.Producto;
import java.util.Map;

public interface ProductoService {
    public Producto registrarProducto(Producto productoBean);

    public String obtenerProductosInventario(int page, int size);
}
