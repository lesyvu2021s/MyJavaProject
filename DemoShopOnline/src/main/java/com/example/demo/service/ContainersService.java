package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ContainerNotFoundException;
import com.example.demo.model.Containers;
import com.example.demo.repository.ContainersRepository;

@Service
public class ContainersService {

	@Autowired
	private ContainersRepository repo ; 
	
	public Containers save(Containers con) {
		return repo.save(con);
	}
	
	public Optional<Containers> findContainer(int id) {
		return repo.findById(id);
	}
	
	public List<Containers> findAll(){
		return repo.findAll();
		
	}
	
	public Containers update(int id,Containers container) {
	
		Optional<Containers> con = repo.findById(id);
		if(!con.isPresent()) {
			throw new ContainerNotFoundException(id);
		}else {
			repo.findById(id);
			return repo.saveAndFlush(container);
		}
		

	}
	
	
	public void delete(Integer id) {
		 Optional<Containers> con = repo.findById(id);
		 if(!con.isPresent()) {
			 throw new ContainerNotFoundException(id);
		 }else {
			 repo.deleteById(id);
		 }
	}
	
}
