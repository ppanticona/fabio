package com.ppanticona.fabio.service;

import java.util.Map;

public interface MovimientoProductoService {
    public String registrarIngreso(Map datosMap);

    public String registrarSalida(Map datosMap);
}
