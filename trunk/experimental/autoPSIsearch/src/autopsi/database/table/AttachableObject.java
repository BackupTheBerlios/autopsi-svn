package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class AttachableObject implements GenericDataObject {

	
	private Integer id;
	private Integer kategorie_id;
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(int kategorieId){
		this.kategorie_id = kategorieId;
	}

}
