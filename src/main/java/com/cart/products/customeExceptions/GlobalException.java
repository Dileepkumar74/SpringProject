package com.cart.products.customeExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(DuplicateProductException.class)
	public ResponseEntity<ErrorResponseModel> duplicateFound(DuplicateProductException dpex){
		ErrorResponseModel error = new ErrorResponseModel(
				HttpStatus.NOT_FOUND,
				dpex.getMessage(),
				System.currentTimeMillis()
				);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(NoChangesProductException.class)
	public ResponseEntity<ErrorResponseModel> coChangesDetected(NoChangesProductException dpex){
		ErrorResponseModel error = new ErrorResponseModel(
				HttpStatus.CONFLICT,
				dpex.getMessage(),
				System.currentTimeMillis()
				);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
}
