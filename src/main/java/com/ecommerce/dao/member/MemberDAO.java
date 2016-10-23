package com.ecommerce.dao.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ecommerce.base.DAOInterface;

@Service("MemberDAO")
public class MemberDAO implements DAOInterface<Member>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Member getOne(int id) {
		return null;
	}
	
	public Member getByEmailPassword(String email,String password){
		return this.getByEmailPasswordHandler(email, password,new MemberRowMapper());
	}
	
	public Member getByEmailPassword(String email,String password,RowMapper<Member> rowMapper){
		return this.getByEmailPasswordHandler(email, password,rowMapper);
	}
	
	private Member getByEmailPasswordHandler(String email,String password,RowMapper<Member> rowMapper){
		String sql = "select * from member where member_email_address = ? and member_password = MD5(?)";
		try{
			return (Member)jdbcTemplate.queryForObject(sql, new Object[]{ email,password },rowMapper);				
		}catch( EmptyResultDataAccessException e ){
			return null;
		}
	}
	
	
	@Override
	public List<Member> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Member data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Member data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	class MemberRowMapper implements RowMapper<Member>{

		@Override
		public Member mapRow(ResultSet rs, int arg1) throws SQLException {
			Member member = new Member();
			member.member_account_modified = rs.getString("member_email_address");
			member.member_contact = rs.getString("member_contact");
			member.member_contact2 = rs.getString("member_contact2");
			member.member_datecreated = rs.getString("member_datecreated");
			member.member_email_address = rs.getString("member_email_address");
			member.member_gender = rs.getInt("member_gender");
			member.member_id = rs.getInt("member_id");
			member.member_is_active = rs.getInt("member_is_active");
			member.member_is_verified = rs.getInt("member_is_verified");
			member.member_last_login = rs.getString("member_last_login");
			member.member_name = rs.getString("member_name");
			member.member_rolemember_role_id = rs.getInt("member_rolemember_role_id");

			return member;
		}
		
	}
	
}
