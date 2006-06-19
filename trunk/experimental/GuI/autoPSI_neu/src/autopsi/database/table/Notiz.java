package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class Notiz extends GenericData implements Entry,GenericDataObject {

	
	public Integer global_id;
	public Integer kategorie_id;
	public String title;
	public String note;
	
	
	public Notiz(){
		Class cl = this.getClass();
		try{
			this.addAttribute("GlobalId",cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {Integer.class} ));
			this.addAttribute("KategorieId", cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {Integer.class}));
			this.addAttribute("Titel",cl.getMethod("getTitle", new Class[] {}), cl.getMethod("setTitle", new Class[] {String.class} ));
			this.addAttribute("Notiz",cl.getMethod("getNote", new Class[] {}), cl.getMethod("setNote", new Class[] {String.class} ));

		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Notiz-Objekts::"+e.toString());
		}
	}
	
	public int getGlobalId(){
		System.out.println("Notiz.getGlobalId()=="+global_id);
		return this.global_id;
	}
	
	public void setGlobalId(Integer globalId){
		this.global_id = globalId;
	}
	
	public int getKategorieId(){
		System.out.println("Notiz.getKategorieId()=="+kategorie_id);
		return this.kategorie_id;
	}
	
	public void setKategorieId(Integer kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public String getTitle(){
		System.out.println("Notiz.getTitle()=="+title);
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getNote(){
		System.out.println("Notiz.getNote()=="+note);
		return this.note;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
}
