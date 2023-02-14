
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public abstract class Container implements Comparable<Container> {
	
	public int ID;
	public int weight;

	public Container(int Id, int Weight) {
		ID = Id;
		weight = Weight;
	}

	
	public abstract double consumption();
	
	
	public int compareTo(Container other) {
		return ID-other.ID;
	}
	
	
	public boolean equals(Container other) {
		return (this.ID == other.ID && this.weight == other.weight);
	}
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

