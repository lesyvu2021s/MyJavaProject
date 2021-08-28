package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrdersService {

	@Autowired
	private OrderRepository orderRepository ;
	
	
	
	
	public List<Orders> getAll( Integer pageNo , Integer pageSize ){
		Pageable pageable =PageRequest.of(pageNo, pageSize);
		Page<Orders> pagedResult =orderRepository.findAll(pageable);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
		return new ArrayList<Orders>() ;
		}
	}
	
	public Orders crateOrder(Orders order) {
		
		
		return orderRepository.save( order);
	}
	
	public void deleteById(Integer id ) {
		
		orderRepository.deleteById(id);
	}
	
	public Orders getOrderById(Integer id) {
		Optional<Orders> optinalOrder = orderRepository.findById(id);
		Orders order = null ; 
		if(optinalOrder.isPresent()) {
			order = optinalOrder.get();
		}else {
			throw new RuntimeException("Không tìm thấy id : " + id);
		}
		return order;
	}
	
	
	
	
}
