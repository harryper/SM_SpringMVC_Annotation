package com.harryper.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.harryper.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {
	private static List<User> userList;
	
	public UserController() {
		super();
		userList = new ArrayList<User>();
	}
	
	
	//静态的日志类
	private final static Log logger = LogFactory.getLog(UserController.class);
	
	
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String registerForm() {
		logger.info("register GET方法被调用...");
		return "registerForm";
	}
	/**
	 * 
	 * @RequestParam 参数绑定注解，用于将请求中的参数赋值到指定的形参中
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("username")String username,
				     @RequestParam("password")String password,
				     @RequestParam("nickname")String nickname) {
		logger.info("register POST方法被调用..." + nickname);
		User user = new User();
		
		user.setNickname(nickname);
		user.setUsername(username);
		user.setPassword(password);
		
		userList.add(user);
		
		return "loginForm";
	}
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam("username") String username,
				  @RequestParam("password") String password,
				  Model model) {
		logger.info("login方法被调用..." + "username = " + username + "password = " + password);
		
		for(User user : userList) {
			if (user.getUsername().equals(username) 
				&& user.getPassword().equals(password)) {
				model.addAttribute("user", user);
				return "welcome";
			}
		}
		return "loginForm";
	}
	/**
	 * @PathVariable 获得请求 URL 中的动态参数
	 * @param userId
	 */
	@RequestMapping("/pathVariable/{userId}")
	public void pathVariableTest(@PathVariable Integer userId) {
		logger.info("pathVariableTest方法被调用... userId = " + userId );
	}
	
	@RequestMapping("/requestHeaderTest")
	public void requestHeaderTest(@RequestHeader("User-Agent") String userAgent,
						@RequestHeader("Accept") String[] accepts) {
		logger.info("requestHeaderTest被调用... userAgent = " + userAgent + " accepts = " + accepts);
	}
	
	/**
	 * @CookieValue 将请求的cookie数据映射到功能处理方法的参数上
	 * @param sessionId
	 */
	@RequestMapping("/cookieValueTest")
	public void cookieValueTest(@CookieValue("JSESSIONID") String sessionId) {
		logger.info("cookieValueTest被调用... sessionId = " + sessionId);
	}
}
