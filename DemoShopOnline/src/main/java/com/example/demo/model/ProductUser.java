package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_user")
public class ProductUser  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "user_id")
	private User user ;
	
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name = "product_id")
	private Products product;
	
	private Double price ;

	public ProductUser(User user, Products product, Double price) {
		super();
		this.user = user;
		this.product = product;
		this.price = price;
	}

	
	
	
}
