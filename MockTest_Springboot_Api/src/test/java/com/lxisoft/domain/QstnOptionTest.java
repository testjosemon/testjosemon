package com.lxisoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class QstnOptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QstnOption.class);
        QstnOption qstnOption1 = new QstnOption();
        qstnOption1.setId(1L);
        QstnOption qstnOption2 = new QstnOption();
        qstnOption2.setId(qstnOption1.getId());
        assertThat(qstnOption1).isEqualTo(qstnOption2);
        qstnOption2.setId(2L);
        assertThat(qstnOption1).isNotEqualTo(qstnOption2);
        qstnOption1.setId(null);
        assertThat(qstnOption1).isNotEqualTo(qstnOption2);
    }
}
