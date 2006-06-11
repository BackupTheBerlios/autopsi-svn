package autopsi.database.sql;

import autopsi.database.sql.SQLStatement;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;


public class SQLWhere implements SQLStatement {

	boolean traversalStarted = false;
	List<SQLConjunctor> con;
	
	public SQLWhere(){
		con = new ArrayList<SQLConjunctor>();
	}
	
	
	public void addConjunctor(SQLConjunctor con){
		this.con.add(con);
	}
	
	public void beginTraversal(){
		this.traversalStarted = true;
	}
	
	public String getQuery(){
		if (!this.traversalStarted)
			return "";
		
		String ret = "";
		Iterator<SQLConjunctor> iter = this.con.iterator();
		boolean first = true;
		while(iter.hasNext()){
			SQLConjunctor c = iter.next();
			c.beginTraversal();
			String cq = c.getQuery();
			if ( (first) && (cq != "")){
				first = false;
				ret = ret + "WHERE";
			}
			ret = ret + " " + cq;
		}
		return ret;
	}
	
}
