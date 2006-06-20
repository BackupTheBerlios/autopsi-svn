package autopsi.database.table;

import net.jini.core.entry.Entry;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class Pruefung extends GenericData implements Entry,GenericDataObject {

	
	public Integer global_id;
	public Integer kategorie_id;
	public Integer lva_id;
	public String examiner;
	public Integer grade;
	
	
	public Pruefung(){
		Class cl = this.getClass();
		try{
			this.addAttribute("GlobalId",cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {Integer.class} ));
			this.addAttribute("KategorieId",cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {Integer.class} ));
			this.addAttribute("LvaId",cl.getMethod("getLvaId", new Class[] {}), cl.getMethod("setLvaId", new Class[] {Integer.class} ));
			this.addAttribute("Prüfer",cl.getMethod("getExaminer", new Class[] {}), cl.getMethod("setExaminer", new Class[] {String.class} ));
			this.addAttribute("Note",cl.getMethod("getGrade", new Class[] {}), cl.getMethod("setGrade", new Class[] {Integer.class} ));	}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Prüfungs-Objekts::"+e.toString());
		}
	}
	
	public Integer getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer globalId){
		this.global_id = globalId;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(Integer kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public Integer getLvaId(){
		return this.lva_id;
	}
	
	public void setLvaId(Integer lvaId){
		this.lva_id = lvaId;
	}
	
	public String getExaminer(){
		return this.examiner;
	}
	
	public void setExaminer(String examiner){
		this.examiner = examiner;
	}
	
	public Integer getGrade(){
		return this.grade;
	}
	
	public void setGrade(Integer grade){
		this.grade = grade;
	}
	
}
