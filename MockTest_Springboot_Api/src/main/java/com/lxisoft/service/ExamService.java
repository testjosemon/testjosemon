package com.lxisoft.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.domain.Exam;
import com.lxisoft.domain.QstnOption;
import com.lxisoft.domain.Question;
import com.lxisoft.repository.ExamRepository;
import com.lxisoft.repository.QuestionRepository;

/**
 * Exam Service
 */

@Service
@Transactional
public class ExamService {

    private final Logger log = LoggerFactory.getLogger(ExamService.class);
    @Autowired
    ExamRepository examRepo;
	@Autowired
	QuestionRepository qstnRepo;
	@Autowired
	QuestionService qstService;
	
	/**
     * Find all exam from database
     * @return List<Exam>.
     */
	public List<Exam> findAll() {
		return examRepo.findAll();
	}	
	
	
	/**
     * Find an Exam By its Id
     * @param ExamId
     * @return Exam
     */
	public Exam findById(String eId) 
	{
		long id=Integer.parseInt(eId);
		Optional<Exam> optional=examRepo.findById(id);
		return optional.get();
	}
	
	/**
     * Check an Exam is present or not in database by its Id.
     * @param ExamId
     * @return boolean
     */
	public boolean findByIdCheck(String eId) 
	{
		long id=Integer.parseInt(eId);
		Optional<Exam> optional=examRepo.findById(id);
		return optional.isPresent();
	}

	/**
     * shuffling questions and Save an Exam in to the database and also check requested question count is greater than questions in database and retun a flag.
     * @param Exam
     * @return flag
     */
	public boolean save_exam(Exam exam) 
	{
		Set<Question> finalQstns = new HashSet<Question>(); 
		List<Question> qstns=qstService.findAll();
		Collections.shuffle(qstns);
		boolean flag=false;
		int neededCount=exam.getCount(),qstncount=0,c=0;
		String level=exam.getLevel();
		for(Question qstn:qstns)
		{
			if(qstn.getLevel().equals(level))
				qstncount++;
		}
		if(qstncount>=neededCount)
		{
			for(Question qstn:qstns)
			{
				if(qstn.getLevel().equals(level) && (c<neededCount))
				{
					c++;
					finalQstns.add(qstn);
				}
			}
			flag=true;
		}
		if(flag==true)
		{
			exam.setQuestions(finalQstns);
			examRepo.save(exam);
		}
		return flag;
	}
	
	
	/**
     * update an exam
     * @param Exam
     */
	public void update(Exam exam) {
		examRepo.save(exam);
	}
	
	
	/**
     * Find Active Exams from the database
     * @return Set<Exam>
     */
	public Set<Exam> findActiveExams()
	{
		List<Exam> exam_list=findAll();
		Set<Exam> final_list = new HashSet<Exam>();
		for(Exam e:exam_list)
		{
			if(e.isIsActive()==true)
			{
				final_list.add(e);
			}
		}
		return final_list;
	}
}
