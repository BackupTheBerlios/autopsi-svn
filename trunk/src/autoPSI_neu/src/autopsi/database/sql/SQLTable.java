package autopsi.database.sql;

public class SQLTable implements SQLStatement {

	protected String tableName = null;
	
	public SQLTable(String tableName){
		this.tableName = tableName;
	}
	
	public String getQuery() {
		return tableName;
	}

}
