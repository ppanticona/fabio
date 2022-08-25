package com.ppanticona.fabio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.fabio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RegComprasTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegCompras.class);
        RegCompras regCompras1 = new RegCompras();
        regCompras1.setId("id1");
        RegCompras regCompras2 = new RegCompras();
        regCompras2.setId(regCompras1.getId());
        assertThat(regCompras1).isEqualTo(regCompras2);
        regCompras2.setId("id2");
        assertThat(regCompras1).isNotEqualTo(regCompras2);
        regCompras1.setId(null);
        assertThat(regCompras1).isNotEqualTo(regCompras2);
    }
}
