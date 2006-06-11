package autopsi.database.sql;


import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class SQLConjunctor extends SQLStatement {

	List<SQLOperator> operators;
	
	
	public void addOperator(SQLOperator oper) {
		operators.add(oper);
	}
	
	public String getQuery(){
		return "";
	}
}
