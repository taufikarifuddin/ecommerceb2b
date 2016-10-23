package com.ecommerce.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ecommerce.dao.member.Member;
import com.ecommerce.resource.Auth;
import com.ecommerce.resource.Constant;

public class AdminLoginInterceptors extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try{			
			System.out.println(request.getHeader(Constant.TOKEN_RESPONS_PARAMS));
			Member member = Auth.getUserData( request.getHeader(Constant.TOKEN_RESPONS_PARAMS) );			
//			Member member = Auth.getUserData( request.getParameter(Constant.TOKEN_RESPONS_PARAMS) );
			if( member != null && Member.isAdmin(member.member_id) ){
				return true;
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
//		response.sendRedirect("/admin/login");
		response.sendError(403, "Forbidden Access");
		return false;
	}
	
}
