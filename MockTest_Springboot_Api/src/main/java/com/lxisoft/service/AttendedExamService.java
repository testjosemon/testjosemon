package com.lxisoft.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.domain.AttendedExam;

import com.lxisoft.domain.Question;
import com.lxisoft.domain.User;
import com.lxisoft.domain.UserExtra;

import com.lxisoft.domain.Exam;

import com.lxisoft.repository.AttendedExamRepository;

@Service
@Transactional
public class AttendedExamService {

    private final Logger log = LoggerFactory.getLogger(AttendedExamService.class);
    @Autowired
    AttendedExamRepository attendedRepo;
    
    public AttendedExam attend(AttendedExam attendedExam,int score,int total) {
		float percentage=(score*100)/total;
		attendedExam.setScore(score);
		attendedExam.setTotal(total);
		attendedExam.setPercentage(percentage);
		if(percentage>=45)
		{
			attendedExam.setResult(true);
		}
		else {
			attendedExam.setResult(false);
		}
		log.debug("exam attended- "+attendedExam);
		return attendedExam;
	}


	public void save(AttendedExam attendedExam) {
		
		attendedRepo.save(attendedExam);
		log.debug("exam attended saved in database- "+attendedExam);
	}
	
	public List<AttendedExam> findAll()
	{
		return attendedRepo.findAll();
	}
	
	public List<AttendedExam> findAllByExam(Exam exam)
	{
		return attendedRepo.findAllByExam(exam);
	}
	
	public List<AttendedExam> findAllByUserExtra(UserExtra user)
	{
		return attendedRepo.findAllByUserExtra(user);
	}

	public AttendedExam findById(String aExamId) {
		
		long id=Integer.parseInt(aExamId);
		
		Optional<AttendedExam> optional=attendedRepo.findById(id);
		AttendedExam atndexam=optional.get();
		return atndexam;
		
	}
	
	public void deleteById(String aExamId) {
		long id=Integer.parseInt(aExamId);
		attendedRepo.deleteById(id);
		log.debug("cancelled exam has been removed, exam id:- "+id);
	}

}
