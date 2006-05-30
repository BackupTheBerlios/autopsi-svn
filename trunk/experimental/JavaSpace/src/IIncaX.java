

import java.rmi.Remote;
import java.rmi.RemoteException;
//Jini admin interfaces
import com.sun.jini.admin.DestroyAdmin;
import net.jini.admin.JoinAdmin;
import net.jini.admin.Administrable;

public interface IIncaX
	extends JoinAdmin,DestroyAdmin,Administrable,Remote{
	
	public String sayHelo() throws RemoteException;
	
	//example method
	public void ping() throws RemoteException;
}
