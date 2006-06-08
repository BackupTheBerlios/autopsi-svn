package autopsi.gui.component;


import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;

public class GenericData {
	
	protected Map<String, GSMethod> map = new HashMap<String, GSMethod>();
	
	protected void addAttribute(String attribName, Method getAttrib, Method setAttrib){
		GSMethod gsm = new GSMethod();
		gsm.getMethod = getAttrib;
		gsm.setMethod = setAttrib;
		map.put(attribName, gsm);
	}

	public Object getAttrib(String name){
		GSMethod gsm = map.get(name);
		try{
			return gsm.getMethod.invoke(this, new Object[] {name});
		}
		catch (Exception e){
			System.out.println("Exception :: "+e.toString());
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
			System.out.println("Exception :: "+e.toString());
		}
	}
	
}
