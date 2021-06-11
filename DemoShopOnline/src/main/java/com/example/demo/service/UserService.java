package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ContainerNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
@Service
public class UserService  {

	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	PasswordEncoder passwordEncoder ;
	

	public User save(User user) {
		
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public List<User> findALl(){
		return userRepository.findAll();
		
	}
	
	public User findUserById(Integer id) {
		Optional<User> optinalUser =userRepository.findById(id);
		if(optinalUser.isPresent()) {
			return optinalUser.get();
		}else {
			throw new ContainerNotFoundException(id);
		}
		
	}
	
	public void deleteUser(Integer id ) {
		userRepository.deleteById(id);
	}
	
	public User edit(User userDetail , Integer id) {
		User user = userRepository
								.findById(id)
								.orElseThrow(() -> new ContainerNotFoundException(id));
		user.setName(userDetail.getName());
		user.setEmail(userDetail.getEmail());
		user.setPassword(passwordEncoder.encode(userDetail.getPassword()));
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
