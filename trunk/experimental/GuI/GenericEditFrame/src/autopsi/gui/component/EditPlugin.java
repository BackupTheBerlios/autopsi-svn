package autopsi.gui.component;


import java.awt.Component;

public abstract class EditPlugin implements Cloneable{

	protected String name = "";
	
	
	public abstract Component getEditor();
	
	public abstract Component getView();
	
	public abstract Object getValue();
	
	protected abstract void setValue(Object newValue);
	
	public abstract void nameChanged();
	
	public void setName(String newName){
		System.out.println("setName");
		this.name = newName;
		System.out.println("trying to set name");
		nameChanged();
		System.out.println("name set!!");
	}
	
	public String getName(){
		return this.name;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}
