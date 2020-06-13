package com.lxisoft.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

import com.lxisoft.domain.Authority;
import com.lxisoft.domain.Exam;
import com.lxisoft.domain.User;
import com.lxisoft.domain.UserExtra;
import com.lxisoft.repository.UserExtraRepository;
import com.lxisoft.repository.UserRepository;
import com.lxisoft.web.ExamController;

import io.github.jhipster.security.RandomUtil;


/**
 * UserExtraService
 */

@Service
public class UserExtraService {
	
	private final Logger log = LoggerFactory.getLogger(UserExtraService.class);
	@Autowired
	private UserExtraRepository extraRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	/**
     * Get current user from authentication.
     * @return UserExtra.
     */
	public UserExtra currentUserExtra()
	{
		String login= SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> optUser=userRepo.findOneByLogin(login);
		User currentUser=optUser.get();
		log.debug("user currently logged is :"+currentUser);
		Optional<UserExtra> optExtra=extraRepo.findById( currentUser.getId());
		UserExtra userExtra=optExtra.get();
		return userExtra;
	}

	
	/**
     * find all user from database.
     * @return List<User>.
     */
	public  List<User> findAll() {
		return userRepo.findAll() ;
	}

	
	/**
     * find user by id from database
     * @param userid
     * @return UserExtra.
     */
	public UserExtra findById(String uid) throws Exception {
			long id=Integer.parseInt(uid);
			Optional<User>user=userRepo.findById(id);
			User currentUser=user.get();
			Optional<UserExtra> optExtra=extraRepo.findById(currentUser.getId());
			UserExtra userExtra=optExtra.get();
			return userExtra;
	}

	
	/**
     * Save a user in to database.
     * @param User
     */
	public void save(@Valid User user) {
		BCryptPasswordEncoder encode=new BCryptPasswordEncoder(); 
		user.setPassword(encode.encode(user.getPassword()));
		Set<Authority> authorities = new HashSet<>();
		authorities.add(new Authority("ROLE_USER"));		
		user.setAuthorities(authorities);
		user.setActivated(true);
		UserExtra user_extra=new UserExtra();
		user_extra.setUser(user);
		log.debug("user  is :"+user_extra.getUser());
		extraRepo.save(user_extra);
		userRepo.save(user);
	}

	
	/**
     * Check userEmail exists or not
     * @param email
     * @return flag.
     */
	public boolean userEmailAlreadyExist(String email) {
		List<User> users=findAll();
		boolean flag=false;
		for(User us:users)
		{
		 if(us.getEmail().equalsIgnoreCase(email)) flag=true;
		}
		return flag;
	}

	/**
     * Check username exists or not
     * @param login(username)
     * @return flag.
     */
	public boolean UsernameExists(String login) {
		
		List<User> users=findAll();
		boolean flag=false;
		for(User us:users)
		{
		 if(us.getLogin().equalsIgnoreCase(login)) flag=true;
		}
		return flag;
	}

	
}
