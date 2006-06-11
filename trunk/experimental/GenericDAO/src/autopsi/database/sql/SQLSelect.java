package autopsi.database.sql;

public class SQLSelect implements SQLStatement {

	protected SQLTable table;
	protected SQLWhere where;
	
	public SQLSelect(SQLTable table, SQLFields fields){
		this.table = table;
		this.where = new SQLWhere();
		SQLConjunctor con = new SQLConjunctorAnd();
		SQLOperator op = new SQLOperatorEquals(fields);
		con.addOperator(op);
		this.where.addConjunctor(con);
	}
	
	public SQLSelect(SQLTable table, SQLWhere where){
		this.table = table;
		this.where = where;
	}
	
	public String getQuery() {
		String ret = "SELECT * FROM "+table.getQuery() + " ";
		/*boolean hasWhere = false;
		fields.beginTraversal();
		while(fields.next()){
			if (!hasWhere){
				hasWhere = true;
				ret=ret+" WHERE ";
			}
			else
				ret=ret+" AND ";
			
			ret=ret+fields.getCurrentName()+"="+fields.getCurrentValue();
		}*/
		this.where.beginTraversal();
		ret = ret + this.where.getQuery();
		return ret;
	}

}
