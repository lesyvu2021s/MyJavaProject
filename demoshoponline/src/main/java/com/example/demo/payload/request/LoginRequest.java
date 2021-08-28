package com.example.demo.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank
	private String name ;
	@NotBlank
	private String password ;
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
