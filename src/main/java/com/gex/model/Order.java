package com.gex.model;

import java.util.Date;

import com.gex.utils.NotImplementedException;

public class Order {

  public final boolean filled() {
    throw new NotImplementedException();
  }

  public final int getAmountFilled() {
    throw new NotImplementedException();
  }

  public final int getAmountToFill() {
    throw new NotImplementedException();
  }

  public final String getCustomerId() {
    throw new NotImplementedException();
  }

  public final int getId() {
    throw new NotImplementedException();
  }

  public final double getPrice() {
    throw new NotImplementedException();
  }

  public final Date getTimeReceived() {
    throw new NotImplementedException();
  }

  protected final void trade(int tradeSize) {
    throw new NotImplementedException();
  }
}
