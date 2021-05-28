package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Containers;
import com.example.demo.model.Products;
import com.example.demo.repository.ContainersRepository;
import com.example.demo.repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository ; 
	
	@Autowired
	private ContainersRepository containersRepository ;
	
	
	
	
	public List<Products> getAll(){
		return productsRepository.findAll();
	}
	
	public Products addProducts(Products products) {
		Containers container = containersRepository.findById(products.getContainer()
													.getId()).orElse(null);
		if(null == container) {
			container = new Containers();
		}
		
		container.setName(products.getContainer().getName());
		//container.setCapacity(products.getContainer().getCapacity());
		products.setContainer(container);
		return productsRepository.save(products);
	}
	public Products edit(Products product) {
		return productsRepository.save(product);
	}
	
	public void delete(Long id) {
		productsRepository.deleteById(id);
	}
	

	
}
