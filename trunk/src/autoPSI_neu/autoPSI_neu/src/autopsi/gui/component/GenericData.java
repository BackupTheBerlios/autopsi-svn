package autopsi.gui.component;


import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;


public class GenericData implements Cloneable{
	
	protected Map<String, GSMethod> map;
	protected String objectName = "";
	
	
	public GenericData(){
		map = new HashMap<String, GSMethod>();
	}
	
	public String getObjectName(){
		return this.objectName;
	}
	
	public void setObjectName(String newObjName){
		this.objectName = newObjName;
	}
	
	protected void addAttribute(String attribName, Method getAttrib, Method setAttrib){
		GSMethodNormal gsm = new GSMethodNormal();
		gsm.getMethod = getAttrib;
		gsm.setMethod = setAttrib;
		map.put(attribName, gsm);
	}

	protected void addAttribute(String attribName, GSMethod gsm){
		map.put(attribName, gsm);
	}
	
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


	public Map<String, GSMethod> getAllAttribs(){
		return map;
	}
	
	public void setAttrib(String name, Object newValue){
		GSMethod gsm = map.get(name);
		try{
			gsm.getMethod.invoke(this, new Object[] { name, newValue});
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public int getAttribCount(){
		return map.size();
	}
	
	public Object clone(){
		try{
			return super.clone();
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(),"Error!",JOptionPane.ERROR_MESSAGE);
			}
		return null;
	}
	
	public void onAdd(){
		
	}
	
	public void onUpdate(){
		
	}
	
	public void onDelete(){
		
	}
	
}
