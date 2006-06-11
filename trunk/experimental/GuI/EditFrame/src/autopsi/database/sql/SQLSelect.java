package autopsi.database.sql;

public class SQLSelect implements SQLStatement {

	protected SQLTable table;
	protected SQLFields fields;
	protected SQLWhere where;
	
	public SQLSelect(SQLTable table, SQLFields fields){
		this.table = table;
		this.fields = fields;
	}
	
	public SQLSelect(SQLTable table, SQLFields fields, SQLWhere where){
		this.SQLSelect(table, fields);
		this.where = where;
	}
	
	public String getQuery() {
		String ret = "SELECT * FROM "+table.getQuery();
		boolean hasWhere = false;
		fields.beginTraversal();
		while(fields.next()){
			if (!hasWhere){
				hasWhere = true;
				ret=ret+" WHERE ";
			}
			else
				ret=ret+" AND ";
			
			ret=ret+fields.getCurrentName()+"="+fields.getCurrentValue();
		}
		return ret;
	}

}
