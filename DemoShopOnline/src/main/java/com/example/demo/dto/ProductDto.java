package com.example.demo.dto;

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
public class ProductDto {

	private Integer id ; 
	private String name ; 
	private String content ; 
	private Double price ;
	private String unit ; 
	ContainerDto conDto ; 
}
