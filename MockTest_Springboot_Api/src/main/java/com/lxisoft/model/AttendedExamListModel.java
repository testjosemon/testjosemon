package com.lxisoft.model;
/**
 *  AttendedExamListModel  for transferring the data to Front end
 */

import java.util.List;

import com.lxisoft.domain.AttendedExam;
import com.lxisoft.domain.Exam;
import com.lxisoft.domain.User;

public class AttendedExamListModel {
	
	
	Exam exam ;
	List<AttendedExamModel> attendList;
	
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public List<AttendedExamModel> getAttendList() {
		return attendList;
	}
	public void setAttendList(List<AttendedExamModel> examlist) {
		this.attendList = examlist;
	}
	

}
