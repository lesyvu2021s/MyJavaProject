package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductUserNotfoundException;
import com.example.demo.model.ProductUser;
import com.example.demo.service.ProductUserService;

@RestController
@RequestMapping("/api/v1/productUser")
public class ProductUserController {

	@Autowired
	private ProductUserService service;
	
	@PostMapping("/add-productUser")
	public ResponseEntity<ProductUser> save(@RequestBody ProductUser productUser){
		return ResponseEntity.ok().body(service.save(productUser));
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		
		service.delete(id);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<ProductUser>> findProductUserById(@PathVariable(name = "id") Integer id){
		
		
		return ResponseEntity.ok(service.getProductUserById(id));
		
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<ProductUser>> GetAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
}
