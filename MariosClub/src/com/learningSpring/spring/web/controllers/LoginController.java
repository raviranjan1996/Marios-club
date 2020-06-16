package com.learningSpring.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learningSpring.spring.web.dao.FormValidationGroup;
import com.learningSpring.spring.web.dao.Message;
import com.learningSpring.spring.web.dao.User;
import com.learningSpring.spring.web.service.OffersService;
import com.learningSpring.spring.web.service.UsersService;

@Controller
public class LoginController {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private OffersService offersService;
	
	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}


	@RequestMapping("/login")
	public String showLogin()
	{
		return "login";
	}
	
	
	
	@RequestMapping("/denied")
	public String showDenied()
	{
		return "denied";
	}
	
	@RequestMapping("/messages")
	public String showMessages()
	{
		return "messages";
	}
	
	
	@RequestMapping("/admin")
	public String showAdmin(Model model ,Principal principal) {
		
		

		User user = usersService.getUser(principal.getName());
		model.addAttribute("user", user);
		System.out.println(user);
		
		try {
			List<User> users = usersService.getAllUsers();
			model.addAttribute("users", users);
					
							
		} catch (Exception e) {
			return "denied";
		}
		return "admin";
	}
	

	
	@RequestMapping("/logout")
	public String logout()
	{
		return "logout";
	}
	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model )
	{
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value="/createaccount" , method=RequestMethod.POST)
    public String createAccount(@Validated(FormValidationGroup.class) User user , BindingResult result) {
		
		if(result.hasErrors())
		{
		return "newaccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		
		if(usersService.exists(user.getUsername()))
		{
			System.out.println("caught duplicate username");
			    result.rejectValue("username", "DuplicateKey.user.username");
			    return "newaccount";
		}
						
			try {
				usersService.create(user);
			} catch (DuplicateKeyException  e) {
				
				result.rejectValue("username", "DuplicateKey.user.username");
				return "newaccount";
			}	
			
				
	return "accountcreated";
}
		
	
	@RequestMapping("/delete")
	public String delete(Model model , User user)
	{
		usersService.deleteUser(user.getUsername());
		System.out.println(user.getUsername());
		return "delete";
	}
	
	@RequestMapping(value="/getmessages" , method=RequestMethod.GET , produces="application/json")
	@ResponseBody
	public Map<String , Object> getMessages(Principal principal)
	{
		List<Message> messages = null;
		if(principal == null)
		{
			messages = new ArrayList<Message>();
		}
		
		else
		{
			String username = principal.getName();
			messages = usersService.getMessages(username);
		}
		
		Map<String , Object> data = new HashMap<String , Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		
		return data;
	}
	
	
	@RequestMapping(value="/sendmessage" , method=RequestMethod.POST , produces="application/json")
	@ResponseBody
	public Map<String , Object> sendMessage(Principal principal , @RequestBody Map<String , Object> data)
	{
		
		String text = (String)data.get("text");
		String name = (String)data.get("name");
		String email = (String)data.get("email");
		Integer target = (Integer)data.get("target");
		
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("raviranjanp94@gmail.com");
		mail.setTo(email);
		mail.setSubject("Re: " + name + ", your message");
		mail.setText(text);
		
		try {
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't send message");
		}
		
		
		
		
		System.out.println(text + "," + name + "," + email);
		Map<String , Object> rval = new HashMap<String , Object>();
		rval.put("success", true);
		rval.put("target" , target);
		return rval;
	}
	
	
	
	
	
	
	
	
	
}
