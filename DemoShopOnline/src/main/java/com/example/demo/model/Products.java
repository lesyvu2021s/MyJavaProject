package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@GeneratedValue
	private Long id ;
	
	private String name ; 
	private String content ; 
	private Double price ; 
	private String unit ; 
	
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "container_id" , nullable = false)
	private Containers container ;

	public Products() {
		
	}

	public Products(Long id, String name, String content, Double price, String unit, Containers container) {
		
		this.name = name;
		this.content = content;
		this.price = price;
		this.unit = unit;
		this.container = container;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Containers getContainer() {
		return container;
	}

	public void setContainer(Containers container) {
		this.container = container;
	}
	
	
	
	
}
