package autopsi.javaspace;

import autopsi.javaspace.exception.EServiceFailure;
import autopsi.javaspace.exception.ESpaceNotFound;
import net.jini.core.entry.Entry;

public interface IServiceCommunicator {

	
	public void addObject(Entry newObject) throws ESpaceNotFound, EServiceFailure;
	
	public Entry getObject(Entry lookupObject) throws ESpaceNotFound, EServiceFailure;
	
	public Entry getObjectConsuming(Entry lookupObject) throws ESpaceNotFound, EServiceFailure;
	
	public void delAllOwnedObjects();
	
}
