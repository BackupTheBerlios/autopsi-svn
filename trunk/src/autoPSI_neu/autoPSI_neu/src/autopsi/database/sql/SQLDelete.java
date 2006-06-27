package autopsi.database.sql;

public class SQLDelete implements SQLStatement {

	protected SQLTable table = null;
	protected SQLWhere where = null;
	
	
	public SQLDelete(SQLTable table, SQLFields fields){
		this.table = table;
		this.where = new SQLWhere();
		SQLConjunctor con = new SQLConjunctorAnd();
		SQLOperator op = new SQLOperatorEquals(fields);
		con.addOperator(op);
		this.where.addConjunctor(con);
	}
	
	public SQLDelete(SQLTable table, SQLWhere where){
		this.table = table;
		this.where = where;
	}
	
	public String getQuery() {
		String ret="DELETE FROM "+table.getQuery() + " ";
		this.where.beginTraversal();
		ret = ret + this.where.getQuery();
		return ret;
	}

}
