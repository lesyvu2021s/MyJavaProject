package com.example.demo.controller;

public class ContainerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContainerNotFoundException(Integer id) {
		super("Không tìm thấy container với id " + id + ".");
	}
}
