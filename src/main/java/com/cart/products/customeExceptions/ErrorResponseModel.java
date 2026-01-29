package com.cart.products.customeExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity.BodyBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseModel {
	private HttpStatus status;
	private String message;
	private long timestamp;
}
