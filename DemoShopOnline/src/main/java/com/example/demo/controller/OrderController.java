package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Orders;
import com.example.demo.service.OrdersService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrdersService service ; 
	
	@Autowired
	private ModelMapper modelMap ;
	
	@PostMapping("/post")
	public ResponseEntity<Orders> save(@RequestBody Orders order) {
		return ResponseEntity.ok().body(service.crateOrder(order));
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Orders>> getAllOrder(){
		return ResponseEntity.ok(service.getAll());
		
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderDto> getById(@PathVariable(name = "id") Integer id){
		Orders order = service.getOrderById(id);
		OrderDto oderDto = modelMap.map(order,OrderDto.class );
		return ResponseEntity.ok(oderDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void DeleteById(@PathVariable("id") Integer id ) {
		
		service.deleteById(id);
		
	}
	
}
