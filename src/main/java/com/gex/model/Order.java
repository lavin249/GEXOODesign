package com.gex.model;

import java.util.Date;

import com.gex.utils.NotImplementedException;

public class Order {
  
  private boolean filled; 
  private int amountFilled;
  private int amountToFill;
  private String customerId;
  private int id;
  private Double price;
  private Date timeReceived;
  private int tradeSize;
  
  
  public boolean isFilled() {
	return filled;
}

public void setFilled(boolean filled) {
	this.filled = filled;
}

public int getAmountFilled() {
	return amountFilled;
}

public void setAmountFilled(int amountFilled) {
	this.amountFilled = amountFilled;
}

public int getAmountToFill() {
	return amountToFill;
}

public void setAmountToFill(int amountToFill) {
	this.amountToFill = amountToFill;
}

public String getCustomerId() {
	return customerId;
}

public void setCustomerId(String customerId) {
	this.customerId = customerId;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Double getPrice() {
	return price;
}

public void setPrice(Double price) {
	this.price = price;
}

public Date getTimeReceived() {
	return timeReceived;
}

public void setTimeReceived(Date timeReceived) {
	this.timeReceived = timeReceived;
}

public int getTradeSize() {
	return tradeSize;
}

public void setTradeSize(int tradeSize) {
	this.tradeSize = tradeSize;
}

public Order(boolean filled, int amountFilled, int amountToFill, String customerId, int id, Double price,
		Date timeReceived, int tradeSize) {
	super();
	this.filled = filled;
	this.amountFilled = amountFilled;
	this.amountToFill = amountToFill;
	this.customerId = customerId;
	this.id = id;
	this.price = price;
	this.timeReceived = timeReceived;
	this.tradeSize = tradeSize;
}


}
