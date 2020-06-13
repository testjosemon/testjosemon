package com.lxisoft.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxisoft.domain.AttendedExam;
import com.lxisoft.domain.AttendedOption;
import com.lxisoft.domain.QstnOption;
import com.lxisoft.domain.Question;
import com.lxisoft.repository.AttendedOptionRepository;
import com.lxisoft.web.ExamController;

@Service
public class AttendedOptionService {
	@Autowired
	private OptionService optService;
	@Autowired
	private AttendedOptionRepository attendOptRepo;
	
	private final Logger log = LoggerFactory.getLogger(AttendedOptionService.class);

	public void attendOptionInitial( Question question, AttendedExam attendedExam) {

		AttendedOption attendedOpt=new AttendedOption();
		attendedOpt.setAttendedOpt(null);
		attendedOpt.setAttendedAnswer(null);
		attendedOpt.setAttendedExam(attendedExam);
		attendedOpt.setQuestion(question);
		log.debug("an attended option save: "+attendedOpt);
		attendOptRepo.save(attendedOpt);

	}
	

	public List<AttendedOption> findAllByAttendedExam(AttendedExam attendedExam) {
		return attendOptRepo.findAllByAttendedExam(attendedExam);
	}

	public void attendOptionUpdate(String optionid, Question question, AttendedExam attendedExam) {
		AttendedOption attendedOption=attendOptRepo.findByAttendedExamAndQuestion(attendedExam,question);
		QstnOption qstnOption;
		if(!optionid.equals("0"))
		{
			qstnOption=optService.findById(optionid);
			attendedOption.setAttendedOpt(qstnOption.getOption());
			attendedOption.setAttendedAnswer(qstnOption.isIsAnswer());
			attendOptRepo.save(attendedOption);
			log.debug("attendedOption updated after qstn attended:- "+attendedOption);
		}
		
	}


	public int examScore(AttendedExam attendedExam) {
		List<AttendedOption> attendedOptions=findAllByAttendedExam(attendedExam);
		int score=0;
		for(AttendedOption aOption:attendedOptions)
		{
			if((aOption.isAttendedAnswer()!=null) && (aOption.isAttendedAnswer()==true))
			{
				score+=1;
			}
		}
		return score;
	}
	
	public void clearOption(String id)
	{
		Optional<AttendedOption> optional=attendOptRepo.findById((long) Integer.parseInt(id));
		AttendedOption attendedOpt=optional.get();
		log.debug("before clearing attnded option:- "+attendedOpt);
		attendedOpt.setAttendedOpt(null);
		attendedOpt.setAttendedAnswer(null);
		attendOptRepo.save(attendedOpt);
		log.debug("after the clearing of attnded option:- "+attendedOpt);
	}
	
}



