package autopsi.database.sql;

public class SQLOperatorEquals extends SQLOperator {
	
	public SQLOperatorEquals(SQLFields fields){
		super(fields);
		this.OPERATOR = "=";
	}
}
