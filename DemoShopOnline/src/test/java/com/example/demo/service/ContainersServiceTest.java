package com.example.demo.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Containers;
import com.example.demo.repository.ContainersRepository;

class ContainersServiceTest {

	@Mock
	private ContainersRepository repo ; 
	
	@InjectMocks
	private ContainersService service;
	
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void saveTest() {
		Containers con =new Containers(1, "nước suối hiệu soju", "330ml", null);
		
		service.save(con);
		verify(repo,times(1)).save(con);
	}
	
	@Test
	public void findContainerTest() {
		Containers con = new Containers(1, "nước suối hiệu soju", "330ml", null);
		when(repo.findById(1)).thenReturn(Optional.of(con));
		
		Optional<Containers> expected = service.findContainer(con.getId());
		assertThat(expected).isSameAs(con);
		verify(repo).findById(con.getId());
		
		
		
	}

}
