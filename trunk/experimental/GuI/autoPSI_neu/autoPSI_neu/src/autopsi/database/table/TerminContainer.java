package autopsi.database.table;


import net.jini.core.entry.Entry;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class TerminContainer extends GenericData implements Entry,GenericDataObject{

	public Integer id;
	public String title;
	public String description;
	public Integer group_id;

	
	public TerminContainer(){
		Class cl = this.getClass();
		try{
			this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));
			this.addAttribute("Titel",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));	
			this.addAttribute("Beschreibung",cl.getMethod("getDescription", new Class[] {}), cl.getMethod("setDescription", new Class[] {String.class} ));
			this.addAttribute("GruppenId",cl.getMethod("getGroupID", new Class[] {}), cl.getMethod("setGroupID", new Class[] {Integer.class} ));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Termin-Container-Objekts::"+e.toString());
		}
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getGroupID(){
		return this.group_id;
	}
	
	public void setGroupID(Integer newGroupId){
		this.group_id = newGroupId;
	}
}
