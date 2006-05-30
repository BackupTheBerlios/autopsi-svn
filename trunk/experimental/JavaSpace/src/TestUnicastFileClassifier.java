

import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceItem;
import net.jini.core.lookup.ServiceRegistration;
import java.rmi.RMISecurityManager;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.space.JavaSpace;


public class TestUnicastFileClassifier {

    public TestUnicastFileClassifier() {
	LookupLocator lookup = null;
	ServiceRegistrar registrar = null;
	JavaSpace space = null;

        try {
            // lookup = new LookupLocator("jini://www.all_about_files.com");
            lookup = new LookupLocator("jini://62.178.163.228");
        } catch(java.net.MalformedURLException e) {
            System.err.println("Lookup failed: " + e.toString());
	    System.exit(1);
        }

	System.setSecurityManager(new RMISecurityManager());

	try {
	    registrar = lookup.getRegistrar();
	} catch (java.io.IOException e) {
            System.err.println("Registrar search failed: " + e.toString());
	    System.exit(1);
	} catch (java.lang.ClassNotFoundException e) {
            System.err.println("Registrar search failed: " + e.toString());
	    System.exit(1);
	}

	Class[] classes = new Class[] {JavaSpace.class};
	ServiceTemplate template = new ServiceTemplate(null, classes, null);
	try {
	    space = (JavaSpace) registrar.lookup(template);
	} catch(java.rmi.RemoteException e) {
	    e.printStackTrace();
	    System.exit(1);
	}

	if (space == null) {
	    System.out.println("Space null");
	    System.exit(2);
	}
	System.out.println("Java Space found!");
	System.exit(0);
    }
} // TestUnicastFileClassifier