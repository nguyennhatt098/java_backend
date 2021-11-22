package com.entities;

public class Authenticate {
	private String token;
	private Users user;
	public Authenticate() {
		
	}
	public Authenticate(String token, Users user) {
		super();
		this.token = token;
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
