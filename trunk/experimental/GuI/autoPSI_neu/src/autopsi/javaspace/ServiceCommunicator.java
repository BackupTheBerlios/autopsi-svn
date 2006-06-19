package autopsi.javaspace;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.lease.UnknownLeaseException;
import net.jini.core.transaction.TransactionException;
import net.jini.lease.DesiredExpirationListener;
import net.jini.lease.LeaseRenewalEvent;
import net.jini.lease.LeaseRenewalManager;
import net.jini.space.JavaSpace;

public class ServiceCommunicator implements IServiceCommunicator, DesiredExpirationListener {

	protected JavaSpace space = null;
	protected LeaseRenewalManager lrm = null;
	protected List<Lease> leases = null;
	protected long renewalTime = 5000;
	protected long timeout = 5000;
	protected String adress = "jini://localhost";

	
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
	
	public void setTimeout(long newTimeout){
		this.timeout = newTimeout;
	}
	
	public String getAdress(){
		return this.adress;
	}
	
	public void setAdress(String newAdress){
		this.adress = newAdress;
	}
	
	protected JavaSpace getSpace(){
		if (this.space == null)
		{
			JavaSpace sp = null;
			sp = (JavaSpace)ServiceFinder.getServiceFrom(JavaSpace.class, null, this.timeout, this.adress);
			this.space = sp;
		}
		
		return space;
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
			} catch (Exception e){
				System.out.println("ServiceCommunicator.addObject::Konnte Objekt nicht in den JavaSpace schreiben::"+e.toString());
			}
			lrm.renewUntil(newLease, Lease.FOREVER, this.renewalTime, this);
		}

	}

	public Entry getObject(Entry lookupObject) {
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

	public void delAllObjects() {
		Iterator<Lease> iter = leases.iterator();
		while(iter.hasNext()){
			Lease toRemove = iter.next();
			try {
				lrm.cancel(toRemove);
			} catch (Exception e){
				System.out.println("ServiceCommunicator.delAllObjects::Konnte Objekt durch Lease-Objekt nicht löschen::"+e.toString());
			}
		}

	}

	public void expirationReached(LeaseRenewalEvent arg0) {
		System.out.println("Erneuere Lease für Objekt");
	}

	public void notify(LeaseRenewalEvent arg0) {
		System.out.println("Konnte Lease für Objekt nicht erneuern!");
		
	}

}
