package elements;

/**
 * Order is the parent class of BuyingOrder.java and SellingOrder.java. 
 * @author bilal
 *
 */
public class Order {
	double amount;

	double price;

	int traderID;

	/**
	 * This generates a Order object and sets default or given (by parameters) values to the properties.
	 * @param tID is Trader's ID.
	 * @param amnt is amount of PQoin.
	 * @param prc is price of one PQoin.
	 */
	public Order(int tID, double amnt, double prc) {
		amount = amnt;
		price = prc;
		traderID = tID;
		
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}
	

	
}
