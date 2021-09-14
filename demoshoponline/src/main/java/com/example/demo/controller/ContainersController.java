package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ContainerDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUserDto;
import com.example.demo.exception.ContainerNotFoundException;
import com.example.demo.model.Containers;
import com.example.demo.model.ProductUser;
import com.example.demo.model.Products;
import com.example.demo.service.ContainersService;

@RestController
@RequestMapping("/con/api/container")
public class ContainersController {

	@Autowired
	private ContainersService service ; 
	
	@Autowired
	private ModelMapper modelMapper ;
	
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
 	
	
	@PostMapping("/post")
	public ResponseEntity<ContainerDto> save(
			@RequestBody ContainerDto conDto
			){
		Containers conRequest = modelMapper.map(conDto, Containers.class);
		
		Containers con = service.save(conRequest);
		
		ContainerDto conReponse = modelMapper.map(con, ContainerDto.class);
		
		return new ResponseEntity<ContainerDto>(conReponse,HttpStatus.CREATED);
	}
	
	

	@GetMapping("/get/{id}")
	public ResponseEntity<ContainerDto> findContainer(
			@PathVariable(name = "id") Integer id
			){
		Containers con = service.findContainer(id);
		ContainerDto conDto = modelMapper.map(con, ContainerDto.class);
		return ResponseEntity.ok(conDto);
	}
	
	
	@GetMapping("/get")
	public List<ContainerDto> findAll(){
		
		return service.findAll().stream().map(container -> modelMapper.map(container, ContainerDto.class))
				.collect(Collectors.toList());
	}
	
	
	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id) {
		service.delete(id);
		return "Xóa thành công !";
	}

	@PutMapping("/put/{id}")
	public ResponseEntity<ContainerDto> updateContainer(@PathVariable("id") Integer id , 
			@RequestBody ContainerDto conDto){
		
		Containers containerRequest =modelMapper.map(conDto, Containers.class);
		
		Containers con  =service.update(id, containerRequest);
		
		ContainerDto containerReponse =modelMapper.map(con, ContainerDto.class);
		
		
		return ResponseEntity.ok().body(containerReponse);
		
	}
	//sơ khai 
	
	
}
