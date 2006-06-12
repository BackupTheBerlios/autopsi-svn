package autopsi.database.sql;


import java.util.List;
import java.util.Iterator;;

public abstract class SQLOperator{

	boolean traversalStarted = false;
	protected SQLFields fields;
	protected static String OPERATOR = "";
	
	public SQLOperator(SQLFields fields){
		this.fields = fields;
	}
	
	public void beginTraversal(){
		this.traversalStarted = true;
		this.fields.beginTraversal();
	}
	
	public boolean hasNext(){
		if (this.fields.next())
			return true;
		else
			return false;
	}
	
	public String getNext(){
		return fields.getCurrentName() + this.OPERATOR + "'"+fields.getCurrentValue()+"'";
	}
	
}
