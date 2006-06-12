package autopsi.gui;
import java.sql.Date;

import autopsi.database.dao.GenericDataObject;

public class TData implements GenericDataObject{
	
	public Integer id = null;
	public String secondary_title = null;
	public String description = null;
	public Date date = null;
	public Integer duration = null;
	
}