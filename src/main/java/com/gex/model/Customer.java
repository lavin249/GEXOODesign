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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + cashInUSD;
	result = prime * result + goldOunces;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Customer other = (Customer) obj;
	if (cashInUSD != other.cashInUSD)
		return false;
	if (goldOunces != other.goldOunces)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}

public int getCashInUSD() {
	return cashInUSD;
}

public void setCashInUSD(int cashInUSD) {
	this.cashInUSD = cashInUSD;
}

public int getGoldOunces() {
	return goldOunces;
}

public void setGoldOunces(int goldOunces) {
	this.goldOunces = goldOunces;
}

public String getName() {
	return name;
}
  
}
