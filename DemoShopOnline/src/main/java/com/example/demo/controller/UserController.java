package com.example.demo.controller;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
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
	public ResponseEntity<User> save(
			@RequestBody User user
			){
		return ResponseEntity.ok().body(userService.save(user));
	}

//	@GetMapping("/get")
//	public ResponseEntity<List<User>> finAll(){
//		return ResponseEntity.ok(userService.findALl());
//	}
	
	
	
//	@GetMapping("/get/{id}")
//	public ResponseEntity<Optional<User>> findUserById(
//			@PathVariable(name = "id") Integer id
//			){
//		
//		return  ResponseEntity.ok(userService.findUserById(id));
//	}
	

	@DeleteMapping("/delete/{id}")
	public void delete(
			@PathVariable(name = "id") Integer id
			) {
		userService.deleteUser(id);
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<User> update(
			@PathVariable(name = "id") Integer id,
			@RequestBody User user
			){
		return ResponseEntity.ok().body(userService.edit(user , id ));
	}
	
//	@GetMapping("/get")
//	public ResponseEntity<List<User>> getAllUser(
//			@RequestParam(defaultValue = "0") Integer pageNo ,
//			@RequestParam(defaultValue = "3")Integer pageSize ,
//			@RequestParam(defaultValue = "id") String sortBy
//			){
//		List<User> list = userService.getAllUser(pageNo, pageSize, sortBy);
//		return ResponseEntity.ok(list);
//	}
	
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
	public ResponseEntity<List<User>> getAllUser2(){
		return ResponseEntity.ok(userService.findALl());
	}
	
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id){
		User user = userService.findUserById(id);
		
		UserDto userReponse = modelMapper.map(user, UserDto.class);
		return ResponseEntity.ok().body(userReponse);
		
	}
	
	
	
	
}
