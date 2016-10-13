package com.ecommerce.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.resource.Constant;

@Service("MemberDAO")
public class MemberDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Member login(String email,String password,boolean isAdminLogin){
		Member member = new  Member();
		System.out.println("masuk sini gan "+email);
		if( email.equals("a@a.com") && password.equals("123") ){
			member.member_email_address = email;
			member.member_rolemember_role_id = Constant.ADMIN_ROLE;
			if( isAdminLogin ){
				if( true ){
					return member;
				}
			}else{
				return member;
			}
		}
		return null;
	}
	
	
}
