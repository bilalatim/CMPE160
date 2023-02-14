
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;


public class LiquidContainer extends HeavyContainer {
	
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);	
	}
	
	
	@Override
	public double consumption() {
		return weight * 4.00;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

