package elements;

/**
 * This class in which trader objects and wallets are created
 * @author bilal
 *
 */
public class Trader {
	
	private int id;

	private Wallet wallet;
	
	
	public static int numberOfUsers = 0;

	/**
	 * This generates a Trader object and sets default or given (by dollars,coins) values to the properties
	 * @param dollars is amount of dollars in the trader's wallet
	 * @param coins is amount of coins in the trader's wallet
	 */
	public Trader(double dollars, double coins) {
		
		wallet = new Wallet(dollars, coins);
		
	}

	/**
	 * for sell operation
	 * @param amount is PQoin amount
	 * @param price is a PQoin price
	 * @param market is our market object
	 * @return integer
	 */
	public int sell(double amount, double price, Market market) {
		return 0;
	}

	/**
	 * for buy operation
	 * @param amount is PQoin amount
	 * @param price is a PQoin price
	 * @param market is our market object
	 * @return integer
	 */
	public int buy(double amount, double price, Market market) {
		return 0;
	}

	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	


}
