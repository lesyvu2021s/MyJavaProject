package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Orders;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrdersService {

	@Autowired
	private OrderRepository orderRepository ;
	
	@Autowired
	private UserRepository userRepo ;
	
	
	public List<Orders> getAll(){
		return orderRepository.findAll();
	}
	
	public Orders crateOrder(Orders order) {
		return orderRepository.save(order);
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
