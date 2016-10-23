package com.ecommerce.service.login;


public class Login{
	public String email,password;
	
	public Login(String email,String password){
		this.email = email;
		this.password = password;
	}
	
	public Login() {
		// TODO Auto-generated constructor stub
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
