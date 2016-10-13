package com.ecommerce.response;

public class LoginResponse {
	
	private String message,url,token;
	private boolean isSuccess;
	public static final String FAIL_LOGIN_MSG  = "Maaf email / password salah";
	public static final String SUCCESS_LOGIN_MSG = "Login berhasil, anda akan di pindahkan secara otomatis";
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public LoginResponse(String message,String url,String token,boolean isSuccess) {
		this.message = message;
		this.url = url;
		this.token = token;
		this.isSuccess = isSuccess;
	}
	
	public LoginResponse() {
		this.isSuccess = false;
		this.message = FAIL_LOGIN_MSG ;
		// TODO Auto-generated constructor stub
	}
	
	
		
	
	
}
