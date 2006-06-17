package autopsi.gui.frame;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class Notiz extends GenericData implements GenericDataObject {

	
	private Integer global_id;
	private String title;
	private String note;
	
	
	public Notiz(){
		Class cl = this.getClass();
		try{
			this.addAttribute("GlobalId",cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {Integer.class} ));
			this.addAttribute("Titel",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));
			this.addAttribute("Notiz",cl.getMethod("getNote", new Class[] {}), cl.getMethod("setNote", new Class[] {String.class} ));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Notiz-Objekts::"+e.toString());
		}
	}
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer globalId){
		this.global_id = globalId;
	}

	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getNote(){
		return this.note;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
}
