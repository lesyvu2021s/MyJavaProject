package com.example.demo.dto;

import java.time.LocalDate;

import javax.persistence.Id;

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
public class OrderDto {
	@Id
	private Integer id ; 
	private final  LocalDate createDate  = LocalDate.now(); 
	UserDto userDto;
}
