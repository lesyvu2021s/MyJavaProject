package com.example.demo.dto;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {

	
	private Integer id ; 
	@Pattern(regexp = "^[a-z0-9_-]{4,16}$")
	private String name ; 
	
	private String email ; 
	
	
	
}
