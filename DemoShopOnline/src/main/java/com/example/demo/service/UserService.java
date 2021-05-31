package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository ;
	

	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findALl(){
		return userRepository.findAll();
		
	}
	
	public Optional<User> findUserById(Integer id) {
		return userRepository.findById(id);
		
	}
	
	public void deleteUser(Integer id ) {
		userRepository.deleteById(id);
	}
	
	public User edit(User user) {
		return userRepository.save(user);
	}
	

	
	
	
}
