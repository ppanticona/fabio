package com.ppanticona.fabio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.fabio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetalleOperaContableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetalleOperaContable.class);
        DetalleOperaContable detalleOperaContable1 = new DetalleOperaContable();
        detalleOperaContable1.setId("id1");
        DetalleOperaContable detalleOperaContable2 = new DetalleOperaContable();
        detalleOperaContable2.setId(detalleOperaContable1.getId());
        assertThat(detalleOperaContable1).isEqualTo(detalleOperaContable2);
        detalleOperaContable2.setId("id2");
        assertThat(detalleOperaContable1).isNotEqualTo(detalleOperaContable2);
        detalleOperaContable1.setId(null);
        assertThat(detalleOperaContable1).isNotEqualTo(detalleOperaContable2);
    }
}
