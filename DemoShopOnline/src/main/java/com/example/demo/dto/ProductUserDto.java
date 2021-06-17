package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductUserDto {

	private Integer id ; 
	private Double price ;
	private Integer getUserId ; 
	private Integer getProductId ;
}
