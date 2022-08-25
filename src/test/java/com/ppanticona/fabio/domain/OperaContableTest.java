package com.ppanticona.fabio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.fabio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OperaContableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperaContable.class);
        OperaContable operaContable1 = new OperaContable();
        operaContable1.setId("id1");
        OperaContable operaContable2 = new OperaContable();
        operaContable2.setId(operaContable1.getId());
        assertThat(operaContable1).isEqualTo(operaContable2);
        operaContable2.setId("id2");
        assertThat(operaContable1).isNotEqualTo(operaContable2);
        operaContable1.setId(null);
        assertThat(operaContable1).isNotEqualTo(operaContable2);
    }
}
