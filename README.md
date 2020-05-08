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
