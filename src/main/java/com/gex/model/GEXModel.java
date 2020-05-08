package com.gex.model;

import java.util.List;

import com.gex.utils.NotImplementedException;

public class GEXModel {
  public Customer addCustomer(final String name, final int cashInUSD, final int goldOunces) {
    throw new NotImplementedException();
  }

  public Order buy(final String customerId, final double price, final int orderSize) {
    throw new NotImplementedException();
  }

  public Order sell(final String customerId, final double price, final int orderSize) {
    throw new NotImplementedException();
  }

  public List<Order> getUnFilledOrders(final String customerId) {
    throw new NotImplementedException();
  }

  public Customer getCustomer(final String customerId) {
    throw new NotImplementedException();
  }
}
