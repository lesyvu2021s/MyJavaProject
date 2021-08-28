package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Order_Item;
import com.example.demo.model.Orders;
import com.example.demo.model.ProductUser;
import com.example.demo.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepo ; 
	
	
	public Order_Item save(Order_Item orderItem) {
		return orderItemRepo.save(orderItem);
	}
	
	public List<Order_Item> getAll(){
		return orderItemRepo.findAll();
	}
	
	public void delebyId(Integer id) {
		orderItemRepo.deleteById(id);
	}
	
	public Order_Item getOrderItemById(Integer id) {
		Optional<Order_Item> optinalOrderItem = orderItemRepo.findById(id);
		Order_Item orderItem = null ; 
		if(optinalOrderItem.isPresent()) {
			orderItem = optinalOrderItem.get();
		}else {
			throw new RuntimeException("Không tìm thấy id : " + id);
		}
		return orderItem;
	}
	
	public Order_Item updateOrderItem(Integer Id , Order_Item orderItemRequest ) {
		Order_Item orderItem = orderItemRepo.findById(Id).orElseThrow(
				()-> new NotFoundException("Id không tồn tại "));
		orderItem.setPrice(orderItemRequest.getPrice());
		return orderItemRepo.save(orderItem);
	}
	
}
