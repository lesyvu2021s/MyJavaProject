package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Products;
import com.example.demo.service.ProductsService;

@RestController
public class ProductsController {

	@Autowired
	private ProductsService service;
	
	@GetMapping("/get")
	public ResponseEntity<List<Products>> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/add-pro")
	public ResponseEntity<Products> create(
			@RequestBody  Products products
			){
				return ResponseEntity.ok().body(service.addProducts(products));
		
	}
	
	
	
	
	
	
}
