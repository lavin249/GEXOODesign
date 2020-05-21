package com.gex.controller;

import java.util.List;

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
public class OrderController {
	
	@Autowired
	private GEXModel gexModel;
	
	@PostMapping("/buyOrder/{name}/{bidPrice}/{orderSize}")
	 private ResponseEntity<List<Order>> buyOrder(@PathVariable(name="name") String name, @PathVariable(name="bidPrice") int bidPrice,@PathVariable(name="orderSize") int orderSize){
		 List<Order> orderDetails = gexModel.buy(name, bidPrice, orderSize);
		 if(orderDetails==null) {
			 return new ResponseEntity("{\"error\" : \"Either Duplicate Order or incomplete details\"}", HttpStatus.BAD_REQUEST);
		 }
		 return ResponseEntity.accepted().body(orderDetails);
	 } 
	
	@PostMapping("/sellOrder/{name}/{bidPrice}/{orderSize}")
	 private ResponseEntity<List<Order>> sellOrder(@PathVariable(name="name") String name, @PathVariable(name="bidPrice") int bidPrice,@PathVariable(name="orderSize") int orderSize){
		 List<Order> orderDetails = gexModel.sell(name, bidPrice, orderSize);
		 if(orderDetails==null) {
			 return new ResponseEntity("{\"error\" : \"Either Duplicate Order or incomplete details\"}", HttpStatus.BAD_REQUEST);
		 }
		 return ResponseEntity.accepted().body(orderDetails);
	 } 
	
	@GetMapping("/getAllTrades/{tradeType}/{tradeStatus}")
	 private ResponseEntity<List<Order>> getUnFilledOrders(@PathVariable(name="tradeType") String tradeType, @PathVariable(name="tradeStatus") String tradeStatus){
		 List<Order> tradeDetails = gexModel.getTrades(tradeType,tradeStatus);
		 if(tradeDetails==null) {
			 return new ResponseEntity("{\"error\" : \"invalid Customer Id\"}", HttpStatus.BAD_REQUEST);
		 }
		 return ResponseEntity.accepted().body(tradeDetails);
	 } 
}
