package com.cart.products.customeExceptions;

public class DuplicateProductException extends RuntimeException{
	public DuplicateProductException(String message) {
		super(message);
	}
}
