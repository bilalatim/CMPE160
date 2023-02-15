
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;
import java.util.Collections;
import containers.*;
import interfaces.IShip;
import ports.*;

/**
 * This class has ship objects, their variables and methods. Also it implements an interfaces which is IShip.
 * @author bilal
 *
 */
public class Ship implements IShip , Comparable<Ship> {
	
	
	public int ID;
	public double fuel = 0.00;
	public Port currentPort;
	int TWeightCapacity;
	int FirstWeight = 0;
	int MNumberOfAllContainers;
	int FirstNumberOfAllContainers = 0;
	int MNumberOfHeavyContainers;
	int FirstNumberOfHeavyContainers = 0;
	int MNumberOfRefContainers;
	int FirstNumberOfRefContainers = 0;
	int MNumberOfLiquidContainers;
	int FirstNumberOfLiquidContainers = 0;
	double FConsumptionPerKM;
	
	public ArrayList<Container> currentContainers;
	

	/**
	 * This generates a ClassName object and sets default or given (by parameters) values to the properties.
	 * @param id is Ship ID. Every Ship has different ID starting from 0.
	 * @param p is port object where the ship is located.
	 * @param totalWeightCapacity is maximum weight limit for containers that the ship can carry.
	 * @param maxNumberOfAllContainers is maximum number of containers the ship can carry.
	 * @param maxNumberOfHeavyContainers is maximum number of heavy containers that the ship can carry.
	 * @param maxNumberOfRefrigeratedContainers is maximum number of frozen type of containers that the ship can carry.
	 * @param maxNumberOfLiquidContainers is maximum number of liquid containers that the ship can carry.
	 * @param fuelConsumptionPerKM is the amount of fuel consumed by the ship per kilometer
	 */
	public Ship(int id, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		
		ID = id;
		currentPort = p;
		TWeightCapacity = totalWeightCapacity;
		MNumberOfAllContainers = maxNumberOfAllContainers;
		MNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		MNumberOfRefContainers = maxNumberOfRefrigeratedContainers;
		MNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		FConsumptionPerKM = fuelConsumptionPerKM;
		
		currentContainers = new ArrayList<Container>();

	}
	
	
	public boolean sailTo(Port p) {
		
		double distance = Math.abs( Math.sqrt(Math.pow(p.X - this.currentPort.X, 2) + Math.pow(p.Y - this.currentPort.Y, 2)));
		double totalconsump = 0.0;
		
		for (Container c : currentContainers) {
			totalconsump = totalconsump + c.consumption();	
		}
		if (fuel >= (FConsumptionPerKM * distance + totalconsump)) {
			
			fuel = fuel - ((FConsumptionPerKM * distance) + totalconsump);
			
			return true;
		}
		else return false;
	}
	
	
	public void changePort(Port p) {
		this.currentPort = p;
	}
		
	
	public void reFuel(double newFuel) {
		fuel = this.fuel + newFuel;
	}
	
	
	public boolean load(Container cont) {
		if (this.currentPort.containers.contains(cont)) {
			if (TWeightCapacity >= FirstWeight + cont.weight) {
				if(MNumberOfAllContainers >= (FirstNumberOfAllContainers + 1)) {
					
					if(cont instanceof LiquidContainer && MNumberOfLiquidContainers >= (FirstNumberOfLiquidContainers + 1) && MNumberOfHeavyContainers >= (FirstNumberOfHeavyContainers + 1)) {
						
						FirstWeight = FirstWeight + cont.weight;
						FirstNumberOfAllContainers = FirstNumberOfAllContainers + 1;
						FirstNumberOfLiquidContainers = FirstNumberOfLiquidContainers + 1;
						FirstNumberOfHeavyContainers = FirstNumberOfHeavyContainers + 1;
						currentContainers.add(cont);
						return true;
					}
					else if(cont instanceof RefrigeratedContainer && MNumberOfRefContainers >= FirstNumberOfRefContainers + 1 && MNumberOfHeavyContainers >= FirstNumberOfHeavyContainers + 1) {
						FirstWeight = FirstWeight + cont.weight;
						FirstNumberOfAllContainers = FirstNumberOfAllContainers + 1;
						FirstNumberOfRefContainers = FirstNumberOfRefContainers + 1;
						FirstNumberOfHeavyContainers = FirstNumberOfHeavyContainers + 1;
						currentContainers.add(cont);
						return true;
					}
					else if(cont instanceof HeavyContainer && MNumberOfHeavyContainers >= FirstNumberOfHeavyContainers + 1 && !(cont instanceof RefrigeratedContainer) && !(cont instanceof LiquidContainer)) {
						FirstWeight = FirstWeight + cont.weight;
						FirstNumberOfAllContainers = FirstNumberOfAllContainers + 1;
						FirstNumberOfHeavyContainers = FirstNumberOfHeavyContainers + 1; 
						currentContainers.add(cont);
						return true;
					}
					else if(cont instanceof BasicContainer) {
						FirstWeight = FirstWeight + cont.weight;
						FirstNumberOfAllContainers = FirstNumberOfAllContainers + 1;
						currentContainers.add(cont);
						return true;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	
	public boolean unLoad(Container cont) {
		if (currentContainers.contains(cont)) {
			if(cont instanceof LiquidContainer ) {
				FirstWeight = FirstWeight - cont.weight;
				FirstNumberOfAllContainers = FirstNumberOfAllContainers - 1;
				FirstNumberOfLiquidContainers = FirstNumberOfLiquidContainers - 1;
				currentContainers.remove(cont);
				return true;
			}
			else if(cont instanceof RefrigeratedContainer ) {
				FirstWeight = FirstWeight - cont.weight;
				FirstNumberOfAllContainers = FirstNumberOfAllContainers - 1;
				FirstNumberOfRefContainers = FirstNumberOfRefContainers - 1;
				currentContainers.remove(cont);
				return true;
			}
			else if(cont instanceof HeavyContainer ) {
				FirstWeight = FirstWeight - cont.weight;
				FirstNumberOfAllContainers = FirstNumberOfAllContainers - 1;
				FirstNumberOfHeavyContainers = FirstNumberOfHeavyContainers - 1; 
				currentContainers.remove(cont);
				return true;
			}
			else if(cont instanceof BasicContainer) {
				FirstWeight = FirstWeight - cont.weight;
				FirstNumberOfAllContainers = FirstNumberOfAllContainers - 1;
				currentContainers.remove(cont);
				return true;
			}
			else return false;
		}
		else return false;
	}

	public int compareTo(Ship other) {
		return ID-other.ID;
	}

	public ArrayList<Container> getCurrentContainers() {
		Collections.sort(currentContainers);
		return currentContainers;
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

