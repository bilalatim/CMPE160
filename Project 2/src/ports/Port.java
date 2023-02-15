
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;


import java.util.ArrayList;
import java.util.Collections;

import containers.*;
import interfaces.IPort;
import ships.*;

public class Port implements IPort {
	
	public int ID;
	public double X;
	public double Y;
	public ArrayList<Container> containers;
	


	ArrayList<Ship> history;
	public ArrayList<Ship> current;
	

	public Port(int id, double x, double y) {
		ID = id;
		X = x;
		Y =y;
		containers = new ArrayList<Container>();
		history = new ArrayList<Ship>();
		current = new ArrayList<Ship>();
	}
	
	public double getDistance(Port other) {
		return Math.sqrt(Math.pow(other.X - this.X, 2) + Math.pow(other.Y - this.Y, 2));
	}
	
	
	public void incomingShip(Ship s) {
		if (!current.contains(s)) {
			current.add(s);
		}	
	}
	
	
	public void outgoingShip(Ship s) {	
		current.remove(s);
		if (!history.contains(s)) {
			history.add(s);
		}
	}
	
	
	public void get(Container cont) {
		if (!containers.contains(cont)) containers.add(cont);
	}
	
	
	public void give(Container cont) {
		containers.remove(cont);
	}
	
	
	public ArrayList<Ship> getCurrent() {
		Collections.sort( current);
		return current;
	}
	
	public ArrayList<Container> getContainers() {
		Collections.sort( containers);
		return containers;
	}

	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

