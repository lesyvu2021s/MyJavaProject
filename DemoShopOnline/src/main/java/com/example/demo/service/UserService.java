package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.data.domain.Sort;
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
	

	public List<User>  getAllUser(Integer pageNo , Integer pageSize , String sortBY){
		Pageable paging = PageRequest.of(pageNo, pageSize ,Sort.by(sortBY));
		
		Page<User> pagedResult = userRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<User>();
		}
	}
	
	
	
}
