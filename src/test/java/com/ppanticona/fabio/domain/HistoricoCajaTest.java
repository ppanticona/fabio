package com.ppanticona.fabio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.fabio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistoricoCajaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoCaja.class);
        HistoricoCaja historicoCaja1 = new HistoricoCaja();
        historicoCaja1.setId("id1");
        HistoricoCaja historicoCaja2 = new HistoricoCaja();
        historicoCaja2.setId(historicoCaja1.getId());
        assertThat(historicoCaja1).isEqualTo(historicoCaja2);
        historicoCaja2.setId("id2");
        assertThat(historicoCaja1).isNotEqualTo(historicoCaja2);
        historicoCaja1.setId(null);
        assertThat(historicoCaja1).isNotEqualTo(historicoCaja2);
    }
}
