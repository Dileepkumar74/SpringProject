package com.cart.products.customeExceptions;

public class NoChangesProductException extends RuntimeException{
	public NoChangesProductException(String message) {
		super(message);
	}
}
