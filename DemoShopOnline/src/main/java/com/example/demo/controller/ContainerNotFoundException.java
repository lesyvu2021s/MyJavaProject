package com.example.demo.controller;

public class ContainerNotFoundException extends RuntimeException {

	public ContainerNotFoundException(Integer id) {
		super("Không tìm thấy container với id " + id + ".");
	}
}
