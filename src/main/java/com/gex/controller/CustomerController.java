package com.gex.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gex.model.Customer;
import com.gex.model.GEXModel;
import com.gex.model.Order;

@RestController
public class CustomerController {
	static final Logger LOGGER = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	private GEXModel gexModel;
	
	@PostMapping("/addCustomer/{name}/{cash}/{gold}")
	 private ResponseEntity<Customer> addCustomer(@PathVariable(name="name") String name, @PathVariable(name="cash") int cashInUSD,@PathVariable(name="gold") int goldOunces){
		 LOGGER.info("Adding Customer.");
		 Customer customerDetails = gexModel.addCustomer(name, cashInUSD, goldOunces);
		 if (customerDetails == null) {
			 LOGGER.info("Duplicate Add Customer Request.");
			 return new ResponseEntity("{\"error\" : \"Duplicate Request\"}", HttpStatus.BAD_REQUEST);
		 }
		 LOGGER.info("Added Customer.");
		 return ResponseEntity.accepted().body(customerDetails);
	 } 
	
	@GetMapping("/getUnFilledOrders/{name}")
	 private ResponseEntity<List<Order>> getUnFilledOrders(@PathVariable("name") String name){
		 LOGGER.info("Fetching unfilled order");
		 List<Order> orderDetails = gexModel.getUnFilledOrders(name);
		 if(orderDetails==null) {
			 LOGGER.info("Invalid Customer Id");
			 return new ResponseEntity("{\"error\" : \"invalid Customer Id or customer doesnot have any unfilled buy/sell orders\"}", HttpStatus.BAD_REQUEST);
		 }
		 LOGGER.info("Fetched unfilled orders");
		 return ResponseEntity.accepted().body(orderDetails);
	 } 
	
	@GetMapping("/getCustomer/{name}")
	 private ResponseEntity<Customer> getCustomer(@PathVariable String name){
		 LOGGER.info("Fetching Customer Details");
		 Customer customerDetails = gexModel.getCustomer(name);
		 if(customerDetails==null) {
			 LOGGER.info("Invalid Customer Id");
			 return new ResponseEntity("{\"error\" : \"invalid Customer id\"}", HttpStatus.BAD_REQUEST);
		 }
		 LOGGER.info("Fetched Customer Details");
		 return ResponseEntity.accepted().body(customerDetails);
	 } 
}
