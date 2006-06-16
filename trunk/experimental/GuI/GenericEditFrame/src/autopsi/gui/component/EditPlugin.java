package autopsi.gui.component;


import java.awt.Component;

public abstract class EditPlugin implements Cloneable{

	protected String name = "";
	
	
	public abstract Component getEditor();
	
	public abstract Component getView();
	
	public abstract Object getValue();
	
	public abstract void setValue(Object newValue);
	
	public abstract void nameChanged();
	
	public void setName(String newName){
		this.name = newName;
		nameChanged();
		System.out.println("!!!name set!!=="+this.name);
	}
	
	public String getName(){
		return this.name;
	}
	
}
