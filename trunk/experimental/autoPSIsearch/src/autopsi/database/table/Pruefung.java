package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class Pruefung implements GenericDataObject {

	
	private int global_id;
	private int kategorie_id;
	private int lva_id;
	private String examiner;
	private int grade;
	
	
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
