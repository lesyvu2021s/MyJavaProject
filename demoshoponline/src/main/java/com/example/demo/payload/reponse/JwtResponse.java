package com.example.demo.payload.reponse;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Integer id ; 
	private String name;
	private String email;
	private List<String> roles;
	
	public JwtResponse(String token, Integer id, String name, String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
