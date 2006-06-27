package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 * EditPlugin that is used to edit Boolean values
 * @author Rudolf
 *
 */
public class BooleanEditPlugin extends EditPlugin implements ItemListener{

	
	protected Boolean selected = false;
	protected JPanel panel = null;
	protected JCheckBox checkBox = null;
	
	/**
	 * Initializes a BooleanEditPlugin
	 *
	 */
	public BooleanEditPlugin(){
		panel = new JPanel();
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setBackground(new Color(255,0,0));
		this.panel.setForeground(new Color(0,0,0));
		checkBox = new JCheckBox();
		panel.add(checkBox);
		checkBox.addItemListener(this);
	}
	
	/**
	 * Return editing view
	 */
	public Component getEditor(){
		return panel;
	}
	
	/**
	 * Sets the check box' s caption to the new name
	 */
	public void nameChanged(){
		checkBox.setText(name);
	}
	
	/**
	 * Sets the currently edited value
	 * @param newValue The new value
	 */
	public void setValue(Object newValue){
		if(newValue != null)
			this.selected = (Boolean)newValue;
		else
			this.selected = true;
		checkBox.setSelected(this.selected);
	}
	
	/**
	 * Returns showing-only view
	 */
	public Component getView(){
		return panel;
	}
	
	/**
	 * Returns the currently edited value
	 */
	public Object getValue(){
		return this.selected;
	}
	
	/**
	 * When the item state is changed, the value will be updated
	 */
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource().equals(this.checkBox)){
			this.selected = this.checkBox.isSelected();
		}
		
	}
	
}
