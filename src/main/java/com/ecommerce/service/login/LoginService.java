package com.ecommerce.service.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.member.Member;
import com.ecommerce.dao.member.MemberDAO;

@Service("LoginService")
public class LoginService extends MemberDAO{
	
	public Member adminLogin(String username,String password){
		return loginAuth(username, password, true);
	}
	
	public Member login(String username,String password){
		return loginAuth(username, password, false);
	}
	
	private Member loginAuth(String email,String password,boolean isAdminLogin){
		Member member = getByEmailPassword(email, password, new LoginMemberData());
		if( member == null )
			return null;
		
		if( isAdminLogin ){
			if( member.member_rolemember_role_id != Member.ADMIN_ROLE ){
				return null;
			}
		}
		return member;
	}
	
	class LoginMemberData implements RowMapper<Member>{

		@Override
		public Member mapRow(ResultSet rs, int arg1) throws SQLException {
			Member member = new Member();
			member.member_id = rs.getInt("member_id");
			member.member_name = rs.getString("member_name");
			member.member_email_address = rs.getString("member_email_address");
			member.member_rolemember_role_id = rs.getInt("member_rolemember_role_id");
			
			return member;
		}
		
	}
}
