package autopsi.database.sql;


import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public abstract class SQLConjunctor implements SQLStatement{

	boolean traversalStarted = false;
	List<SQLOperator> operators = new ArrayList<SQLOperator>();
	protected static String CONJUNCTOR = "";
	
	
	public void addOperator(SQLOperator oper) {
		operators.add(oper);
	}

	public void beginTraversal(){
		this.traversalStarted = true;
	}
	
	public String getQuery(){
		if (!this.traversalStarted)
			return "";
		
		String ret = "";
		Iterator<SQLOperator> iter = operators.iterator();
		while(iter.hasNext()){
			SQLOperator o = iter.next();
			o.beginTraversal();
			boolean isFirst = true;
			while(o.hasNext()){
				if (isFirst){
					isFirst=false;
				}
				else{
					ret = ret + " " + this.CONJUNCTOR + " ";
				}
				ret = ret + o.getNext();
			}
		}
		return ret;
	}
}
