package autopsi.javaspace;

import net.jini.core.entry.Entry;

public interface IServiceCommunicator {

	
	public void addObject(Entry newObject);
	
	public Entry getObject(Entry lookupObject);
	
	public Entry getObjectConsuming(Entry lookupObject);
	
	public void delAllOwnedObjects();
	
}
