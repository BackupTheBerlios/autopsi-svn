package autopsi.gui.component;


import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

/**
 * Abstract object which all classes that should be editable by GenericEditPanel must implement. 
 * @author Rudolf
 *
 */
public class GenericData implements Cloneable{
	
	protected Map<String, GSMethod> map;
	protected String objectName = "";
	
	
	/**
	 * Initializes the GenericData - Object
	 *
	 */
	public GenericData(){
		map = new HashMap<String, GSMethod>();
	}
	
	/**
	 * Returns the name of the object
	 * @return The name of the object
	 */
	public String getObjectName(){
		return this.objectName;
	}
	
	/**
	 * Sets the name of the object (that name that will be shown to the user)
	 * @param newObjName The new name of the object
	 */
	public void setObjectName(String newObjName){
		this.objectName = newObjName;
	}
	
	/**
	 * Adds an attribute to the object; this is necessary to tell the GenericEditPanel 
	 * which attributes exist and how to set and get them. 
	 * @param attribName The name of the attribute; this will be possibly shown to the user
	 * @param getAttrib The method used to get the attribute
	 * @param setAttrib The method used to set the value
	 */
	protected void addAttribute(String attribName, Method getAttrib, Method setAttrib){
		GSMethodNormal gsm = new GSMethodNormal();
		gsm.getMethod = getAttrib;
		gsm.setMethod = setAttrib;
		map.put(attribName, gsm);
	}

	/**
	 * An overloaded version of addAttribute which gives the programmer more freedom of 
	 * selecting which sort of attribute he will insert into the object. 
	 * @param attribName The name the user will get to see
	 * @param gsm The GSMethod- Object describing getter and setter methods
	 */
	protected void addAttribute(String attribName, GSMethod gsm){
		map.put(attribName, gsm);
	}
	
	/**
	 * Returns the value of an attribute 
	 * @param name The name of the attribute
	 * @return
	 */
	public Object getAttrib(String name){
		GSMethod gsm = map.get(name);
		try{
			return gsm.getMethod.invoke(this, new Object[] {});
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		return null;
	}

	/**
	 * Returns a map of all attributes of the object
	 * @return A map of all attributes of the object
	 */
	public Map<String, GSMethod> getAllAttribs(){
		return map;
	}
	
	/**
	 * Sets the value of an attribute
	 * @param name The name of the attribute
	 * @param newValue The new value of the attribute
	 */
	public void setAttrib(String name, Object newValue){
		GSMethod gsm = map.get(name);
		try{
			gsm.getMethod.invoke(this, new Object[] { name, newValue});
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/**
	 * Returns the count of attributes
	 * @return The count of attributes
	 */
	public int getAttribCount(){
		return map.size();
	}
	
	/**
	 * Makes a flat copy of the object
	 */
	public Object clone(){
		try{
			return super.clone();
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		return null;
	}
	
	/**
	 * Executed if an object will be added. 
	 *
	 */
	public void onAdd(){
		
	}
	
	/**
	 * Executed if an object will be updated. 
	 *
	 */
	public void onUpdate(){
		
	}
	
	/**
	 * Executed if an object will be deleted
	 *
	 */
	public void onDelete(){
		
	}
	
}
