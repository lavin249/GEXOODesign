package com.gex.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DuplicateCustomerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateCustomerException(String message) {
		super(message);
	}
}