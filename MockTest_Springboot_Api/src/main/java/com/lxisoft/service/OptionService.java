package com.lxisoft.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.domain.QstnOption;
import com.lxisoft.domain.Question;
import com.lxisoft.repository.QstnOptionRepository;

/**
 * OptionService
 */

@Service
@Transactional
public class OptionService {

    private final Logger log = LoggerFactory.getLogger(OptionService.class);

    @Autowired
	private QstnOptionRepository optRepo;
    @Autowired
    private QuestionService qstnService;
    
    /**
     * Save an Option
     * @param option
     */
	public void save(QstnOption option) 
	{
		optRepo.save(option);
		
	}
	
	/**
     * Save all options and corresponding question 
     * @param question and options
     * @return Question.
     */
	public Question saveQstnOptn( Question question,String ...options) 
	{
		log.debug("options saving {} "+options[0],options[1],options[2]);
		for(int i=0;i<options.length;i++)
		{
			QstnOption optn=new QstnOption();
			optn.setOption(options[i]);
			qstnService.save(question);
			optn.setQuestion(question);
			save(optn);
		}
		return question;
	}

	/**
     * Find an option by its id.
     * @param option_id
     * @return option.
     */
	public QstnOption findById(String opt_Id) {
		long id=Integer.parseInt(opt_Id);
		QstnOption option=null;
			Optional< QstnOption> optional=optRepo.findById(id);
			if(optional.isPresent())
			{
				option=optional.get();
			}
		return option;
	}

}
