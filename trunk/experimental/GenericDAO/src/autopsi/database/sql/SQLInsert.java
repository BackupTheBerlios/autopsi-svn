package autopsi.database.sql;

public class SQLInsert implements SQLStatement {

	protected SQLTable table = null;
	protected SQLFields fields = null;
	
	
	public SQLInsert(SQLTable table, SQLFields fields){
		this.table = table;
		this.fields = fields;
	}
	
	public String getQuery() {
		String ret = "INSERT INTO "+table.getQuery()+" VALUES(";
		boolean isFirst = true;
		fields.beginTraversal();
		while(fields.next()){
			if(!isFirst)
				ret=ret+",";
			else
				isFirst = false;
			
			ret=ret+fields.getCurrentValue();
		}
		ret=ret+")";
		return ret;
	}

}
