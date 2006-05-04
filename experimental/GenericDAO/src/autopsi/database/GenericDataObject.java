/**
 * filename: GenericDataObject.java
 * description: GenericDataObject that is able to store attributes
 * @author: Rudolf Mildner
 */

package autopsi.database;

import java.util.List;
import java.util.ArrayList;

import autopsi.database.attributes.GenericDataAttribute;
import autopsi.AutopsiException;

public class GenericDataObject {
	private List<GenericDataAttribute> attributes;
	
	public GenericDataAttribute getAttribute(String name){
		for (int i=0;i<attributes.size();i++)
			if (attributes.get(i).getName()==name)
				return attributes.get(i);
		return null;
	}
	
	/**
	 *
	 *ändert das Attribut mit dem Name name in newAttribute bzw. wird es neu hinzugefügt
	 * @param name der Name des Attributes
	 * @param newAttribute das Objekt welches das veränderte Attribut hält
	 */
	public void setAttribute(String name, GenericDataAttribute newAttribute) throws AutopsiException{
		for(int i=0;i<attributes.size();i++)
			if (attributes.get(i).getName()==name){
				if (!(attributes.get(i).getClass().equals(newAttribute.getClass())))
					throw new AutopsiException();
				attributes.remove(i);
				attributes.add(newAttribute);
				return;
			}
		
		attributes.add(newAttribute);
		
	}
}
