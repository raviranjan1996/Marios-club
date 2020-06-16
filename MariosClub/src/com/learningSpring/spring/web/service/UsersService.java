package com.learningSpring.spring.web.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.learningSpring.spring.web.dao.Message;
import com.learningSpring.spring.web.dao.MessagesDao;
import com.learningSpring.spring.web.dao.Offer;
import com.learningSpring.spring.web.dao.OffersDAO;
import com.learningSpring.spring.web.dao.User;
import com.learningSpring.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;
	
	
	
	
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers(){
		
		return usersDao.getUsers();
	}
		
	public void create(User user) {
		
		usersDao.create(user);
		
	}
	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	
	public void sendMessage(Message message)
	{
		messagesDao.saveOrUpdate(message);
	}
	
	public User getUser(String username) {
		return usersDao.getUser(username);
	}

	public void deleteUser(String username) {
		usersDao.delete(username);
		
	}

	public List<Message> getMessages(String username) {
		
		return messagesDao.getMessages(username);
	}
}
