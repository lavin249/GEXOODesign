package com.gex.model;

public class Customer {

  private final String name;
  private int cashInUSD;
  private int goldOunces;

  public Customer(String name, int cashInUSD, int goldOunces) {
    this.name = name;
    this.cashInUSD = cashInUSD;
    this.goldOunces = goldOunces;
  }
}
