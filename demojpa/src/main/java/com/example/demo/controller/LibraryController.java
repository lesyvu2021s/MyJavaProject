package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Library;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository; 

@RestController
@RequestMapping("api/v1/libraries")
public class LibraryController {

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping
	public ResponseEntity<Library> create( @Validated @RequestBody Library library){
		return ResponseEntity.ok().body(libraryRepository.save(library));
		
	}
	
	@GetMapping
	public ResponseEntity<Page<Library>> getAll(Pageable pageable){
		return ResponseEntity.ok().body(libraryRepository.findAll(pageable));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Library> update(
			@PathVariable(name = "id") Integer id , 
			@RequestBody Library library
			
			){
		Optional<Library> optinalLibrary = libraryRepository.findById(id);
		if(!optinalLibrary.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		library.setId(optinalLibrary.get().getId());
		libraryRepository.save(library);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Library> getById(
			@PathVariable(name = "id") Integer id 
			){
		Optional<Library> optinalLibrary = libraryRepository.findById(id);
		if(!optinalLibrary.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(optinalLibrary.get());
	}
	
}
