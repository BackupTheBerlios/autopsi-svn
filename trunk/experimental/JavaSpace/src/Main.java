

import net.jini.space.JavaSpace;
import net.jini.core.lease.Lease;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main of JavaSpace testing application");
		JavaSpace space = null;
		try{
			//JavaSpace space = (JavaSpace)ServiceFinder.getService(JavaSpace.class, null, 3000);
			space = (JavaSpace)ServiceFinder.getServiceFrom(JavaSpace.class, null, 3000, "jini://localhost");
		}
		catch (Exception e){
			System.out.println("error getting JavaSpace::"+e.toString());
		}
		if (space!=null)
			try{
				space.write(new HelloWorldMessage(), null, Lease.FOREVER);
			//	space.take(new HelloWorldMessage(), null, 3000);
			}
			catch(Exception e){
				System.out.println("Error writing to javaspace::"+e.toString());
			}
		System.out.println("finished");
	}

}
