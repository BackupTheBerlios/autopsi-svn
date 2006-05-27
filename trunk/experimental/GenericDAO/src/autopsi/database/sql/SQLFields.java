package autopsi.database.sql;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class SQLFields{

	private List<SQLField> fields = null;
	private Iterator<SQLField> iter = null;
	private SQLField current = null;
	private boolean traversal = false;
	
	public SQLFields(){
		fields = new ArrayList<SQLField>();
	}
	
	public boolean add(SQLField toAdd){
		if (!traversal){
			fields.add(toAdd);
			return true;
		}
		return false;
	}
	
	public void beginTraversal(){
		iter = fields.iterator();
		traversal = true;
	}
	
	public boolean next(){
		if (iter.hasNext()){
			current = iter.next();
			return true;
		}
		return false;
	}

	public String getCurrentName(){
		return current.getName();
	}
	
	public String getCurrentValue(){
		return current.getValue();
	}
}
