package elements;

import java.util.*;
import elements.*;

/**
 * this class compares and lists buying orders and selling orders objects.
 * @author bilal
 *
 */
public class Market {
	
	private PriorityQueue<SellingOrder> sellingOrders;

	private PriorityQueue<BuyingOrder> buyingOrders;

	private ArrayList<Transaction> transactions;
	
	private int market_fee;
	
	/**
	 * This generates a Market object and sets default or given (by fee) values to the properties.
	 * @param fee
	 */
	public Market(int fee) {
		
		sellingOrders = new PriorityQueue<SellingOrder>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		transactions = new ArrayList<Transaction>();
		market_fee = fee;
		
	}

	/**
	 * This method adds selling orders to the sellingOrders PriorityQueue.
	 * @param order
	 */
	public void giveSellOrder(SellingOrder order) {
	
		sellingOrders.add(order);
		
	}

	/**
	 * This method adds buying orders to the buyingOrders PriorityQueue.
	 * @param order
	 */
	public void giveBuyOrder(BuyingOrder order) {
		
		buyingOrders.add(order);
	}

	/**
	 * This method ensures that the market price is equal to the given price parameter.
	 * @param price is double desired to be the market price
	 */
	public void makeOpenMarketOperation(double price) {
		
			if (buyingOrders.size()!= 0){
				
				if (price <= buyingOrders.peek().getPrice()) {

					giveSellOrder(new SellingOrder(0, buyingOrders.peek().getAmount() , buyingOrders.peek().getPrice()));
					
				}
				
			}

			if (sellingOrders.size()!= 0) {

				if (price >= sellingOrders.peek().price) {
					
					giveBuyOrder(new BuyingOrder(0, sellingOrders.peek().amount , sellingOrders.peek().price));	
				
				}
			}
			
		
		
	
	
	
	}

	/**
	 * This method performs trade transactions by comparing the most recent elements of the lists. 
	 * @param traders is an ArrayList holding traders.
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		
		while(true) {
			if (sellingOrders.size()!=0 && buyingOrders.size()!= 0) {
				
				
				if (sellingOrders.peek().price <= buyingOrders.peek().price) {
					
					
					if (sellingOrders.peek().amount < buyingOrders.peek().amount) {
						if (sellingOrders.peek().traderID!=0) {
						traders.get(sellingOrders.peek().traderID).getWallet().remBlockCoin(sellingOrders.peek().amount, sellingOrders.peek().amount*sellingOrders.peek().price*(1000-market_fee)*0.001);
						}
						if (buyingOrders.peek().traderID!=0) {
						traders.get(buyingOrders.peek().traderID).getWallet().remBlockDollar(sellingOrders.peek().amount, sellingOrders.peek().amount*buyingOrders.peek().price);
						}
						transactions.add(new Transaction(sellingOrders.peek(), buyingOrders.peek()));
						sellingOrders.poll();
						buyingOrders.peek().setAmount(buyingOrders.peek().amount-sellingOrders.peek().amount);
					
					}
					else if (sellingOrders.peek().amount > buyingOrders.peek().amount) {
						if (sellingOrders.peek().traderID!=0) {
						traders.get(sellingOrders.peek().traderID).getWallet().remBlockCoin(buyingOrders.peek().amount, buyingOrders.peek().amount*sellingOrders.peek().price*(1000-market_fee)*0.001);
						}
						if (buyingOrders.peek().traderID!=0) {
						traders.get(buyingOrders.peek().traderID).getWallet().remBlockDollar(buyingOrders.peek().amount, buyingOrders.peek().amount*buyingOrders.peek().price);
						}
						transactions.add(new Transaction(sellingOrders.peek(), buyingOrders.peek()));
						buyingOrders.poll();
						sellingOrders.peek().setAmount(sellingOrders.peek().amount-buyingOrders.peek().amount);
					
					}
					else {
						if (sellingOrders.peek().traderID!=0) {
						traders.get(sellingOrders.peek().traderID).getWallet().remBlockCoin(buyingOrders.peek().amount, buyingOrders.peek().amount*sellingOrders.peek().price*(1000-market_fee)*0.001);
						}
						if (buyingOrders.peek().traderID!=0) {
						traders.get(buyingOrders.peek().traderID).getWallet().remBlockDollar(buyingOrders.peek().amount, buyingOrders.peek().amount*buyingOrders.peek().price);
						}
						transactions.add(new Transaction(sellingOrders.peek(), buyingOrders.peek()));
						buyingOrders.poll();
						sellingOrders.poll();
					}
				}
				else {
					break;
				}
			}
			else {
				break;
			}
		}
	}

	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}

	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public int getMarket_fee() {
		return market_fee;
	}
	


}
