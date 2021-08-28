package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository ; 

	@Autowired
	@InjectMocks
	private UserService userService;
	
	private User user1 ; 
	private User user2;
	
	List<User> userList ; 
	
	@BeforeEach
	public void setup() {
		userList = new ArrayList<>();
		user1 = new User("Congphuong09", "congphuong@gmail.com", "12345678");
		user2 = new User("Messi10","messi10@gmail.com","12345678");
		
		userList.add(user1);
		userList.add(user2);
	}
	
	@AfterEach
	public void tearDown() {
		user1 = user2 = null ;
		userList = null ; 
	}
	
	
	
	@Test
	void GivenGetAllUsersShouldReturnListOfAllUsers() {
		userRepository.save(user1);
		when(userRepository.findAll()).thenReturn(userList);
		List<User> userList1 =userService.findALl();
		assertEquals(userList1, userList);
		verify(userRepository , times(1)).save(user1);
		verify(userRepository , times(1)).findAll();
	}
	

	
}
