import structures.OrderStructC;
import central.*;

public class Test {
	public static void main(String[] args) {

	    // This example instantiates a Central and an Operator
	    // and sends a login Signal for that operator.
	    // Then the operator will submit a new order to the system.
		Central cen = new Central();
		Operator op = new Operator(cen);
		
		op.loginC(1,"aaa");
		OrderStructC order = new OrderStructC(-1,1,1,"Peter","nothing",4,7,false,0);
		op.newOrderC(order);
	}
}
