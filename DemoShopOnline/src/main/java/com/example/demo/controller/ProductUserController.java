package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.example.demo.dto.ProductUserDto;
import com.example.demo.model.ProductUser;
import com.example.demo.service.ProductUserService;

@RestController
@RequestMapping("/api/v1/productUser")
public class ProductUserController {

	@Autowired
	private ProductUserService service;
	
	@Autowired
	private ModelMapper modelMapper ; 
	
	@PostMapping("/add")
	public ResponseEntity<ProductUserDto> save(@RequestBody ProductUserDto productUserDto){
		ProductUser productUserRequest = modelMapper.map(productUserDto, ProductUser.class);
		ProductUser productUser = service.save(productUserRequest);
		ProductUserDto productUserReponse = modelMapper.map(productUser, ProductUserDto.class);
		return new ResponseEntity<ProductUserDto>(productUserReponse,HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		service.delete(id);
		return "Xóa thành công !" ;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ProductUserDto> findProductUserById(@PathVariable(name = "id") Integer id){
		
		ProductUser productUser = service.getProductUserById(id);
		ProductUserDto productUserDTO = modelMapper.map(productUser, ProductUserDto.class);
		return ResponseEntity.ok(productUserDTO);
		
	}
	
	@GetMapping("/get")
	public  List<ProductUserDto> GetAll(
			@RequestParam(defaultValue = "0") Integer pageNo ,
			@RequestParam(defaultValue = "10")Integer pageSize ,
			@RequestParam(defaultValue = "id") String sortBy
			){
		return service.findAll(pageNo, pageSize, sortBy).stream().map(productUser -> modelMapper.map(productUser, ProductUserDto.class))
				.collect(Collectors.toList());
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<ProductUserDto> updateProductUser(@PathVariable("id") Integer id , 
			@RequestBody ProductUserDto productUserDto){
		
		ProductUser productUserRequest =modelMapper.map(productUserDto, ProductUser.class);
		
		ProductUser productUser =service.updateProductUser(id, productUserRequest);
		
		ProductUserDto productUserReponse =modelMapper.map(productUser, ProductUserDto.class);
		
		
		return ResponseEntity.ok().body(productUserReponse);
		
	}
	
}
