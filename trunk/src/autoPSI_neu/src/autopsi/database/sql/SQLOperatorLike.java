package autopsi.database.sql;

public class SQLOperatorLike extends SQLOperator {
	
	public SQLOperatorLike(SQLFields fields){
		super(fields);
		this.OPERATOR = " LIKE ";
	}
	
	public String getNext(){
		return fields.getCurrentName() + this.OPERATOR + "'" + fields.getCurrentValue() + "'";
	}
}
