package autopsi.database.sql;

public class SQLUpdate implements SQLStatement {

	private SQLTable table = null;
	private SQLFields whereFields = null;
	private SQLFields setFields = null;
	
	
	public SQLUpdate(SQLTable table, SQLFields whereFields, SQLFields setFields){
		this.table = table;
		this.whereFields = whereFields;
		this.setFields = setFields;
	}
	
	public String getQuery() {
		String ret = "UPDATE "+table.getQuery()+" SET ";
		boolean isFirst = true;
		setFields.beginTraversal();
		while(setFields.next()){
			if (!isFirst)
				ret=ret+",";
			else
				isFirst = false;
			
			ret=ret+setFields.getCurrentName()+"="+setFields.getCurrentValue();
		}
		isFirst = true;
		whereFields.beginTraversal();
		while(whereFields.next()){
			if (!isFirst)
				ret=ret+" AND ";
			else{
				isFirst = false;
				ret=ret+" WHERE ";
			}
			
			ret=ret+whereFields.getCurrentName()+"="+whereFields.getCurrentValue();
		}
		
		return ret;
	}

}
