# Problem Description

You work for GEX, a new gold exchange, and are developing their trading system. New exchange customers are assigned a customerId, and they have a cash balance (in USD), and a gold balance in ounces. They can place both buy and sell orders. A buy order has a customer identifier, a bid price, an **amountToFill** (integer number of ounces to buy), and **amountFilled**. A sell order has a customer identifier, an ask price, an **amountToFill** (integer number of ounces to sell), and **amountFilled**. Orders are assigned a unique number as they are received, timestamped, and appended to the order book along with **amountToFill**. Whenever a trade affects an order, the **amountFilled** is increased by the trade size, and the **amountToFill** is decreased by the trade size. An order is considered filled when the **amountToFill** reaches zero. A trade is executed whenever the highest unfilled bid price meets or exceeds the lowest unfilled ask price. If there are identical highest bids or lowest asks, the orders are filled in the order received. When a trade executes, the buyer pays their bid price, the seller receives their ask price, the exchange receives the difference, the trade is timestamped, assigned a unique tradeId, and appended to the trade blotter. The **amountFilled** is updated, and **amountToFill** set to zero for both orders. If one order size is larger than the other, the amount traded is the smaller order size, and the smaller order will be filled, while the larger order will be partially filled. All trades are timestamped and assigned a unique identifier. Assume customer accounts can have negative balances.

The system will have an MVC-based design. The Model supports these behaviors:

1. **Account details:** Given a **customerId**, return the customer's account information, including name, gold balance, and cash balance.

2. **Place buy/sell orders:** Given a **customerId**, price, and **amountToFill**, place buy or sell order. Return the **orderId**, time received, and **amountFilled**.

3. **Match orders:** Whenever an order arrives, it is matched immediately if possible.

# Your task

Using the _GEXOODesign_ project, implement GEXModel and any other needed classes in order to satisfy GEXModelTest.

# Questions

Direct questions to **ISSDataDeskRecruitment@issgovernance.com**

# Submission

1. Fork this repository to make your own repository 
2. Work against your own repository
3. Add this email id: **ISSDataDeskRecruitment@issgovernance.com** as collaborator to your repository



-------------------------------------------------------------------------------------------------------------------------------------------------------
Assumptions
1. Single person can order multiple times
	BUY (lavin,1000$,20 ounce)
	BUY (lavin,1000$,20 ounce)
Here lavin has placed two order. I have assumed both are valid and both will have different unique identifier (Order id)

2. Same is assumed for selling

3. Transaction will only happen on BUY. On every buy, ssller list is checked. 

System is non-persistent. It holds memmory in application.

End Points
orders
1. Sell : Method    : POST
		  URL       : http://localhost:8080/sellOrder/
		  Parameter : /sellOrder/{name}/{bidPrice}/{orderSize}
		  Example   : http://localhost:8080/sellOrder/seller-lavin-1/100/5 
		  This states that seller-lavin-1 wants to buy 5 ounces of gold at $100.

2. Buy  :  METHOD     : POST
		   URL      : http://localhost:8080/buyOrder/
		   Parameter: /buyOrder/{name}/{bidPrice}/{orderSize}	
		   Example  : http://localhost:8080/buyOrder/buyer-lavin-5/200/5
		   This state that buyer-lavin-5 wants to buy 5 ounces of gold at $200
		   	 

3. Check Trades : Method 	: 	GET 
				  URL		: 	http://localhost:8080/getAllTrades/
				  Parameters:	http://localhost:8080/getAllTrades/{tradeType}/{tradeStatus}
				  tradeType can be 'buy' or 'sell'
				  tradeStatus can be 'filled' or 'unfilled' or 'all'
				  Example : http://localhost:8080/getAllTrades/buy/all
				  This returns all (peding as well as complete) the buy orders 
				  Example : http://localhost:8080/getAllTrades/buy/unfilled
				  This returns all pending the buy orders 
				  Example : http://localhost:8080/getAllTrades/buy/filled
				  This returns all completed the buy orders 
				  

Customer
1. Add Customer  : Method 	 : POST
				   URL    	 : http://localhost:8080/addCustomer/
				   Parameters: /addCustomer/{name}/{cash}/{gold}  	
				   Example   : http://localhost:8080/addCustomer/eldin/1000/50
				   This returns successfully stored customer record in application. It states that Eldin has 

