package com.ppanticona.fabio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.fabio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LibDiarioTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LibDiario.class);
        LibDiario libDiario1 = new LibDiario();
        libDiario1.setId("id1");
        LibDiario libDiario2 = new LibDiario();
        libDiario2.setId(libDiario1.getId());
        assertThat(libDiario1).isEqualTo(libDiario2);
        libDiario2.setId("id2");
        assertThat(libDiario1).isNotEqualTo(libDiario2);
        libDiario1.setId(null);
        assertThat(libDiario1).isNotEqualTo(libDiario2);
    }
}
