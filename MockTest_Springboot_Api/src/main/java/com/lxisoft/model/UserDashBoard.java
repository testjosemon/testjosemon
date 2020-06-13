package com.lxisoft.model;

import java.util.List;

import com.lxisoft.domain.AttendedExam;
import com.lxisoft.domain.UserExtra;

/**
 * userDashboard model  for transferring the data to Front end
 */

public class UserDashBoard {
	
	private UserExtra currentUser;
	private long userId;
<<<<<<< HEAD
	private List<AttendedExamModel> attendedExamList;
	

=======
	private List<AttendedExam> attendedExamList;
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
	
	
	public UserExtra getCurrentUser() {
		return currentUser;
	}


	public void setCurrentUser(UserExtra currentUser) {
		this.currentUser = currentUser;
	}


<<<<<<< HEAD
	


=======
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


<<<<<<< HEAD
	public List<AttendedExamModel> getAttendedExamList() {
=======
	public List<AttendedExam> getAttendedExamList() {
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
		return attendedExamList;
	}


<<<<<<< HEAD
	public void setAttendedExamList(List<AttendedExamModel> examlist) {
		this.attendedExamList = examlist;
	}


	public UserDashBoard(UserExtra currentUser, long userId, List<AttendedExamModel> attendedExamList) {
=======
	public void setAttendedExamList(List<AttendedExam> attendedExamList) {
		this.attendedExamList = attendedExamList;
	}


	public UserDashBoard(UserExtra currentUser, long userId, List<AttendedExam> attendedExamList) {
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
		super();
		this.currentUser = currentUser;
		this.userId = userId;
		this.attendedExamList = attendedExamList;
	}

<<<<<<< HEAD
=======

>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
	public UserDashBoard() {
		super();
		
	}
	
	
}
