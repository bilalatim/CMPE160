package elements;

/**
 * this class exists to create and sort SellingOrder objects.
 * @author bilal
 *
 */
public class SellingOrder extends Order implements Comparable {

	/**
	 * This generates a SellingOrder object and sets default or given (by parameters) values to the properties.
	 * @param tID is Trader's ID.
	 * @param amnt is amount of PQoin.
	 * @param prc is price of one PQoin.
	 */
	public SellingOrder(int tID, double amnt, double prc) {
		super(tID, amnt, prc);
	
	}

	/**
	 * this method sorts objects first by price and then by quantity.
	 */
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof SellingOrder) {
			SellingOrder other = (SellingOrder)o;
			if(price > other.price ) {
				return 1;
			}
			else if (price < other.price ) {
				return -1;
			}
			else {
				if (amount > other.amount) {
					return 1;
				}
				else if (amount < other.amount) {
					return -1;
				}
				else {
					if (traderID < other.traderID) {
						return 1;
					}
					else {
						return -1;
					}
				}
			}
		}	
		return 0;
	}
	
	

}
