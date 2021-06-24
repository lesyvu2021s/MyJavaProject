package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "containers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Containers {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id ;
	
	private String name ;
	private String capacity ;
	
	@OneToMany(mappedBy = "container",cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnore
	private List<Products> products;
}
