package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Products;
import com.example.demo.service.ProductsService;
@RestController
@RequestMapping("/con/api/products")
public class ProductsController {

	@Autowired
	private ProductsService productsService ;
	
	@GetMapping("/get-products")
	public ResponseEntity<List<Products>> getAllProducts(){
		return ResponseEntity.ok(productsService.getAllProducts());
		
	}
	
	@PostMapping("/products")
	public ResponseEntity<Products> saveProducts(@RequestBody Products product){
		
		return ResponseEntity.ok(productsService.addProduct(product));
		
	}
	
	@PutMapping("/products")
	public ResponseEntity<Products> updateProducts(
			@RequestBody Products product 
			){
		Products pro =productsService.edit(product);
		return new  ResponseEntity<>(pro,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/get-products/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productsService.deleteProduct(id);
	}
	
	@GetMapping("/get-products/{id}")
	public ResponseEntity<Optional<Products>> GetByIdProduct(@PathVariable(name="id") Integer id
			){
		return ResponseEntity.ok(productsService.GetByIdProducts(id));
	}
	
	
}
