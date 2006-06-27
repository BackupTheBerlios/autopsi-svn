package autopsi.gui.component;


import java.awt.Component;

/**
 * Class that a valid EditPlugin for GenericEditPanel will have to implement. 
 * @author Rudolf
 *
 */
public abstract class EditPlugin implements Cloneable{

	protected String name = "";
	
	/**
	 * Returns a component which allows editing of the value of the EditPlugin. 
	 * @return A component for graphic editing of the EditPlugin's value
	 */
	public abstract Component getEditor();
	
	/**
	 * Returns a component which allows showing of the value of the EditPlugin. 
	 * @return A component for graphical showing of the EditPlugin' s value. 
	 */
	public abstract Component getView();
	
	/**
	 * Returns the EditPlugin's value
	 * @return The EditPlugin' s value
	 */
	public abstract Object getValue();
	
	/**
	 * Sets the EditPlugin's value
	 * @param newValue The new value
	 */
	public abstract void setValue(Object newValue);
	
	/**
	 * Is called if the name(caption) of the value that the EditPlugin edits is changed. 
	 * This will often be used to draw the components correctly whe the name/caption of
	 * the edited value changes. 
	 *
	 */
	public abstract void nameChanged();
	
	/**
	 * Sets the name of the edited value. 
	 * @param newName The new name
	 */
	public void setName(String newName){
		this.name = newName;
		nameChanged();
	}
	
	/**
	 * Returns the name of the edited value
	 * @return The name of the edited value
	 */
	public String getName(){
		return this.name;
	}
	
}
