package com.example.demo.controller;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Example;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrdersService service ; 
	
	@Autowired
	private ModelMapper modelMap ;
	
	@PostMapping("/post")
	public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
		Orders orderRequest =modelMap.map(orderDto, Orders.class);

		Orders order =service.crateOrder(orderRequest); 
		
		OrderDto orderReponse = modelMap.map(order, OrderDto.class);
		
		return new ResponseEntity<OrderDto>(orderReponse,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderDto> getById(@PathVariable(name = "id") Integer id){
		Orders order = service.getOrderById(id);
		OrderDto oderDto = modelMap.map(order,OrderDto.class );
		return ResponseEntity.ok(oderDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public String DeleteById(@PathVariable("id") Integer id ) {
		
		service.deleteById(id);
		return "Order đã được xóa thành công !";
	}
	
	@GetMapping("/get")
	public List<OrderDto> getAllOrders(){
		
		return service.getAll().stream().map(order ->modelMap.map(order, OrderDto.class))
				.collect(Collectors.toList());
		
	}
	
	
}
