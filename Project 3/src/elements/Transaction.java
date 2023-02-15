package elements;

/**
 * the class in which the Transaction objects are created
 * @author bilal
 *
 */
public class Transaction {
	
	private SellingOrder sellingOrder;

	private BuyingOrder buyingOrder;
	
	/**
	 * This generates a Transaction object and sets default or given (by sellingOrder,buyingOrder) values to the properties
	 * @param selOrder is sellingOrder object
	 * @param buyOrder is buyingOrder object
	 */
	public Transaction(SellingOrder selOrder,BuyingOrder buyOrder) {
		
		sellingOrder = selOrder;
		buyingOrder = buyOrder;
	}

	

}
