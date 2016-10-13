package com.ecommerce.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ecommerce.member.Member;
import com.ecommerce.resource.Auth;
import com.ecommerce.resource.Constant;

public class AdminLoginInterceptors extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try{
			
			Member member = Auth.getUserData( this.getTokenCookie(request) );
			if( Member.isAdmin(member.member_rolemember_role_id) ){
				return true;
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/admin/login");
		return false;
	}
	
	private String getTokenCookie(HttpServletRequest request){
		Cookie cookie = this.IterateCookieByName(Constant.COOKIE_TOKEN_INDEX, request);
		return  cookie != null ? cookie.getValue() : "";
	}
	
	private Cookie IterateCookieByName(String name,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		System.out.print(cookies[0].getValue()+" panjangnya gan ");
		if( cookies.length > 0 ){
			for( Cookie cookie : cookies ){
				if( cookie.getName().equals(name) )
					return cookie;
			}
		}
		return null;
	}
	
}
