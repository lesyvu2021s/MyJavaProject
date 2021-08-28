package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ContainerNotFoundException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Containers;
import com.example.demo.repository.ContainersRepository;

@Service
public class ContainersService {

	@Autowired
	private ContainersRepository repo ; 
	
	public Containers save(Containers con) {
		return repo.save(con);
	}
	
	public Containers findContainer(int id) {
		Optional<Containers> optinalContainer =repo.findById(id);
		Containers con = null;
		if(optinalContainer.isPresent()) {
			con = optinalContainer.get();
		}else {
			new NotFoundException("Id không tồn tại ");
		}
		return con;
	}
	
	public List<Containers> findAll(){
		return repo.findAll();
		
	}
	
	public Containers update(Integer id,Containers container) {
	
		Containers con = repo.findById(id).orElseThrow(
				()->new NotFoundException("Id không tồn tại "));
			con.setName(container.getName());
			con.setCapacity(container.getCapacity());
			return repo.saveAndFlush(con);
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
