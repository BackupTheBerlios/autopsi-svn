/**
 * filename: Main.java
 * description: testing application for the experimental JavaSpace project
 * @author: Rudolf Mildner
 */



import net.jini.core.lookup.*;
import net.jini.space.*;
import net.jini.discovery.LookupDiscoveryManager;
import net.jini.lookup.ServiceDiscoveryManager;
import net.jini.space.JavaSpace;
import net.jini.lookup.entry.Name;
import net.jini.core.entry.Entry;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;
import net.jini.core.entry.Entry;

import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionFactory;
import net.jini.core.transaction.server.TransactionManager;


import java.rmi.*;

public class SpaceTest {

	
	protected static ServiceTemplate template;
	protected static JavaSpace space = null;
	
	public static JavaSpace getSpace(String spaceName){
		try{
		JavaSpace space = null;
		
		Entry[] attr = new Entry[1];
		attr[0] = new Name(spaceName);
		Class[] types = new Class[1];
		types[0] = JavaSpace.class;
		template = new ServiceTemplate(null, types, null);
		
			if (System.getSecurityManager() == null){
				System.setSecurityManager(new SecurityManager());
			}
		
		try{
			System.out.println("hier simma");
			LookupDiscoveryManager ldm = new LookupDiscoveryManager(new String[] {""}, null, null);
			System.out.println("ldm ok");
			ServiceDiscoveryManager sdm = new ServiceDiscoveryManager(ldm, null);
			System.out.println("hier auch noch");
			ServiceItem o = sdm.lookup(template, null, 20000);
			if (o==null){
				System.out.println("o==null; Service not found");
			}
			else{
				System.out.println("Service found");
			}
			System.out.println("hier nicht mehr");
			space = ((JavaSpace)o.service);
		}
		catch(Exception e){
			System.out.println("Error beim Finden des JavaSpaces");
		}
		}
		catch (Exception e){
			System.out.println("Exception@getSpace=="+e.toString());
		}
		return space;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("autoPSI JavaSpace experimental Project testing\n");
		try{
			JavaSpace space = (JavaSpace)ServiceLocator.getService(JavaSpace.class, 10000);
			System.out.println("writing something into space");
			for (int i=0;i<18;i++){
				space.take(new HelloWorldMessage(), null, Lease.FOREVER);
			}
		}
		catch (Exception e){
			System.out.println("Error!=="+e.toString());
		}
	}

}
