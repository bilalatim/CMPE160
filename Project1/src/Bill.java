
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	private double limitingAmount;
	private double currentDebt;
	private double totalpay;
	
	public Bill(double lAmount) {
		limitingAmount = lAmount;
		currentDebt = 0;
		totalpay = 0;
	}
	
	

	public boolean check(double amount) {
		if (currentDebt + amount > limitingAmount) {
			return false;
		}
		else {
			return true;	
		}
	}
	public void add(double amount) {
		currentDebt = currentDebt + amount;
	} 
	
	public void pay(double amount) {
		if (amount <= currentDebt) {
			currentDebt = currentDebt - amount;
			totalpay = totalpay + amount;
		}
		else {
			totalpay = totalpay + currentDebt;
			currentDebt = 0;
			
		}
	}
	
	//public void changeTheLimit(double amount) {
	//	if (amount >= currentDebt) {
	//		limitingAmount = amount;
	//	}
	//}
	
	
	public void setLimitingAmount(double limitingAmount) {
		this.limitingAmount = limitingAmount;
	}

	public double getTotalpay() {
		return totalpay;
	}
	
	public double getLimitingAmount() {
		return limitingAmount;
	}

	public double getCurrentDebt() {
		return currentDebt;
	}

	
	
	
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

