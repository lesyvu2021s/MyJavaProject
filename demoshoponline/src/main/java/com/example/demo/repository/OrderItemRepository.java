package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Order_Item;

public interface OrderItemRepository extends JpaRepository<Order_Item, Integer> {

}
