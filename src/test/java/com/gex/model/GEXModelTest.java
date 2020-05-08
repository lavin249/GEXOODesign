package com.gex.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class GEXModelTest {
  final GEXModel model = new GEXModel();

  @BeforeEach
  void setUp() throws Exception {
    model.addCustomer("Alice", 100000, 100);
    model.addCustomer("Bob", 100000, 100);
    model.addCustomer("Charlene", 100000, 100);
  }

  @Test
  void testAddCustomer() {
    Assert.notNull(model.addCustomer("Dora", 100000, 100), "Could not add customer to model.");
  }

  @Test
  void testBuy() {
    Assert.notNull(model.buy("Alice", 1511, 6), "Could not create buy order.");
  }

  @Test
  void testSell() {
    Assert.notNull(model.sell("Alice", 1511, 6), "Could not create sell order.");
  }

  @Test
  void testGetUnFilledOrders() {
    model.sell("Bob", 1600, 4);
    model.buy("Alice", 1610, 5);
    model.sell("Bob", 1590, 3);
    final List<Order> orders = model.getUnFilledOrders("Bob");
    assertEquals(1, orders.size());
    assertEquals(2, orders.get(0).getAmountToFill());
  }

  @Test
  void testGetCustomer() {
    Assert.notNull(model.getCustomer("Alice"), "Could not find customer.");
  }
}
