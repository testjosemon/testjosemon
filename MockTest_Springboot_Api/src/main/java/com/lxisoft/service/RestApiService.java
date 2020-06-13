package com.lxisoft.service;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.lxisoft.domain.AttendedExam;
import com.lxisoft.domain.User;
import com.lxisoft.domain.UserExtra;
import com.lxisoft.model.AttendedExamBean;
import com.lxisoft.model.AttendedExamModel;
=======
import com.lxisoft.domain.User;
import com.lxisoft.domain.UserExtra;
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
import com.lxisoft.repository.UserExtraRepository;
import com.lxisoft.repository.UserRepository;

/**
 * Service class for managing Action in MocktestRestController.
 */
@Service
public class RestApiService {
	private final Logger log = LoggerFactory.getLogger(UserService.class);
	
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserExtraRepository extraRepo;
	public UserExtra currentUserExtra(String login)
	{
//		String login= SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> optUser=userRepo.findOneByLogin(login);
		User currentUser=optUser.get();
		log.debug("user currently logged is :"+currentUser);
		Optional<UserExtra> optExtra=extraRepo.findById( currentUser.getId());
		UserExtra userExtra=optExtra.get();
		return userExtra;
	}
<<<<<<< HEAD
	public List<AttendedExamModel> attendedExamDetails(List<AttendedExam> attendExamList) {
		
		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("hh:mm:ss a");
		List<AttendedExamModel> examlist=new ArrayList<AttendedExamModel>();
		
		User user=new User();
		for(AttendedExam atndexam:attendExamList)
		{
			
			log.debug("idmmm, "+atndexam.getId());
			AttendedExamModel model=new AttendedExamModel();
			
			
			model.setExamId(atndexam.getId());
			model.setDateTime(atndexam.getDateTime().toLocalDate().format(datePattern)+" at "+atndexam.getDateTime().toLocalTime().format(timePattern));
			//model.setTime(atndexam.getDateTime().toLocalTime().format(timePattern));
			model.setExamName(atndexam.getExam().getName());
			model.setScore(atndexam.getScore());
			model.setTotal(atndexam.getTotal());
			model.setPercentage(atndexam.getPercentage());
			model.setResult(atndexam.isResult());
			user=atndexam.getUserExtra().getUser();
			model.setUser(user);
			examlist.add(model);
		}
		return examlist;
	}
//	public List<User> getAttendedUserDetails(List<User> users, List<AttendedExam> attendList) {
//		
//		List<User> userlist=new ArrayList<User>();
//		for(AttendedExam atndexam:attendList)
//		{
//			
//			for(User user:users)
//			{
//				if(atndexam.getUserExtra().getId().equals(user.getId()))
//					userlist.add(user);
//			}
//		}
//		return userlist;
//	}
	
	
=======

>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801

}
