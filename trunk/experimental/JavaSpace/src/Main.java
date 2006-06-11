

import net.jini.space.JavaSpace;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main of JavaSpace testing application");
		try{
			JavaSpace space = (JavaSpace)ServiceFinder.getService(JavaSpace.class, null, 3000);
		}
		catch (Exception e){
			System.out.println("error getting JavaSpace::"+e.toString());
		}
		System.out.println("finished");
	}

}
