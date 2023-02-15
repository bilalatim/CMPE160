package elements;

import elements.*;
import executable.Main;

/**
 * This class is the class where the objects that hold the dollars and coins of the traders are created
 * @author bilal
 *
 */
public class Wallet {
	
	private double dollars;

	private double coins;

	private double blockedDollars = 0;

	private double blockedCoins = 0;

	/**
	 * This generates a Wallet object and sets default or given (by dollars,coins) values to the properties
	 * @param dllrs is amount of dollars traders have
	 * @param cns is amount of coins traders have
	 */
	public Wallet(double dllrs, double cns) {
		
		dollars = dllrs;
		coins = cns;
	}
	
	/**
	 * deposit method
	 * @param new_dollar is amount to be deposited
	 */
	public void deposit(double new_dollar) {
		
		dollars = dollars + new_dollar; 
		
	}
	
	/**
	 * withdrawal method
	 * @param new_dollar is amount of money to be withdrawn
	 * @return for the transaction successful or unsuccessful
	 */
	public boolean withdraw(double new_dollar) {
		
		if (dollars - new_dollar >= 0) {
			dollars = dollars - new_dollar;
			return true;
		}
		else return false;
	}

	/**
	 * money blocking method
	 * @param amount is amount of money blocked
	 * @return for traders have enough money
	 */
	public boolean blockDollar(double amount) {
		if (dollars - amount>=0) {
			dollars = dollars - amount;
			blockedDollars = blockedDollars + amount;
			return true;
		}
		else return false;
	}
	
	/**
	 * Coin blocking method
	 * @param amount is amount of coin blocked
	 * @return for traders have enough coin
	 */
	public boolean blockCoin(double amount) {
		if (coins - amount>=0) 
		coins = coins - amount;
		blockedCoins = blockedCoins + amount;
		return true;
	}
	
	/**
	 * method of post-trade dollars transactions
	 * @param amount coins amount trader buy
	 * @param price dollars amount of trader pay
	 * @return for process successful or not
	 */
	public boolean remBlockDollar(double amount, double price) {
			coins = coins + amount;
			blockedDollars = blockedDollars - price;
			return true;
	}
	
	/**
	 * method of post-trade coins transactions
	 * @param amount is coins amount trader pay
	 * @param price is dollars amount of trader get
	 * @return for process successful or not
	 */
	public boolean remBlockCoin(double amount, double price) {
		dollars = dollars + price;
		blockedCoins = blockedCoins - amount;
		return true;
	}
	
	
	public double getDollars() {
		return dollars;
	}

	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	public double getCoins() {
		return coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	public double getBlockedDollars() {
		return blockedDollars;
	}

	public double getBlockedCoins() {
		return blockedCoins;
	}
	
	
	
}
