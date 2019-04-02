package com.accolite.parkingsystem.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParkingSystemException extends RuntimeException {

	private static final long serialVersionUID = 6913092591318514038L;

	public ParkingSystemException(String message) {
		super(message);
	}
}
