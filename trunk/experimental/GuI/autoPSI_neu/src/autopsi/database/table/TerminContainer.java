package autopsi.database.table;


import autopsi.database.dao.GenericDataObject;

public class TerminContainer implements GenericDataObject{

	private Integer id = null;
	private String title = null;
	private String description = null;

	
	public Integer getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(Sting title){
		this.title = title;
	}
	
}
