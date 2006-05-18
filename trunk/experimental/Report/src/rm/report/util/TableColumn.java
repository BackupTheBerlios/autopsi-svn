package rm.report.util;

public class TableColumn{
	
	private String name;
	private boolean isEditable;
	private String owner;

	public TableColumn(String name, boolean isEditable, String owner){
		this.name = name;
		this.isEditable = isEditable;
		this.owner = owner;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean getEditable(){
		return isEditable;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setEditable(boolean editable){
		this.isEditable = editable;
	}
	
	public void setOwner(String no){
		this.owner = no;
	}
	
	public String getOwner(){
		return this.owner;
	}
	
	
	public boolean equals(TableColumn tc){
		if (!this.getName().equals(tc.getName()))
			return false;
		if (!this.getEditable() == tc.getEditable())
			return false;
		if (this.getOwner().equals(tc.getOwner()))
			return false;
		
		return true;
	}
}