package autopsi.gui.component;


import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.lang.reflect.Method;

public class GenericData implements Cloneable{
	
	protected Map<String, GSMethod> map = new HashMap<String, GSMethod>();
	
	protected void addAttribute(String attribName, Method getAttrib, Method setAttrib){
		GSMethod gsm = new GSMethod();
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
			System.out.println("Exception :: "+e.toString());
		}
		return null;
	}
//	
//	public Map<String, GSMethodPrimary> getAllPrimary(){
//		Map<String, GSMethodPrimary> result = new Map<String, GSMethodPrimary>();
//		Set<String> s = map.keySet();
//		Iterator<String> iter = s.iterator();
//		while(iter.hasNext()){
//			String key = iter.next();
//			GSMethod meth = map.get(key);
//			if (meth instanceof GSMethodPrimary)
//				result.put(key, (GSMethodPrimary)meth);
//		}
//		return result;
//	}
//	
//	public Map<String, GSMethodForeign> getAllForeign(){
//		
//	}
	
	
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
	
	public int getAttribCount(){
		return map.size();
	}
	
	public Object clone(){
		try{
			return super.clone();
		}
		catch (Exception e){
			System.out.println("Konnte GenericDataObject nicht klonen!::"+e.toString());
		}
		return null;
	}
	
}
