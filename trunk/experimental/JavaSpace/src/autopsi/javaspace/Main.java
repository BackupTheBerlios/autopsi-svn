package autopsi.javaspace;


import net.jini.lease.DesiredExpirationListener;
import net.jini.lease.LeaseRenewalEvent;
import net.jini.lease.LeaseRenewalManager;
import net.jini.space.JavaSpace;
import net.jini.core.lease.Lease;

public class Main implements DesiredExpirationListener{

	
	public boolean haveNotToExit = true;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main of JavaSpace testing application");
//		JavaSpace space = null;
//		try{
//			//JavaSpace space = (JavaSpace)ServiceFinder.getService(JavaSpace.class, null, 3000);
//			space = (JavaSpace)ServiceFinder.getServiceFrom(JavaSpace.class, null, 3000, "jini://localhost");
//		}
//		catch (Exception e){
//			System.out.println("error getting JavaSpace::"+e.toString());
//		}
//		if (space!=null)
//			try{
//				HelloWorldMessage m = null;
//////			
//				m = new HelloWorldMessage();
//				m.setText(null);
////				Lease test = space.write(m, null, 5000);
////				test = space.write(m, null, 20000);
//////				LeaseRenewalManager lrm = new LeaseRenewalManager();
//////				Main meMyself = new Main();
//////				lrm.renewFor(test,20000,meMyself);
//////				while(meMyself.haveNotToExit){
//////					//busy waiting
//////				}
////////				lrm.cancel(test);
//////				
//				m = (HelloWorldMessage)space.take(m, null, 3000);
//				System.out.println("Message=="+m.toString());
//			}
//			catch(Exception e){
//				System.out.println("Error writing to javaspace::"+e.toString());
//			}
		
		
		
		
		
		SpaceTestFrame frame = new SpaceTestFrame();
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		
		
		System.out.println("finished");
	}

	public void expirationReached(LeaseRenewalEvent arg0) {
		System.out.println("expirationReached!");
		this.haveNotToExit = false;
		
	}

	public void notify(LeaseRenewalEvent arg0) {
		System.out.println("Couldn't renew lease");
		
	}

}
