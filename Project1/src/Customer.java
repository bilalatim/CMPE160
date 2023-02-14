
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	private int ID;
	private String name;
	private int age;
	private Operator operator;
	private Bill bill;
	private int talkminute;
	private int messageamount;
	private double networkamount;
	
	public Customer(int id, String cname, int cage , Operator coperator, double lAmount) {
		ID = id;
		name = cname;
		age = cage;
		operator = coperator;
		bill = new Bill(lAmount);
		talkminute = 0;
		messageamount = 0;
		networkamount = 0;
	}
	
	

	public void talk(int minute, Customer other) {
		if (this.ID!=other.ID) {
			double cost;
			cost = operator.calculateTalkingCost(minute, this);
			if (bill.check(cost)) {
				bill.add(cost);
				
				operator.addTotalTalk(minute);
				other.getOperator().addTotalTalk(minute);
				
				talkminute = talkminute + minute;
				other.talkminute = other.talkminute + minute;
				
			}
		}
	}
	
	public void message(int quantity, Customer other) {
		if (this.ID!=other.ID) {
			double cost;
			cost = operator.calculateMessageCost(quantity, this, other);
			if (bill.check(cost)) {
				bill.add(cost);
				
				operator.addTotalMessage(quantity);
				
				messageamount = messageamount + quantity;
				
			}
		}
	}
	
	public void connection(double amount) {
		double cost;
		cost = operator.calculateNetworkCost(amount);
		if (bill.check(cost)) {
			bill.add(cost);
			
			operator.addTotalNetwork(amount);
			
			networkamount = networkamount + amount;
		}
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public int getTalkminute() {
		return talkminute;
	}

	public int getMessageamount() {
		return messageamount;
	}

	public double getNetworkamount() {
		return networkamount;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

