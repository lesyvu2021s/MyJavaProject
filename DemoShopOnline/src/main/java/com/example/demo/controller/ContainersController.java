package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.exception.ContainerNotFoundException;
import com.example.demo.model.Containers;
import com.example.demo.service.ContainersService;

@RestController
@RequestMapping("/con/api/container")
public class ContainersController {

	@Autowired
	private ContainersService service ; 
	
//	@Autowired 
//	private ContainersRepository containersRepository ;
//	
//	@Autowired
//	private ProductsRepository productsRepository;
	
//	@PostMapping
//	public ResponseEntity<Containers> create(@RequestBody Containers con){
//		return ResponseEntity.ok().body(containersRepository.save(con));
//	}
//	
//	@GetMapping
//	public ResponseEntity<Page<Containers>> getAll(Pageable pageable){
//		return ResponseEntity.ok(containersRepository.findAll(pageable));
//	}
 	
	
	@PostMapping("/add-con")
	public ResponseEntity<Containers> save(
			@RequestBody Containers con
			){
		return ResponseEntity.ok(service.save(con));
	}
	
	

	@GetMapping("/get/{id}")
	public ResponseEntity<Containers> findContainer(
			@PathVariable(name = "id") Integer id
			){
		
		return ResponseEntity.ok(service.findContainer(id).orElseThrow(()-> new ContainerNotFoundException(id)));
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<List<Containers>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Containers> update(
			@PathVariable(name = "id") Integer id , 
			@RequestBody Containers container
			){
		return ResponseEntity.ok().body(service.update(id,container));
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		service.delete(id);
	}

	
	
	
}
