package com.ecommerce.dao.member;

public class Member {
	
	public static final int ADMIN_ROLE = 1; 
	public static final int CUSTOMER_ROLE = 2; 	

	
	public int member_id,member_is_active, member_is_verified,member_rolemember_role_id,member_gender;
	public String member_name,member_email_address, member_contact, member_contact2, member_password,
	member_last_login,member_datecreated,member_account_modified;	
	
	
	
	public static boolean isAdmin(int role_id){
		return role_id == ADMIN_ROLE;
	}
	
}
