package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="containers")
public class Containers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	
	@Column(name = "name")
	private String name;
	@Column(name = "capacity")
	private String capacity ;
	public Containers() {
		
	}
	public Containers(Integer id, String name, String capacity) {
		
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	
	
}
