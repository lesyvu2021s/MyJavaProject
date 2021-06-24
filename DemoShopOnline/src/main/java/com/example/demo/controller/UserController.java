package com.example.demo.controller;



import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.example.demo.dto.UserDto;
import com.example.demo.model.Orders;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/v1/api")
public class UserController {
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private ModelMapper modelMapper ;
	
	
	
	
	
	
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/add-user")
	public ResponseEntity<UserDto> save(
			@RequestBody @Valid UserDto userDto
			){
		User userRequest = modelMapper.map(userDto, User.class);
		User user = userService.save(userRequest);
		UserDto userReponse = modelMapper.map(user, UserDto.class);
		return new  ResponseEntity<UserDto>(userReponse,HttpStatus.CREATED);
	}



	@DeleteMapping("/delete/{id}")
	public String delete(
			@PathVariable(name = "id") Integer id
			) {
		userService.deleteUser(id);
		return "Xóa user thành công " ;
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<UserDto> update(
			@PathVariable(name = "id") Integer id,
			@RequestBody @Valid UserDto userDto
			){
		User userRequest = modelMapper.map(userDto, User.class);
		User user = userService.edit(userRequest , id);
		UserDto userReponse = modelMapper.map(user, UserDto.class);
		return ResponseEntity.ok().body(userReponse);
	}
	

	
//	@GetMapping("/get/{id}")
//	public EntityModel<User> retrieveUser(@PathVariable Integer id){
//		Optional<User> optinalUser =userService.findUserById(id);
//		if(!optinalUser.isPresent()) {
//			throw new ContainerNotFoundException(id);
//		}
//		EntityModel<User> resource =EntityModel.of(optinalUser.get());
//		WebMvcLinkBuilder linkTo =linkTo(methodOn(this.getClass()).getAllUser2());
//		resource.add(linkTo.withRel("all-user"));
//		return resource ;
//	}
	
	@GetMapping("/get")
	public List<UserDto> getAllUser(
			@RequestParam(defaultValue = "0") Integer pageNo ,
			@RequestParam(defaultValue = "3")Integer pageSize ,
			@RequestParam(defaultValue = "id") String sortBy
			){
		
		return userService.getAllUser(pageNo, pageSize, sortBy).stream().map(user -> modelMapper.map(user, UserDto.class))
				 .collect(Collectors.toList());
	}
	
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id){
		User user = userService.findUserById(id);
		
		UserDto userReponse = modelMapper.map(user, UserDto.class);
		return ResponseEntity.ok().body(userReponse);
		
	}
	
	
	
	
}
