package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dao.member.Member;
import com.ecommerce.resource.Auth;
import com.ecommerce.resource.Constant;
import com.ecommerce.resource.BaseResponse;
import com.ecommerce.service.login.LoginService;
import com.ecommerce.service.login.Login;;

@RestController
public class LoginController {
	
	public static final String FAIL_LOGIN_MSG  = "Maaf email / password salah";
	public static final String SUCCESS_LOGIN_MSG = "Login berhasil, anda akan di pindahkan secara otomatis";
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("login")
	public BaseResponse loginHandler(@RequestParam( required = true,name="email",defaultValue = "" )String email,
			@RequestParam( required = true,name="password",defaultValue = "" ) String password){
		
		return loginHandler(email,password,false);
	}
	
//	@PostMapping("admin/login",)	
	@RequestMapping( value = "admin/login",produces = "application/json",method = RequestMethod.POST )
	public BaseResponse adminLoginHandler(
//			@RequestBody( required = true)String email,
//			@RequestBody( required = true) String password){	
				@RequestBody Login login
			){

		return loginHandler( login.email,login.password,true);
	}
	
	@PostMapping("user/token")	
	public boolean isValidToken(@RequestParam String token){
		try {			
			System.out.println("Token : "+token);
			Auth.getData(token);			
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	private BaseResponse loginHandler(String email,String password,boolean isAdminLogin){

		System.out.println(email+" = "+password);
		
		Member member =  isAdminLogin ? loginService.adminLogin(email, password) : loginService.login(email, password);
		
		BaseResponse  response = new BaseResponse(FAIL_LOGIN_MSG,true);
		
		if( member != null ){
			String token = Auth.createJWT(String.valueOf(member.member_id), Auth.ISSUER, Auth.LOGIN_SUBJECT, 
					Auth.EXPIRED_TIME_ONEYEAR, member);
			
			response.putOtherResponse(Constant.TOKEN_RESPONS_PARAMS, token);
			response.setErrorResponse(false);
			response.setMessageResponse(SUCCESS_LOGIN_MSG);			
			response.putOtherResponse("user_id", member.member_id);
			response.putOtherResponse("user_name", member.member_name);
			response.putOtherResponse("user_email", member.member_email_address);			
			response.putOtherResponse("user_role", member.member_rolemember_role_id);						
			
		}
		
		return response;
	}
	
}
