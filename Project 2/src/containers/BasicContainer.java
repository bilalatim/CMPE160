
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class BasicContainer extends Container {
	
	public BasicContainer(int ID, int weight) {
		
		super(ID,weight);
		
	}
	
	@Override
	public double consumption() {
		
		return weight*2.50;
	}

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

