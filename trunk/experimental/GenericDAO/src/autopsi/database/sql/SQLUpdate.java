package autopsi.database.sql;

public class SQLUpdate implements SQLStatement {

	protected SQLTable table = null;
	protected SQLFields setFields = null;
	protected SQLWhere where = null;
	
	
	public SQLUpdate(SQLTable table, SQLFields whereFields, SQLFields setFields){
		this.table = table;
		this.setFields = setFields;
		this.where = new SQLWhere();
		SQLConjunctor con = new SQLConjunctorAnd();
		SQLOperator op = new SQLOperatorEquals(whereFields);
		con.addOperator(op);
		this.where.addConjunctor(con);
	}
	
	public SQLUpdate(SQLTable table, SQLWhere where, SQLFields setFields){
		this.table = table;
		this.where = where;
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
			
			ret=ret+setFields.getCurrentName()+"="+"'"+setFields.getCurrentValue()+"'";
		}
		ret = ret + " ";
		this.where.beginTraversal();
		ret = ret + this.where.getQuery();
		return ret;
	}

}
