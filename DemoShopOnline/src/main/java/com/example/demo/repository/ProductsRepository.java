package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
