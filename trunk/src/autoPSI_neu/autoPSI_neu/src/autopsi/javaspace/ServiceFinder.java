package autopsi.javaspace;
/**
 * filename: Main.java
 * description: testing application for the experimental JavaSpace project
 * @author: Rudolf Mildner
 */



import net.jini.discovery.LookupDiscovery;
import net.jini.core.discovery.LookupLocator;
import net.jini.discovery.DiscoveryListener;
import net.jini.discovery.DiscoveryEvent;
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
import java.net.MalformedURLException;


import java.rmi.*;

import javax.swing.JOptionPane;

public class ServiceFinder implements DiscoveryListener {

	
	private ServiceTemplate template;
	private Object lock = new Object();
	private Object proxy = null;
	
	
	protected void createServiceTemplate(Class serviceClass, String serviceName){
		Class[] types = new Class[] {serviceClass};
		Entry[] entry=null;
		if (serviceName != null){
			entry = new Entry[] { new Name(serviceName) };
		}
		template = new ServiceTemplate(null, types, entry);
	}
	
	public Object internalGetService(Class serviceClass, String serviceName, long waitTime) throws InterruptedException{
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		LookupDiscovery discovery = null;
		
		createServiceTemplate(serviceClass, serviceName);
		
		try{
			discovery = new LookupDiscovery(LookupDiscovery.ALL_GROUPS);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		discovery.addDiscoveryListener(this);
		synchronized(lock){
			lock.wait(waitTime);
		}
		discovery.terminate();
		if (proxy==null){
			JOptionPane.showMessageDialog(null, "Error: couldn't find service you are looking for","Error!",JOptionPane.ERROR_MESSAGE);
			
			throw new InterruptedException();
		}
		return proxy;
	}
		
	public Object internalGetService(Class serviceClass, String serviceName, long waitTime, String adress){
		LookupLocator lup = null;
		ServiceRegistrar reg = null;
		
		createServiceTemplate(serviceClass, serviceName);
		
		try{
			lup = new LookupLocator(adress);
		}
		catch (MalformedURLException e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		
		try{
			reg = lup.getRegistrar();
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		
		try{
			proxy = reg.lookup(template);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		
		return proxy;
	}
	
	public void discovered(DiscoveryEvent e){
		ServiceRegistrar[] registrars = e.getRegistrars();
		for (int i=0;i<registrars.length;i++){
			findService(registrars[i]);
		}
	}
	
	public void findService(ServiceRegistrar lookupService){
		try{
			synchronized(lock){
				proxy = lookupService.lookup(template);
				if (proxy != null)
					lock.notifyAll();
			}
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public void discarded(DiscoveryEvent e){
		
	}
	
	
	public static Object getService(Class serviceClass, String serviceName, long waitTime) throws InterruptedException{
		ServiceFinder st = new ServiceFinder();
		return st.internalGetService(serviceClass, serviceName, waitTime);
	}
	
	public static Object getServiceFrom(Class serviceClass, String serviceName, long waitTime, String adress){
		ServiceFinder st = new ServiceFinder();
		return st.internalGetService(serviceClass, serviceName, waitTime, adress);
	}
	

/*	public static void main(String[] args) {
		System.out.println("autoPSI JavaSpace experimental Project testing\n");
		try{
			JavaSpace space = (JavaSpace)getService(JavaSpace.class, null, 3000);
		//JavaSpace space = (JavaSpace)ServiceLocator.getService(JavaSpace.class, 10000);
			System.out.println("writing something into space");
			for (int i=0;i<12;i++){
				space.take(new HelloWorldMessage(), null, 500);
			}
		}
		catch (Exception e){
			System.out.println("Error@main :: "+e.toString());
		}
	}*/

}
