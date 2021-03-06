package autopsi.gui.component;


import java.awt.Component;

public abstract class EditPlugin implements Cloneable{

	protected String name = "";
	
	
	public abstract Component getEditor();
	
	public abstract Component getView();
	
	public abstract Object getValue();
	
	public abstract void setValue(Object newValue);
	
	public abstract void nameChanged();
	
	/**
	 * Setzt den Namen des zu bearbeitenden Objekts
	 * @param newName Der neue Name
	 */
	public void setName(String newName){
		this.name = newName;
		nameChanged();
	}
	
	public String getName(){
		return this.name;
	}
	
}
