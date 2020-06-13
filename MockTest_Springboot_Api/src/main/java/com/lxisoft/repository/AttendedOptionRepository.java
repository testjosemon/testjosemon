package com.lxisoft.repository;

import com.lxisoft.domain.AttendedExam;
import com.lxisoft.domain.AttendedOption;
import com.lxisoft.domain.Question;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AttendedOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttendedOptionRepository extends JpaRepository<AttendedOption, Long> {

	List<AttendedOption> findAllByAttendedExam(AttendedExam attendedExam);

	AttendedOption findByAttendedExamAndQuestion(AttendedExam attendedExam, Question question);
}
