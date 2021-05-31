package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Containers;

public interface ContainersRepository extends JpaRepository<Containers, Integer> {

}
