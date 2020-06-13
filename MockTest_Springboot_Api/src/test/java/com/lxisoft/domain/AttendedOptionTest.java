package com.lxisoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class AttendedOptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttendedOption.class);
        AttendedOption attendedOption1 = new AttendedOption();
        attendedOption1.setId(1L);
        AttendedOption attendedOption2 = new AttendedOption();
        attendedOption2.setId(attendedOption1.getId());
        assertThat(attendedOption1).isEqualTo(attendedOption2);
        attendedOption2.setId(2L);
        assertThat(attendedOption1).isNotEqualTo(attendedOption2);
        attendedOption1.setId(null);
        assertThat(attendedOption1).isNotEqualTo(attendedOption2);
    }
}
