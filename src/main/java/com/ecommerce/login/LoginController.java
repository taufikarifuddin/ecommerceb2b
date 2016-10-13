package com.ecommerce.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.member.Member;
import com.ecommerce.member.MemberDAO;
import com.ecommerce.resource.Auth;
import com.ecommerce.resource.Constant;
import com.ecommerce.response.LoginResponse;

@Controller
public class LoginController {
	
	@Autowired
	MemberDAO memberDAO;
	
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login(){
		return "home/login";
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public @ResponseBody LoginResponse loginHandler(String email,String password,@RequestParam(required = false) 
		HttpServletResponse response){
		return loginHandler(email,password,false,response);
	}
	
	@RequestMapping("admin/login")	
	public String adminLogin(){
		return "admin/home/login";		
	}
	
	@RequestMapping(value = "admin/login",method = RequestMethod.POST)
	public @ResponseBody LoginResponse adminLoginHandler(
			@RequestParam( required = true,name="email",defaultValue = "" )String email,
			@RequestParam( required = true,name="password",defaultValue = "" ) String password,
			HttpServletResponse response){	
		
		return loginHandler(email,password,true,response);
	}
	
	private LoginResponse loginHandler(String email,String password,boolean isAdminLogin,HttpServletResponse response){
//		System.out.println(email+" "+password+"  komponennya");
		Member member =  memberDAO.login(email, password, isAdminLogin);
		
		LoginResponse loginResponse = new LoginResponse();		
		
		if( member != null ){
			String token = Auth.createJWT(String.valueOf(member.member_id), Auth.ISSUER, Auth.LOGIN_SUBJECT, 
					Auth.EXPIRED_TIME_ONEYEAR, member);
			
			//add cookie
			Cookie cookie = new Cookie(Constant.COOKIE_TOKEN_INDEX, token);
			cookie.setMaxAge( Auth.EXPIRED_TIME_ONEYEAR );
			response.addCookie( cookie );

			loginResponse.setMessage( LoginResponse.SUCCESS_LOGIN_MSG );
			loginResponse.setToken( token );
			loginResponse.setSuccess(true);
		}
		
		return loginResponse;
	}
	
	
}
