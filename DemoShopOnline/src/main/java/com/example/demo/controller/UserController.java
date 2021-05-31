package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/v1/api")
public class UserController {
	
	@Autowired
	private UserService userService ;
	
	@PostMapping("/add-user")
	public ResponseEntity<User> save(
			@RequestBody User user
			){
		return ResponseEntity.ok(userService.save(user));
	}

	@GetMapping("/get")
	public ResponseEntity<List<User>> finAll(){
		return ResponseEntity.ok(userService.findALl());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<User>> findUserById(
			@PathVariable(name = "id") Integer id
			){
		return  ResponseEntity.ok(userService.findUserById(id));
	}
	
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
		return ResponseEntity.ok().body(userService.edit(user));
	}
	
}
