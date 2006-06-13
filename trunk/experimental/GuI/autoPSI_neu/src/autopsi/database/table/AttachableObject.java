package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;

public class AttachableObject implements GenericDataObject {

	
	private Integer id;
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

}
