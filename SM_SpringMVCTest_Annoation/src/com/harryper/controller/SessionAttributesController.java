package com.harryper.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.harryper.domain.User;

@Controller
@SessionAttributes("user")	//将Model中的属性名为 user 的属性放入HttpSession中
public class SessionAttributesController {
	
	
	private static List<User> userList;
	
	public SessionAttributesController() {
		super();
		userList = new ArrayList<User>();
	}
	
	private static Log logger = LogFactory.getLog(SessionAttributesController.class);
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		logger.info("register GET方法被调用...");
		return "registerForm";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("username") String username,
				     @RequestParam("nickname") String nickname,
				     @RequestParam("password") String password) {
		
		User user = new User();
		
		user.setNickname(nickname);
		user.setUsername(username);
		user.setPassword(password);
		
		userList.add(user);
		
		return "loginForm";
	}
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
				  @RequestParam("password") String password,
				  Model model) {
		logger.info("login方法被调用..." + "username = " + username + " password = " + password);
		
		for(User user : userList) {
			if (user.getUsername().equals(username) 
					&& user.getPassword().equals(password)) {
				model.addAttribute("user",user);
				return "welcome";
			}
		}
		
		return "loginForm";
	}
}
