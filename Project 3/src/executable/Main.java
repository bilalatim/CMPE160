package executable;

import java.io.*;
import java.util.*;
import elements.*;

/**
 * This class is main class and it read corresponding parameters and events from the input file, and log the results to the output file.
 * @author bilal
 *
 */
public class Main {
	
	/**
	 * main method read input from file and give outputs according to the appropriate operations.
	 * @param args
	 * @throws FileNotFoundException
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
			
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
			
		int A = in.nextInt();
		int B = in.nextInt();
		int C = in.nextInt();
		int D = in.nextInt();
		
		Random myRandom = new Random(A);
		
		
		Market market = new Market(B);
		ArrayList<Trader> traders = new ArrayList<Trader>();
		
		BuyingOrder[] buyorders = new BuyingOrder[D];
		SellingOrder[] sellorders = new SellingOrder[D];
 		
		int invalid = 0;
		
	
		for (int i=0; i<C; i++) {
			
			double dollar_amount = in.nextDouble();
			double PQoin_amount = in.nextDouble();
			
		
			traders.add(new Trader(dollar_amount,PQoin_amount));
			traders.get(i).setId(i);
			
		}
		
		int choose;
		int trader_id;
		double money_amount;
		double coin_amount;
		double price;
		int buy_order = 0;
		int sell_order = 0;
		
		double cp_buying = 0.00000;
		double cp_selling = 0.00000;
		
		for (int j=0; j<D; j++) {
			
			choose = in.nextInt();
			
			switch (choose) {
				
			case 10:
				
				trader_id = in.nextInt();
				price = in.nextDouble();
				coin_amount = in.nextDouble();
				
				if(traders.get(trader_id).getWallet().blockDollar(coin_amount*price)) {
					
					buyorders[buy_order] = new BuyingOrder(trader_id, coin_amount, price);
					market.giveBuyOrder(buyorders[buy_order]);
					market.checkTransactions(traders);
					buy_order = buy_order + 1;
					
				}
				else invalid = invalid + 1;
				
				
				break;
				
			case 11:
				
				trader_id = in.nextInt();
				price = market.getSellingOrders().peek().getPrice();
				coin_amount = in.nextDouble();
				
				if(traders.get(trader_id).getWallet().blockDollar(coin_amount*price)) {
					
					buyorders[buy_order] = new BuyingOrder(trader_id, coin_amount, price);
					market.giveBuyOrder(buyorders[buy_order]);
					market.checkTransactions(traders);
					buy_order = buy_order + 1;
				}
				else invalid = invalid + 1;
				
				break;
				
			case 20:
				
				trader_id = in.nextInt();
				price = in.nextDouble();
				coin_amount = in.nextDouble();
				
				if (traders.get(trader_id).getWallet().blockCoin(coin_amount)) {
					
					sellorders[sell_order] = new SellingOrder(trader_id, coin_amount, price);
					market.giveSellOrder(sellorders[sell_order]);
					market.checkTransactions(traders);
					sell_order = sell_order + 1;
				}
				else invalid = invalid + 1;
				
				break;
				
			case 21:
				
				trader_id = in.nextInt();
				price = market.getBuyingOrders().peek().getPrice();
				coin_amount = in.nextDouble();
				
				if (traders.get(trader_id).getWallet().blockCoin(coin_amount)) {
					
					sellorders[sell_order] = new SellingOrder(trader_id, coin_amount, price);
					market.giveSellOrder(sellorders[sell_order]);
					market.checkTransactions(traders);
					sell_order = sell_order + 1;
				}
				else invalid = invalid + 1;
				
				break;
				
			case 3:
				
				trader_id = in.nextInt();
				money_amount = in.nextDouble();
				traders.get(trader_id).getWallet().deposit(money_amount);
				
				break;
				
			case 4:
				
				trader_id = in.nextInt();
				money_amount = in.nextDouble();
				if (!traders.get(trader_id).getWallet().withdraw(money_amount)){
					invalid = invalid + 1;
				}
				
				break;
				
			case 5:
				
				trader_id = in.nextInt();
				out.print("Trader ");
				out.print(trader_id);
				out.print(": ");
				out.printf("%.5f", traders.get(trader_id).getWallet().getDollars()+traders.get(trader_id).getWallet().getBlockedDollars());
				out.print("$ ");
				out.printf("%.5f", traders.get(trader_id).getWallet().getCoins()+traders.get(trader_id).getWallet().getBlockedCoins());
				out.print("PQ");
				out.println();
				break;
				
			case 777:
				
				for (int i=0; i<C; i++) {
					
					traders.get(i).getWallet().setCoins(traders.get(i).getWallet().getCoins() + myRandom.nextDouble()*10);
					
				}
					
				
				break;
				
			case 666:
				
				price = in.nextDouble();
				for(int i=0; i<C; i++) {
					market.makeOpenMarketOperation(price);
					market.checkTransactions(traders);
					
					}
				
				break;
				
			case 500:
				
				double totalBlockedDollars = 0;
				double totalBlockedCoins = 0;
				
				for (int i=0; i<C; i++) {
					
					totalBlockedDollars = totalBlockedDollars + traders.get(i).getWallet().getBlockedDollars();
					totalBlockedCoins = totalBlockedCoins + traders.get(i).getWallet().getBlockedCoins();
				}
				
				out.print("Current market size: ");
				out.printf("%.5f", totalBlockedDollars);
				out.print(" ");
				out.printf("%.5f", totalBlockedCoins);
				out.println();
				
				
				
				break;
				
			case 501:
				
				out.print("Number of successful transactions: ");
				out.print(market.getTransactions().size());
				out.println();
				
				break;
				
			case 502:
				
				out.print("Number of invalid queries: ");
				out.print(invalid);
				out.println();
				
				break;
				
			case 505:
				
				out.print("Current prices: ");
				if (market.getBuyingOrders().peek()==null) {
					out.printf("%.5f", cp_buying);
				}
				else {
					cp_buying = market.getBuyingOrders().peek().getPrice();
					out.printf("%.5f", cp_buying);
				}
				out.print(" ");
				if (market.getSellingOrders().peek()==null) {
					out.printf("%.5f", cp_selling);
				}
				else {
					cp_selling = market.getSellingOrders().peek().getPrice();
					out.printf("%.5f", cp_selling);
					}
				out.print(" ");
				out.printf("%.5f", (cp_buying+cp_selling)/2);
				out.println();
				
				
				break;
				
			case 555:
				
				for (int k=0; k<C; k++) {
					
					out.print("Trader ");
					out.print(k);
					out.print(": ");
					out.printf("%.5f", traders.get(k).getWallet().getDollars()+traders.get(k).getWallet().getBlockedDollars());
					out.print("$ ");
					out.printf("%.5f", traders.get(k).getWallet().getCoins()+traders.get(k).getWallet().getBlockedCoins());
					out.print("PQ");
					out.println();
				}
				
				break;
				
			
			}
		}
		
		
		
		
		in.close();
		out.close();
	
	}
	
}
