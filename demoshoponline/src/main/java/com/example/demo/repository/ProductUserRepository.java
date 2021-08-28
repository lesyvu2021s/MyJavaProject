package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductUser;

public interface ProductUserRepository extends JpaRepository<ProductUser, Integer> {

}
