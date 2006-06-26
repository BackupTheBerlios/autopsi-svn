package autopsi.gui.component;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.Color;

/**
 * EditPlugin that is used to edit Double values
 * @author Rudolf
 *
 */
public class DoubleEditPlugin extends EditPlugin implements FocusListener{

	protected Double value = 0.0;

	protected JPanel panel;
	protected JLabel label;
	protected JTextField doubleEdit;
	protected Color labelFG, textFieldBG;
	
	
	/**
	 * Initializes a DoubleEditPlugin
	 *
	 */
	public DoubleEditPlugin(){
		panel = new JPanel();
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
		this.panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		this.panel.setBackground(new Color(255,0,0));
		label = new JLabel();
		doubleEdit = new JTextField();
		doubleEdit.setPreferredSize(new Dimension(200, doubleEdit.getPreferredSize().height));
		doubleEdit.setText("3");
		doubleEdit.addFocusListener(this);
		panel.add(label);
		panel.add(doubleEdit);
		textFieldBG = doubleEdit.getBackground();
		labelFG = label.getForeground();
	}
	
	/**
	 * Return editing view
	 */
	public Component getEditor() {
		return this.panel;
	}

	/**
	 * Returns showing-only view
	 */
	public Component getView() {
		return this.panel;
	}

	/**
	 * Returns the currently edited value
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Sets the currently edited value
	 * @param newValue The new value
	 */
	public void setValue(Object newValue) {
		if (newValue != null)
			this.value = (Double)newValue;
		else
			this.value = 0.0;
		this.doubleEdit.setText(this.value.toString());
	}

	/**
	 * Sets the label to the new name
	 */
	public void nameChanged() {
		this.label.setText(this.name+":");
	}

	/**
	 * Unused
	 */
	public void focusGained(FocusEvent e){

	}
	
	/**
	 * When the focus is lost the value will be updated
	 */
	public void focusLost(FocusEvent e){
		if (e.getComponent().getClass().equals(JTextField.class)){
			
			try{

				this.value = Double.parseDouble(((JTextField)e.getComponent()).getText());

				((JTextField)e.getComponent()).setBackground(this.textFieldBG);
				this.label.setText(this.getName());
				this.label.setForeground(this.labelFG);
				
			}
			catch (Exception ex){
				this.label.setText("!"+this.getName());
				this.label.setForeground(new Color(255,0,0));
				((JTextField)e.getComponent()).setBackground(new Color(255,0,0));
			}
		}
			
	}
	
}
