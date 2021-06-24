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

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.model.Order_Item;
import com.example.demo.service.OrderItemService;

@RestController
@RequestMapping("/v1/api/orderItem")
public class OrderItemController {

	@Autowired
	private OrderItemService orItemService;
	
	@Autowired
	private ModelMapper modelMapper ; 
	
	@PostMapping("/post")
	public ResponseEntity<OrderItemDTO> save(@RequestBody OrderItemDTO orderItemDto) {
		Order_Item orderItemRequest =modelMapper.map(orderItemDto, Order_Item.class);

		Order_Item orderItem =orItemService.save(orderItemRequest); 
		
		OrderItemDTO orderReponse = modelMapper.map(orderItem, OrderItemDTO.class);
		
		return new ResponseEntity<OrderItemDTO>(orderReponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderItemDTO> getById(@PathVariable(name = "id") Integer id){
		Order_Item orderItem = orItemService.getOrderItemById(id);
		OrderItemDTO oderItemDto = modelMapper.map(orderItem,OrderItemDTO.class );
		return ResponseEntity.ok(oderItemDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public String DeleteById(@PathVariable("id") Integer id ) {
		
		orItemService.delebyId(id);
		return "Order đã được xóa thành công !";
	}
	
	@GetMapping("/get")
	public List<OrderItemDTO> getAllOrders(){
		
		return orItemService.getAll().stream().map(orderItem ->modelMapper.map(orderItem, OrderItemDTO.class))
				.collect(Collectors.toList());
		
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<OrderItemDTO> updateOrderItem(
			@PathVariable("id") Integer id ,
			@RequestBody OrderItemDTO orItemDTO 
			){
		Order_Item orderItemRequest = modelMapper.map(orItemDTO, Order_Item.class);
		
		Order_Item orderItem =orItemService.updateOrderItem(id, orderItemRequest);
		OrderItemDTO orderItemReponse =modelMapper.map(orderItem, OrderItemDTO.class);
		
		return   ResponseEntity.ok().body(orderItemReponse);
	}
	
}
