package com.example.demo.exception;

public class ImageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageException(String massage) {
		super(massage);
	}

	public  ImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
