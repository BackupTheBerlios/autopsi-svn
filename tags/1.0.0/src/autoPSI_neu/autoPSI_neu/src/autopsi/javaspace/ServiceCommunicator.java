package autopsi.javaspace;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import autopsi.database.table.Notiz;
import autopsi.javaspace.exception.EServiceFailure;
import autopsi.javaspace.exception.ESpaceNotFound;
import net.jini.core.entry.Entry;
import net.jini.core.lease.Lease;
import net.jini.lease.DesiredExpirationListener;
import net.jini.lease.LeaseRenewalEvent;
import net.jini.lease.LeaseRenewalManager;
import net.jini.space.JavaSpace;

public class ServiceCommunicator implements IServiceCommunicator, DesiredExpirationListener {

	protected static JavaSpace space;
	protected LeaseRenewalManager lrm;
	protected List<Lease> leases;
	protected static long renewalTime = 5000;
	protected static long timeout = 5000;
	protected static String adress = "jini://localhost";

	
	public ServiceCommunicator(){
		lrm = new LeaseRenewalManager();
		leases = new ArrayList<Lease>();
	}
	
	public long getRenewalTime(){
		return this.renewalTime;
	}
	
	public void setRenewalTime(long renewalTime){
		this.renewalTime = renewalTime;
	}
	
	public long getTimeout(){
		return this.timeout;
	}
	
	public void setTimeout(long timeout){
		this.timeout = timeout;
	}
	
	public String getAdress(){
		return this.adress;
	}
	
	public void setAdress(String adress){
		this.adress = adress;
		space = getSpace();
	}
	
	protected static JavaSpace getSpace(){
		if (space == null)
		{
			JavaSpace sp = null;
			sp = (JavaSpace)ServiceFinder.getServiceFrom(JavaSpace.class, null, timeout, adress);
			space = sp;
		}
		
		return space;
	}
	
	public static boolean hasJavaSpace(){
		if (space != null)
			return true;
		else
			return false;
	}
	
	
	public void addObject(Entry newObject) throws ESpaceNotFound, EServiceFailure {
		if (getSpace() == null){
	
			throw new ESpaceNotFound();
		}
		else
		{
			Lease newLease = null; 
			try {
				newLease = space.write(newObject, null, this.renewalTime);
				leases.add(newLease);
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
			lrm.renewUntil(newLease, Lease.FOREVER, this.renewalTime, this);
		}


	}

	public Entry getObject(Entry lookupObject)  throws ESpaceNotFound, EServiceFailure{
		
		Entry entry = null;
		if (getSpace() == null){
		
			 throw new ESpaceNotFound();
		}
		else
		{
			try {
				entry = space.read(lookupObject, null, this.timeout);
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
		}
		return entry;

	}

	public Entry getObjectConsuming(Entry lookupObject)  throws ESpaceNotFound, EServiceFailure{
		Entry entry = null;
		if (getSpace() == null){
			
			throw new ESpaceNotFound();
		}
		else
		{
			try {
				entry = space.take(lookupObject, null, this.timeout);
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
		}
		return entry;

	}

	public void delAllOwnedObjects() {
		//don't renew leases so that objects will be deleted after renewalTime or less
		this.lrm.clear();

	}

	public void expirationReached(LeaseRenewalEvent arg0) {
	//	System.out.println("Erneuere Lease für Objekt");
	}

	public void notify(LeaseRenewalEvent arg0) {
	//	System.out.println("Konnte Lease für Objekt nicht erneuern!");
		
	}

}
