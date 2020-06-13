package com.lxisoft.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.lxisoft.domain.User;
/**
 *  AttendedExamModel  for transferring the data to Front end
 */
public class AttendedExamModel {

	private String examName;
	
	private int score;
	
	private int total;
	
	private boolean result=false;
	
	private float percentage;
	
	private String dateTime;
	
	private Long ExamId;
	
	private User user;
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getExamId() {
		return ExamId;
	}

	public void setExamId(Long examId) {
		ExamId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}



}
