package co.com.jcd.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.com.jcd.inventory.response.ResponseRest;
import co.com.jcd.inventory.util.Constants;
import co.com.jcd.inventory.util.InventoryUtils;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class InventoryExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseRest> constraintValidationHandler(ConstraintViolationException ex) {
		ResponseRest response = new ResponseRest();
		response.setMetadata(ex.getMessage(), 
				Constants.COD_ERROR.getValor() , InventoryUtils.generateDate());
		return new ResponseEntity<ResponseRest>(response, HttpStatus.BAD_REQUEST);
	}
}
