package com.ecommerce.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping( value = "/",method = RequestMethod.GET )
	public String home(){
		return "home/home";
	}
	
	@RequestMapping("/admin/")
	public String adminHome(){
		return "admin/home/home";
	}
	
}
