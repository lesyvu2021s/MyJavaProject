package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.hateoas.Link;


@Entity
@Table(name = "users" , 
uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
					@UniqueConstraint(columnNames = "email")
})

public class User {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@NotBlank
	@Size(max = 20)
	private String name;
	@NotBlank
	@Size(max=50)
	@Email
	private String email;
	@NotBlank
	@Size(max = 120)
	private String password ;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_roles" , 
					joinColumns = @JoinColumn(name="user_id"),
					inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	
	public User() {
		
	}

	

	public User(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
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



	public String getPassword() {
		return password;
	}



	public Set<Role> getRoles() {
		return roles;
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



	public void setPassword(String password) {
		this.password = password;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
}
