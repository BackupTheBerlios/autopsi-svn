package autopsi.database.sql;

public class SQLField{


	protected String name = null;
	protected String value = null;
	
	public SQLField(String fieldName, String value){
		this.name = fieldName;
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getValue(){
		return this.value;
	}

}
