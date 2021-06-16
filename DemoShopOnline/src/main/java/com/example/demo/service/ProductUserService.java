package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ContainerNotFoundException;
import com.example.demo.exception.ProductUserNotfoundException;
import com.example.demo.model.ProductUser;
import com.example.demo.model.User;
import com.example.demo.repository.ProductUserRepository;

@Service
public class ProductUserService {

	@Autowired
	private ProductUserRepository productUserRepo;
	
	public ProductUser save(ProductUser productUser) {
		return productUserRepo.save(productUser);
	}
	
	public List<ProductUser> findAll(){
		
		return productUserRepo.findAll();
	}
	
	public void  delete(Integer id) {
		Optional<ProductUser> productUser = productUserRepo.findById(id);
		if(!productUser.isPresent()) {
			throw new ProductUserNotfoundException();
		}else {
			productUserRepo.deleteById(id);
		}
		
		
	}
	
	public Optional<ProductUser> getProductUserById(int id) {
		Optional<ProductUser> productUser = productUserRepo.findById(id);
		if(!productUser.isPresent()) {
			throw new ProductUserNotfoundException();
		}else
			return productUserRepo.findById(id);
	}
	
	
	public void updateProductUser(Integer productUserId ) {
		
	}
	
	
}