2. GET Unfilled trade   : Method  	:	GET 
						  URL	  	:	http://localhost:8080/getUnFilledOrders
						  Parameters:	/getUnFilledOrders/{name}
						  Example   :   http://localhost:8080/getUnFilledOrders/lavin
						  This returns all unfilled buy/sell order for that customer
				   
3. GET Customer detail  : Method    : 	GET
						  URL 		:   /getCustomer/
						  Parameters:   /getCustomer/{name}
						  Example   :   http://localhost:8080/getCustomer/lavin
						  This returns details of Customers


On Successfull sell order insertion : System returns all the sell orders in system (filled and unfilled both)
On Successfull buy order : System performs trade. 

Scenario 1 : 
Seller wants to buy 6 ounces of Gold at 1600 : http://localhost:8080/sellOrder/seller-lavin-2/1500/6  
Buyer wants to buy 6 ounces of Gold at 1500  : http://localhost:8080/buyOrder/buyer-lavin-5/1500/6    
Result successfull trade. Both trades filled. 
Rule 1 is staisfied

Scenario 2 : 
Seller wants to buy 6 ounces of Gold at 1600 : http://localhost:8080/sellOrder/seller-lavin-2/1500/6  
Buyer wants to buy 6 ounces of Gold at 1500  : http://localhost:8080/buyOrder/buyer-lavin-5/1500/6    
Result:  Trade incomplete as buyer wants to buy at 1500 and no seller is present to sell gold at/below 1500. 
Rule 1 is staisfied

Scenario 3 :
Seller wants to buy 6 ounces of Gold at 1600  : http://localhost:8080/sellOrder/seller-lavin-2/1600/6   
Seller wants to buy 2 ounces of Gold at 1650  : http://localhost:8080/sellOrder/seller-lavin-3/1650/2   
Buyer wants to buy 10 ounces of Gold at 1700  : http://localhost:8080/buyOrder/buyer-lavin-5/1700/10     

Result : Seller 2 and Seller 3 trade should be complete. Buyer should be in progress as it needs 2 more ounce of Gold to complete trade. 
Rule 2 is satisfied and Rule 3 as well because Seller-2 is getting preference becasue of lesser selling price. 

Scenario 4 :
Seller wants to buy 6 ounces of Gold at 1650  : http://localhost:8080/sellOrder/seller-lavin-1/1650/6   
Seller wants to buy 6 ounces of Gold at 1700  : http://localhost:8080/sellOrder/seller-lavin-2/1700/6
Seller wants to buy 6 ounces of Gold at 1600  : http://localhost:8080/sellOrder/seller-lavin-3/1600/6   
Buyer wants to buy 6 ounces of Gold at 1800   : http://localhost:8080/buyOrder/buyer-lavin-5/1800/6    

Result Trade will happen between Buyer and Seller 3 as seller 3 is selling at the lowest price amonsgt all sellers. 
Buy service will return seller-1 and seller-2 data.
Rule 4 is satisfied

Scenario 5 :
Seller wants to buy 6 ounces of Gold at 1650  : http://localhost:8080/sellOrder/seller-lavin-1/1600/6   
Seller wants to buy 6 ounces of Gold at 1600  : http://localhost:8080/sellOrder/seller-lavin-2/1600/6
Seller wants to buy 6 ounces of Gold at 1600  : http://localhost:8080/sellOrder/seller-lavin-3/1600/6   
Buyer wants to buy 6 ounces of Gold at 1800   : http://localhost:8080/buyOrder/buyer-lavin-5/1800/6    

Result Trade will happen between Buyer and Seller 1 as all sellers are selling at same proce and seller 1 bid was place earliest amongst other sellers 
Buy service will return seller-2 and seller-3 data.
Rule 5 is satisfied

Scenario 6 : 
Seller wants to buy 6 ounces of Gold at 1600 : http://localhost:8080/sellOrder/seller-lavin-2/1600/6   
Buyer wants to buy 2 ounces of Gold at 1700  : http://localhost:8080/buyOrder/buyer-lavin-5/1700/2
Rule 6 is satisfied. Buyer remains in pending state when no seller is avilable at that price