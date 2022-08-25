package com.ppanticona.fabio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.fabio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MovimientoProductoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovimientoProducto.class);
        MovimientoProducto movimientoProducto1 = new MovimientoProducto();
        movimientoProducto1.setId("id1");
        MovimientoProducto movimientoProducto2 = new MovimientoProducto();
        movimientoProducto2.setId(movimientoProducto1.getId());
        assertThat(movimientoProducto1).isEqualTo(movimientoProducto2);
        movimientoProducto2.setId("id2");
        assertThat(movimientoProducto1).isNotEqualTo(movimientoProducto2);
        movimientoProducto1.setId(null);
        assertThat(movimientoProducto1).isNotEqualTo(movimientoProducto2);
    }
}
