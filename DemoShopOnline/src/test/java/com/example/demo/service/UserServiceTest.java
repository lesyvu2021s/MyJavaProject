package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

class UserServiceTest {

	@Mock
	 UserRepository repo ; 
	
	@InjectMocks
	UserService service ;
	
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void saveTest() {
		User user = new User(1, "Brand", "brand@gmail.com", "123456");
		
		service.save(user);
		verify(repo, times(1)).save(user);
		
	}
	
	@Test
	public void findAllTest() {
		List<User> list = new ArrayList<User>();
		User user1 =new User(1, "Brand", "brand@gmail.com", "123456");
		User user2 =new User(2, "JohnDepp", "John@gmail.com", "123456");
		User user3 =new User(3, "Lucky", "brand@gmail.com", "123456");
		
		list.add(user1);
		list.add(user2);
		list.add(user3);
		
		when(repo.findAll()).thenReturn(list);
		
		List<User> userList =service.findALl();
		
		assertEquals(3, userList.size() );
		verify(repo,times(1)).findAll();
	}
	

	
	@Test
	public void deleteUserTest() {
		User user = new User(1, "Brand", "brand@gmail.com", "123456");
		when(repo.findById(1)).thenReturn(Optional.of(user));
		
		service.deleteUser(user.getId());
		
		verify(repo).deleteById(user.getId());
		
	}

}
