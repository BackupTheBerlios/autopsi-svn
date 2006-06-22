package autopsi.database.table;


import net.jini.core.entry.Entry;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class TerminKategorie extends GenericData implements Entry,GenericDataObject{
	
	public Integer id;
	public String name;
	
	
	public TerminKategorie(){
		Class cl = this.getClass();
		try{
			this.setObjectName("Terminkategorie");
			this.addAttribute("Id",cl.getMethod("getId", new Class[] {}), cl.getMethod("setId", new Class[] {Integer.class} ));
			this.addAttribute("Kategoriename",cl.getMethod("getName", new Class[] {}), cl.getMethod("setName", new Class[] {String.class} ));	}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Termin-Kategorie-Objekts::"+e.toString());
		}
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}