package com.harryper.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	private final static Log logger = LogFactory.getLog(HelloController.class);
	
	
	/**
	 * 
	 * @return welcome界面
	 */
	@RequestMapping("/hello")
	public ModelAndView helloWorld() {
		logger.info("helloWorld方法被调用...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/jsps/welcome.jsp");
		mv.addObject("hello", "hello,world");
		
		return mv;
	}

}
