
package question;

public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	
	private int ID;
	private double talkingCharge;
	private double messageCost;
	private double networkCharge;
	private int discountRate;
	private int totaltalk;
	private int totalmessage;
	private double totalnetwork;
	
	
	public Operator(int id, double tCharge, double mCost, double nCharge, int drate) {
		ID = id;
		talkingCharge = tCharge;
		messageCost = mCost;
		networkCharge = nCharge;
		discountRate = drate;
		totaltalk=0;
		totalmessage=0;
		totalnetwork=0;
	}
	
	



	public double calculateTalkingCost(int minute, Customer customer) {
		if ((customer.getAge() <18) || (customer.getAge() > 65)) {
			return talkingCharge * (1-((double)discountRate/100)) * minute;
		}
		else {
			return (minute * talkingCharge);
		}
	}
	
	public double calculateMessageCost(int quantity, Customer customer, Customer other) {
		
		if (customer.getOperator().ID == other.getOperator().ID) {
			return messageCost * (1-((double)discountRate/100)) * quantity;
		}
		else {
			return (quantity * messageCost);
		}
	}
	 
	public double calculateNetworkCost(double amount) {
		return (amount * networkCharge);
	}
	
	public void addTotalTalk(int minute) {
		totaltalk = totaltalk + minute; 
	}
	
	public void addTotalMessage(int amount) {
		totalmessage = totalmessage + amount; 
	}
	
	public void addTotalNetwork(double amount) {
		totalnetwork = totalnetwork + amount; 
	}

	
	
	
	
	
	
	public int getTotaltalk() {
		return totaltalk;
	}

	public int getTotalmessage() {
		return totalmessage;
	}

	public double getTotalnetwork() {
		return totalnetwork;
	}
	
	 
	public double getTalkingCharge() {
		return talkingCharge;
	}

	public void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}

	public double getMessageCost() {
		return messageCost;
	}

	public void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}

	public double getNetworkCharge() {
		return networkCharge;
	}

	public void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

