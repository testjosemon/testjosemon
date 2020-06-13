package com.lxisoft.model;

import java.time.LocalDate;


/**
 * AttendedExamBean for setting model for generate jasper report using bean
 */
public class AttendedExamBean {
	

	private String examName;
	
	private String candidate;
	
	private int score;
	
	private int total;
	
	private String result="Failed";
	
	private float percentage;
	
	private LocalDate date;
	
	

	public String getExamName() {
		return examName;
	}



	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getCandidate() {
		return candidate;
	}



	public void setCandidate(String candidate) {
		this.candidate = candidate;
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



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	public float getPercentage() {
		return percentage;
	}



	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}


	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public AttendedExamBean(String examName, String candidate, int score, int total, String result, float percentage,
		LocalDate date) {
		super();
		this.examName = examName;
		this.candidate = candidate;
		this.score = score;
		this.total = total;
		this.result = result;
		this.percentage = percentage;
		this.date = date;
	}



	public AttendedExamBean() {
		
	}

	

	
	
	

}
