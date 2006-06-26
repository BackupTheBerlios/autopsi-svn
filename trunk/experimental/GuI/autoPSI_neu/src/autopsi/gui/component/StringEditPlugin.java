package autopsi.gui.component;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * EditPlugin used to edit String values
 * @author Rudolf
 *
 */
public class StringEditPlugin extends EditPlugin implements FocusListener{

	
	protected String value = "";
	protected JPanel panel = null;
	protected JTextField stringEdit = null;
	protected JLabel label = null;
	
	/**
	 * Initializes a StringEditPlugin
	 *
	 */
	public StringEditPlugin(){
		panel = new JPanel();
		this.label = new JLabel();
		this.panel.add(this.label);
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
		this.stringEdit = new JTextField(this.value);
		stringEdit.setPreferredSize(new Dimension(200, stringEdit.getPreferredSize().height));
		panel.add(this.stringEdit);
		stringEdit.addFocusListener(this);
	}
	
	/**
	 * Returns edit-View
	 */
	public Component getEditor(){
		return panel;
	}
	
	/**
	 * Sets the label if the name of the currently edited value has changed
	 */
	public void nameChanged(){
		this.label.setText(this.name+":");
	}
	
	/** 
	 * Returns view for showing only
	 */
	public Component getView(){
		return panel;
	}

	/**
	 * Returns the currently by this EditPlugin edited value
	 */
	public Object getValue(){
		return this.value;
	}
	
	/**
	 * Sets the currently edited value to newValue
	 * @param newValue The new value
	 */
	public void setValue(Object newValue){
		if (newValue != null)
			this.value = (String)newValue;
		else
			this.value = "";
		
		stringEdit.setText(this.value);
	}

	/**
	 * Unused
	 */
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * If focus is lost, the EditPlugin' s value will be updated
	 * @param arg0 the FocusEvent
	 */
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource().equals(this.stringEdit)){
			this.value = stringEdit.getText();
		}
		
	}
	
}
