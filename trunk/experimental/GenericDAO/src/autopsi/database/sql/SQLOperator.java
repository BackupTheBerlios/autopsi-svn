package autopsi.database.sql;


import java.util.List;
import java.util.Iterator;;

public class SQLOperator extends SQLStatement {

	protected List<SQLFields> fields;
	
	
	public SQLOperator(SQLFields fields){
		this.fields = fields;
	}
	
	public void next(){
		
	}
	
	public String getNext(){
		return "";
	}
	
}
