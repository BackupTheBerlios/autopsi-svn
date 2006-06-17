package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

public class Pruefung extends GenericData implements GenericDataObject {

	
	private Integer global_id;
	private Integer kategorie_id;
	private Integer lva_id;
	private String examiner;
	private Integer grade;
	
	
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
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(int globalId){
		this.global_id = globalId;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(int kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public int getLvaId(){
		return this.lva_id;
	}
	
	public void setLvaId(int lvaId){
		this.lva_id = lvaId;
	}
	
	public String getExaminer(){
		return this.examiner;
	}
	
	public void setExaminer(String examiner){
		this.examiner = examiner;
	}
	
	public int getGrade(){
		return this.grade;
	}
	
	public void setGrade(int grade){
		this.grade = grade;
	}
	
}
