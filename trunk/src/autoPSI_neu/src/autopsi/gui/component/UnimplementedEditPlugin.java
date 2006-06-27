package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A Class that shows a graphical error msg that no EditPlugin is linked with that sort of Object (means: Class). 
 * @author Rudolf
 *
 */
public class UnimplementedEditPlugin extends EditPlugin {
	
	protected JPanel panel = null;
	protected JLabel missingLabel = null;
	
	protected final static String missingMsg = "Attribute cannot be edited: ";
	
	/**
	 * Initializes an UnimplementedEditPlugin
	 *
	 */
	public UnimplementedEditPlugin(){
		panel = new JPanel();
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setBackground(new Color(255,0,0));
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		missingLabel = new JLabel();
		panel.add(missingLabel);
		missingLabel.setForeground(new Color(255,0,0));
	}
	
	/**
	 * Returns the panel for editing
	 */
	public Component getEditor() {
		return panel;
	}

	/**
	 * Returns the panel for showing
	 */
	public Component getView() {
		return panel;
	}

	/**
	 * Returns the value of the EditPlugin
	 */
	public Object getValue() {
		return "";
	}

	/**
	 * Sets the value of the EditPlugin to newValue
	 * @param newValue The new value
	 */
	public void setValue(Object newValue) {

	}

	/**
	 * Sets the graphical components to the new text
	 */
	public void nameChanged() {
		missingLabel.setText(this.missingMsg+ this.name);

	}

}
