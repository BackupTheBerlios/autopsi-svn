package autopsi.javaspace;

import java.util.ArrayList;
import java.util.List;

import autopsi.database.table.Notiz;
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
	
	
	public void addObject(Entry newObject) {
		if (getSpace() == null){
			System.out.println("ServiceCommunicator.addObject::Konnte JavaSpace nicht finden");
		}
		else
		{
			Lease newLease = null; 
			try {
				newLease = space.write(newObject, null, this.renewalTime);
				leases.add(newLease);
			} catch (Exception e){
				System.out.println("ServiceCommunicator.addObject::Konnte Objekt nicht in den JavaSpace schreiben::"+e.toString());
			}
			lrm.renewUntil(newLease, Lease.FOREVER, this.renewalTime, this);
		}
//		this.space = null;

	}

	public Entry getObject(Entry lookupObject) {
		System.out.println("ServiceCommunicator.getOBject::started");
		Entry entry = null;
		if (getSpace() == null){
			System.out.println("ServiceCommunictor.getObject::Konnte JavaSpace nicht finden");
		}
		else
		{
			try {
				entry = space.read(lookupObject, null, this.timeout);
			} catch (Exception e){
				System.out.println("ServiceCommunicator.getObject::Konnte Objekt nicht aus dem JavaSpace lesen::"+e.toString());
			}
		}
		return entry;

	}

	public Entry getObjectConsuming(Entry lookupObject) {
		Entry entry = null;
		if (getSpace() == null){
			System.out.println("ServiceCommunictor.getObject::Konnte JavaSpace nicht finden");
		}
		else
		{
			try {
				entry = space.take(lookupObject, null, this.timeout);
			} catch (Exception e){
				System.out.println("ServiceCommunicator.getObject::Konnte Objekt nicht aus dem JavaSpace lesen::"+e.toString());
			}
		}
		return entry;

	}

	public void delAllOwnedObjects() {
		//don't renew leases so objects can be deleted
		this.lrm.clear();

	}

	public void expirationReached(LeaseRenewalEvent arg0) {
		System.out.println("Erneuere Lease für Objekt");
	}

	public void notify(LeaseRenewalEvent arg0) {
		System.out.println("Konnte Lease für Objekt nicht erneuern!");
		
	}

}
