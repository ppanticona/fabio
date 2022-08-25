package com.ppanticona.fabio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ppanticona.fabio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanContableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanContable.class);
        PlanContable planContable1 = new PlanContable();
        planContable1.setId("id1");
        PlanContable planContable2 = new PlanContable();
        planContable2.setId(planContable1.getId());
        assertThat(planContable1).isEqualTo(planContable2);
        planContable2.setId("id2");
        assertThat(planContable1).isNotEqualTo(planContable2);
        planContable1.setId(null);
        assertThat(planContable1).isNotEqualTo(planContable2);
    }
}
