package autopsi.database.sql;

public class SQLDelete implements SQLStatement {

	protected SQLTable table = null;
	protected SQLFields fields = null;
	
	
	public SQLDelete(SQLTable table, SQLFields fields){
		this.table = table;
		this.fields = fields;
	}
	
	public String getQuery() {
		String ret="DELETE FROM "+table.getQuery();
		boolean isFirst = true;
		fields.beginTraversal();
		while(fields.next()){
			if (!isFirst)
				ret=ret+",";
			else{
				isFirst = false;
				ret=ret+" WHERE ";
			}
			
			ret=ret+fields.getCurrentName()+"="+fields.getCurrentValue();
		}
		return ret;
	}

}
