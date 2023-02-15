
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		
		
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		
		int option;
		int customerIndex = 0;
		int operatorIndex = 0;
		String name;
		int age;
		int opId;
		double amount;
		
		double tcharge;
		double cost;
		double ncharge;
		int drate;
		
		int firstcustomer;
		int secondcustomer;
		int time;
		int messageamount;
		double interamount;
		double payamount;
		int operatorInd;
		double newbill;
		
		for (int i=0; i<N; i++) {
			
			option = reader.nextInt();
			
			switch (option) {
				
			case 1:
				name = reader.next();
				age = reader.nextInt();
				opId = reader.nextInt();
				amount = reader.nextDouble();
						
				customers[customerIndex] = new Customer( customerIndex,  name, age , operators[opId], amount);
	
				customerIndex++;
				
				break;
			
			case 2:
				
				tcharge = reader.nextDouble();
				cost = reader.nextDouble();
				ncharge = reader.nextDouble();
				drate = reader.nextInt();
				
				
				operators[operatorIndex] = new Operator( operatorIndex, tcharge, cost, ncharge, drate);
				
				operatorIndex++;
				
				break;
				
			case 3:
				firstcustomer = reader.nextInt();
				secondcustomer = reader.nextInt();
				time = reader.nextInt();
				
				customers[firstcustomer].talk(time, customers[secondcustomer]);
				
				break;
				
			case 4:
				firstcustomer = reader.nextInt();
				secondcustomer = reader.nextInt();
				messageamount = reader.nextInt();
				
				customers[firstcustomer].message(messageamount, customers[secondcustomer]);
				
				break;
				
			case 5:
				firstcustomer = reader.nextInt();
				interamount = reader.nextDouble();
				
				customers[firstcustomer].connection(interamount);
				
				break;
				
			case 6:
				firstcustomer = reader.nextInt();
				payamount = reader.nextDouble();
				
				customers[firstcustomer].getBill().pay(payamount);
				
				break;
				
			case 7:
				firstcustomer = reader.nextInt();
				operatorInd = reader.nextInt();
				
				customers[firstcustomer].setOperator(operators[operatorInd]);
				
				break;
				
			case 8:
				firstcustomer = reader.nextInt();
				newbill = reader.nextDouble();
				
				if (newbill>customers[firstcustomer].getBill().getCurrentDebt()) {
				customers[firstcustomer].getBill().setLimitingAmount(newbill);
				}
				break;
			}	
			
		}
		
		for (int k=0; k<O; k++) {
			outstream1.print("Operator " + k + " : " + operators[k].getTotaltalk() + " " + operators[k].getTotalmessage() + " ");
			outstream1.printf("%.2f",operators[k].getTotalnetwork());
			outstream1.println();
		}
		
		int maxtalk = 0;
		int maxmessage = 0;
		double maxnetwork = 0;
		
		String mtalk = "a";
		String mmessage = "a";
		String mnetwork = "a";
		 
		for(int j=0; j<C; j++) {
			outstream1.print("Customer " + j + " : ");
			outstream1.printf("%.2f",customers[j].getBill().getTotalpay());
			outstream1.print(" ");
			outstream1.printf("%.2f",customers[j].getBill().getCurrentDebt() );
			outstream1.println();
			
			if (customers[j].getTalkminute()>maxtalk) {
				maxtalk = customers[j].getTalkminute();
				mtalk = customers[j].getName();
			}
			
			if (customers[j].getMessageamount()>maxmessage) {
				maxmessage = customers[j].getMessageamount();
				mmessage = customers[j].getName();
			}
			
			if (customers[j].getNetworkamount()>maxnetwork) {
				maxnetwork = customers[j].getNetworkamount();
				mnetwork = customers[j].getName();
			}
			
		}
		
		outstream1.println(mtalk + " : "+ maxtalk);
		outstream1.println(mmessage + " : "+ maxmessage);
		outstream1.print(mnetwork + " : ");
		outstream1.printf("%.2f",maxnetwork);
		
		
		

		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
}

