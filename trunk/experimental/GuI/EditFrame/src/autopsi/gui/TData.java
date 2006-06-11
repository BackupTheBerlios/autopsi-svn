package autopsi.gui;
import java.sql.Date;

import autopsi.database.dao.GenericDataObject;

public class TData implements GenericDataObject{
	
	public int id;
	public String sec_title = null;
	public String description = null;
	public Date date = null;
	public int duration;

}