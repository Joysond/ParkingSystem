package com.accolite.parkingsystem.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ParkingSystemControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ParkingSystemExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ParkingSystemExceptionResponse exceptionResponse = new ParkingSystemExceptionResponse(ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ParkingSystemException.class)
	public final ResponseEntity<ParkingSystemExceptionResponse> handleBadRequestException(ParkingSystemResponse ex,
			WebRequest request) {
		ParkingSystemExceptionResponse exceptionResponse = new ParkingSystemExceptionResponse(ex.getMsg(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
