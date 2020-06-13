package com.lxisoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class AttendedExamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttendedExam.class);
        AttendedExam attendedExam1 = new AttendedExam();
        attendedExam1.setId(1L);
        AttendedExam attendedExam2 = new AttendedExam();
        attendedExam2.setId(attendedExam1.getId());
        assertThat(attendedExam1).isEqualTo(attendedExam2);
        attendedExam2.setId(2L);
        assertThat(attendedExam1).isNotEqualTo(attendedExam2);
        attendedExam1.setId(null);
        assertThat(attendedExam1).isNotEqualTo(attendedExam2);
    }
}
