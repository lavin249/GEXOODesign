package com.gex.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CutomerControllerErrorAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NullPointerException.class})
    public void handle404(DuplicateCustomerException e) {
    	System.out.print("\nError\n");
    }
    
	/*
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler({DuplicateCustomerException.class,NullPointerException.
	 * class}) public void handle500(DuplicateCustomerException e) {}
	 * 
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler({DuplicateCustomerException.class,NullPointerException.
	 * class}) public void handle400(DuplicateCustomerException e) {}
	 */
	
}
