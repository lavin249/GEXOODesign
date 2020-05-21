package com.gex.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gex.utils.NotImplementedException;

@Service
public class GEXModel {
	Set<Customer> customerSet = new HashSet<Customer>();
	List<Order> pendingBuyerList = new ArrayList<Order>();
	List<Order> pendingSellerList = new ArrayList<Order>();
	public int orderId = 1;

	/**
	 * @param name
	 * @param cashInUSD
	 * @param goldOunces
	 * Adds customer to GEX Application. This is non persistent. 
	 * @return
	 */
	public Customer addCustomer(final String name, final int cashInUSD, final int goldOunces) {
		Customer customerDetails = new Customer(name, cashInUSD, goldOunces);
		if (customerSet.add(customerDetails)) {
			return customerDetails;
		}
		return null;
	}

	/**
	 * @param customerId
	 * @param price
	 * @param orderSize
	 * @return list of pending sell orders
	 * if no sell order is present then buy order is put in pending buy order list
	 * Sorts pendingSellerList on the basis of price. If two orders have same proce then they will be sorted on the basis of time received. 
	 */
	public List<Order> buy(final String customerId, final double price, final int orderSize) {
		orderId++;
		Order buyOrder = new Order(false, 0, orderSize, customerId, orderId, price, new Date(), orderSize);
		if (!pendingSellerList.isEmpty()) {

			Comparator<Order> priceComparator = (Order o1, Order o2) -> {
				return (o1.getPrice()).compareTo(o2.getPrice());
			};

			Comparator<Order> tradeDateComparator = (Order o1, Order o2) -> {
				return (o1.getTimeReceived()).compareTo(o2.getTimeReceived());
			};

			pendingSellerList = pendingSellerList.stream().filter(sellObject -> sellObject.isFilled() == false)
					.sorted(priceComparator.thenComparing(tradeDateComparator)).collect(Collectors.toList());

			for (Order pendingSellOrder : pendingSellerList) {
				if (pendingSellOrder.isFilled() == false && buyOrder.getPrice() >= pendingSellOrder.getPrice()) {
					if (pendingSellOrder.getAmountToFill() > buyOrder.getAmountToFill()) { // seller has more gold quantity. Buyer wants less gold quantity
						// partial trade seller side. complete trade buyer side
						pendingSellOrder.setAmountFilled(pendingSellOrder.getAmountFilled() + buyOrder.getAmountToFill());
						pendingSellOrder.setAmountToFill(pendingSellOrder.getAmountToFill() - buyOrder.getAmountToFill());
						buyOrder.setAmountFilled(buyOrder.getAmountFilled());
						buyOrder.setFilled(true);
						buyOrder.setAmountToFill(0);
						break;
						// break as buy order is complete
					} else if (pendingSellOrder.getAmountToFill() < buyOrder.getAmountToFill()) { // Buyer wants more  gold quantity. Seller has less gold quantity
						// partial trade Buyer side. complete trade seller side
						buyOrder.setAmountFilled(buyOrder.getAmountFilled() + pendingSellOrder.getAmountToFill());
						buyOrder.setAmountToFill(buyOrder.getAmountToFill() - pendingSellOrder.getAmountToFill());
						pendingSellOrder.setAmountFilled(pendingSellOrder.getTradeSize());
						pendingSellOrder.setFilled(true);
						pendingSellOrder.setAmountToFill(0);
						// as buy order is not complete. loop through the list.
					} else {
						// complete trade Buyer side. complete trade seller side
						buyOrder.setAmountFilled(buyOrder.getTradeSize());
						buyOrder.setAmountToFill(0);
						buyOrder.setFilled(true);
						pendingSellOrder.setAmountFilled(pendingSellOrder.getTradeSize());
						pendingSellOrder.setFilled(true);
						pendingSellOrder.setAmountToFill(0);
						break;
						// as buy order is complete.
					}
				}
				// Looped over entire pending seller list and still buyOrder is pending. Add it to pending buyer List
				if (buyOrder.isFilled() == false) {
					pendingBuyerList.add(buyOrder);
				}
			}
		} else {
			pendingBuyerList.add(buyOrder);
		}
		return pendingSellerList.stream().filter(sellObject -> sellObject.isFilled() == false)
				.collect(Collectors.toList());
	}

	public List<Order> sell(final String customerId, final double price, final int orderSize) {
		orderId++;
		Order sellOrder = new Order(false, 0, orderSize, customerId, orderId, price, new Date(), orderSize);
		if (pendingSellerList.add(sellOrder)) {
			return pendingSellerList;
		}
		throw new NotImplementedException();
	}

	/**
	 * @param customerId
	 * @return
	 * Get all unfilled buy order and unfilled sell order if any.. for that customer
	 */
	public List<Order> getUnFilledOrders(final String customerId) {
		List<Order> unFilledBuyOrder = pendingBuyerList.stream()
				.filter(order -> customerId.equals(order.getCustomerId()) && order.isFilled() == false)
				.collect(Collectors.toList());
		List<Order> unFilledSellOrder = pendingSellerList.stream()
				.filter(order -> customerId.equals(order.getCustomerId()) && order.isFilled() == false)
				.collect(Collectors.toList());
		unFilledBuyOrder.addAll(unFilledSellOrder);
		return unFilledBuyOrder;
	}

	/**
	 * @param customerId
	 * @return Customer with it's details
	 */
	public Customer getCustomer(final String customerId) {
		Customer customerDetails = customerSet.stream().filter(customer -> customerId.equals(customer.getName()))
				.findAny().orElse(null);
		if (customerDetails != null)
			return customerDetails;
		return null;
	}

	/**
	 * @param tradeType   can be buy/sell
	 * @param tradeStatus can be filled/unfilled/all
	 * @return appropriate result set getsAll unfilled/filled buy/sell orders in the
	 *         system
	 */
	public List<Order> getTrades(String tradeType, String tradeStatus) {
		if (tradeType.equalsIgnoreCase("buy")) {
			if (tradeStatus.equalsIgnoreCase("filled"))
				return pendingBuyerList.stream().filter(buyObject -> buyObject.isFilled() == true)
						.collect(Collectors.toList());
			else if (tradeStatus.equalsIgnoreCase("unfilled"))
				return pendingBuyerList.stream().filter(buyObject -> buyObject.isFilled() == false)
						.collect(Collectors.toList());
			else
				return pendingBuyerList;
		} else {
			if (tradeStatus.equalsIgnoreCase("filled"))
				return pendingSellerList.stream().filter(sellObject -> sellObject.isFilled() == true)
						.collect(Collectors.toList());
			else if (tradeStatus.equalsIgnoreCase("unfilled"))
				return pendingSellerList.stream().filter(sellObject -> sellObject.isFilled() == false)
						.collect(Collectors.toList());
			else
				return pendingSellerList;
		}
	}
}