package com.lxisoft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lxisoft.domain.AttendedExam;
import com.lxisoft.domain.AttendedOption;
import com.lxisoft.domain.Exam;
import com.lxisoft.domain.QstnOption;
import com.lxisoft.domain.Question;
import com.lxisoft.domain.User;
import com.lxisoft.domain.UserExtra;
import com.lxisoft.model.AttendedExamBean;

@Service
@Transactional
public class AttendedExamBeanService {

    private final Logger log = LoggerFactory.getLogger(AttendedExamBeanService.class);
    @Autowired
	private	UserExtraService extraService;
    @Autowired
	private AttendedExamService attendExamService;
	@Autowired
	private ExamService examService;

	public List<AttendedExamBean> getAttendedExamDataBean(String Exam_id) throws Exception
	{
		
		List<AttendedExamBean> list=new ArrayList<AttendedExamBean>();
		
		Exam exam=examService.findById(Exam_id);
		
		List<AttendedExam> attendList=attendExamService.findAllByExam(exam);
		
		List<User> candidates =extraService.findAll();
		
			
			for(AttendedExam atndexam:attendList)
			{
				AttendedExamBean bean=new AttendedExamBean();
				bean.setExamName(exam.getName());
				for(User user:candidates)
				{
					if(user.getId().equals(atndexam.getUserExtra().getId())) 
					{
						
							
						log.debug("candidate name"+user.getFirstName()+" "+user.getLastName());
						
						bean.setCandidate(user.getFirstName()+" "+user.getLastName());
						bean.setScore(atndexam.getScore());
						bean.setTotal(atndexam.getTotal());
						bean.setPercentage(atndexam.getPercentage());
						bean.setDate(atndexam.getDateTime().toLocalDate());
						if(atndexam.isResult()==true)
						{
							bean.setResult("Passed");
						}
						list.add(bean);
						
					}
					
				}
				
				
			}
			
			log.debug("list pdf"+list);
		return list;
	}
    
    

}
