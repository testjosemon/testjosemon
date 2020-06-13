package com.lxisoft.service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lxisoft.domain.Exam;
import com.lxisoft.domain.QstnOption;
import com.lxisoft.domain.Question;
import com.lxisoft.repository.ExamRepository;
import com.lxisoft.repository.QstnOptionRepository;
import com.lxisoft.repository.QuestionRepository;


/**
 * QuestionService
 */

@Service
@Transactional
public class QuestionService {

    private final Logger log = LoggerFactory.getLogger(QuestionService.class);
	    @Autowired
	    ExamRepository examRepo;
	    @Autowired
		private OptionService optService;
        @Autowired
    	private QuestionRepository questRepo;

        /**
         * Find all Questions from Database.
         * @return List<Question>.
         */
        public List<Question> findAll() {
    		List<Question> question=questRepo.findAll();
    		return question;
    	}

        
	/*
	 * public void saveOrUpdate(Question question, Exam exam) {
	 * //question.setExam(exam); questRepo.save(question);
	 * 
	 * }
	 */

        
        /**
         * Find a Question from Database By its id
         * @return Question.
         */
    	public Question findById(long qstn_id) {
    		Question quest=null;
    			Optional< Question> optional=questRepo.findById(qstn_id);
    			if(optional.isPresent())
    			{
    				quest=optional.get();
    			}
    		return quest;
    		
    	}

    	
    	/**
         * Save a Question in to database
         * @param Question
         */
		public void save(@Valid Question question) 
		{
			
			questRepo.save(question);
		}

		
		/**
         * Find set of Questions from Database By its level
         * @param level
         * @return List<Question>.
         */
		public List<Question> findByLevel(String level) {
			List<Question> finalQstns=new ArrayList<Question>(); 
			List<Question> qstns=findAll();
			for(Question quest:qstns)
				if(quest.getLevel().equalsIgnoreCase(level))
				{
					finalQstns.add(quest);
					log.info(quest.getLevel()+"level creating");
				}
			return finalQstns;
		}

		
		/**
         * Search for set of questions based on string value
         * @param searchQstn
         * @return List<Question>.
         */
		public List<Question> findByQstn(String searchQstn) {
			List<Question> finalQstns=new ArrayList<Question>(); 
			List<Question> qstns=findAll();
			for(Question quest:qstns)
				if(quest.getQstn().contains(searchQstn))
				{
					finalQstns.add(quest);
					log.info("quest get");
				}
			return finalQstns;
		}

		
		/**
         * delete a Question from Database By its id
         * @param questionId.
         */
		public void deleteQuestion(String qId) {
			long id=Integer.parseInt(qId);
			questRepo.deleteById(id);
		}

		
		/**
         * Get all questions from corresponding exam
         * @param Exam
         * @return List<Question>.
         */
		public List<Question> getAllQuestionsFromExam(Exam exam) {
			Set<Question> questions=exam.getQuestions();
			List<Question> list = new ArrayList<Question>();
			for(Question quest:questions) 
			{
				list.add(quest);
			}
			return list;
		}

		
		
		/**
         * Checking csv file has proper fields and return flag
         * @param file
         * @return int.
         * @throws IOException
         */
		public int checkFile(MultipartFile file) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line;int flag=0;
			while((line=br.readLine())!=null)
			{
				String[] data=line.split(",");
				if(data.length!=5)
					flag=1;
			}
			return flag;
		}
		
		
		/**
         * Save data in the csv file
         * @param file
         * @return List<Question>.
         * @throws IOException
         */
		public List<Question> saveFile(MultipartFile file) throws IOException {
			int i=0;
			List<Question> qstnList=new ArrayList<>();
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line;
			while((line=br.readLine())!=null)
			{
				if(i!=0)
				{
					String[] data=line.split(",");
					Question qstn=new Question();
					qstn.setQstn(data[0]);
					qstn.setLevel(data[1]);
					save(qstn);
					qstn=optService.saveQstnOptn(qstn,data[2],data[3],data[4]);
					qstnList.add(qstn);
				}
				i++;
			}
			return qstnList;
		}
		
		
		/**
         * Delete a question from database
         * @param question
         */ 
		public void delete(@Valid Question question) 
		{
			questRepo.delete(question);
		}
		
		
		/**
         * Delete multiple questions from database
         * @param List of question_id's
         */
		public void deleteMultiple(List<String> qIds) {
			for(String id:qIds)
			{
				delete(findById(Integer.parseInt(id)));
			}
		}
		
		
		/**
         * checking the requested delete questions are in active exams
         * @param List of question_id's
         * @return List<Question>.
         */
		public List<Question> checkDelete(List<String> qIds) {
			List<Question> qstnList=new ArrayList<Question>();
			for(String id:qIds)
			{
				Question temp=activeQuestionCheck(findById(Integer.parseInt(id)));
				if(temp!=null)
					qstnList.add(temp);
			}
			return qstnList;
		}
		
		
		/**
         * getting active question from Active Exam 
         * @param Question
         * @return Question.
         */
		public Question activeQuestionCheck(Question qstn)
		{
			Question active=null;
			List<Exam> examList=examRepo.findAll();
			for(Exam exam:examList)
			{
				for(Question q:exam.getQuestions())
				{
					if(q.getId().equals(qstn.getId()))
						active=q;
				}
			}
			return active;
		}
}