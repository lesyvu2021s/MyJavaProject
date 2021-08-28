package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ContainerNotFoundException;
import com.example.demo.exception.NotFoundException;
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
	
	public List<ProductUser> findAll(Integer pageNo , Integer pageSize , String sortBY){
		Pageable paging = PageRequest.of(pageNo, pageSize ,Sort.by(sortBY));
		
		Page<ProductUser> pagedResult = productUserRepo.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<ProductUser>();
		}
		
	}
	
	public void  delete(Integer id) {
		ProductUser productUser = productUserRepo.findById(id).orElseThrow(
				()-> new NotFoundException("Id không tồn tại "));
			productUserRepo.delete(productUser);;
		
		
		
	}
	
	public ProductUser getProductUserById(int id) {
		Optional<ProductUser> optinalProductUser = productUserRepo.findById(id);
		ProductUser productUser = null ; 
		if(optinalProductUser.isPresent()) {
			productUser = optinalProductUser.get();
		}else {
			new NotFoundException("Id không tồn tại" + id);
		}
			return productUser;
	}
	
	
	public ProductUser updateProductUser(Integer productUserId , ProductUser productUserRequest ) {
		ProductUser productUser = productUserRepo.findById(productUserId).orElseThrow(
				()-> new NotFoundException("Id không tồn tại "));
		productUser.setPrice(productUserRequest.getPrice());
		return productUserRepo.save(productUser);
	}
	
	
}
