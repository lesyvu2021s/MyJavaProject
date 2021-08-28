package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductsService;
@RestController
@RequestMapping("/con/api/products")
public class ProductsController {

	@Autowired
	private ProductsService productsService ;
	
	@Autowired
	private ModelMapper modelMap;
	
//	@Value("${upload.path}")
//	private String fileUpload ;
	
	@GetMapping("/get")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> list =productsService.getAllProducts();
		return new ResponseEntity<List<ProductDto>>(list,HttpStatus.OK);
		
	}
	
//	@PostMapping("/post")
//	public ResponseEntity<ProductDto> saveProducts(@RequestBody ProductDto productDto){
//		Products productRequest = modelMap.map(productDto, Products.class);
//		
//		Products product = productsService.addProduct(productRequest);
//		ProductDto productReponse = modelMap.map(product, ProductDto.class);
//		return new ResponseEntity<ProductDto>(productReponse,HttpStatus.CREATED);
//		
//	}
	
//	@PutMapping("/put/{id}")
//	public ResponseEntity<ProductDto> updateProducts(
//			@PathVariable("id") Integer id ,
//			@RequestBody ProductDto productDto 
//			){
//		Products productRequest = modelMap.map(productDto, Products.class);
//		
//		Products product =productsService.edit(productRequest, id);
//		ProductDto productReponse =modelMap.map(product, ProductDto.class);
//		
//		return   ResponseEntity.ok().body(productReponse);
//	}
	
	@PostMapping(value = { "/add", "/update" }, consumes = "multipart/form-data")
	public ResponseEntity<String> createOrUpdateProduct(@ModelAttribute ProductDto productDto) {
		productsService.CreateOrUpdateProduct(productDto);
		return new ResponseEntity<>("Data insert sucessfully!", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		productsService.deleteProduct(id);
		return "Xóa sản phẩn thành công ";
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ProductDto> GetByIdProduct(@PathVariable(name="id") Integer id
			){
		ProductDto list =productsService.GetByIdProducts(id);
		
		
		return new  ResponseEntity<ProductDto>(list,HttpStatus.OK);
	}
	
	
}
