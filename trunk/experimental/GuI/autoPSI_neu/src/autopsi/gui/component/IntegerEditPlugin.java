package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

/**
 * An EditPlugin used for editing Integer values
 * @author Rudolf
 *
 */
public class IntegerEditPlugin extends EditPlugin implements FocusListener {

	protected Integer value = 0;

	protected JPanel panel;
	protected JLabel label;
	protected JTextField doubleEdit;
	protected Color labelFG, textFieldBG;
	
	/**
	 * Initializes an IntegerEditPlugin
	 *
	 */
	public IntegerEditPlugin(){

		panel = new JPanel();
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setBackground(new Color(255,0,0));
		label = new JLabel();
		doubleEdit = new JTextField();
		doubleEdit.setPreferredSize(new Dimension(200, doubleEdit.getPreferredSize().height));
		doubleEdit.setText("0");
		doubleEdit.addFocusListener(this);
		panel.add(label);
		panel.add(doubleEdit);
		textFieldBG = doubleEdit.getBackground();
		labelFG = label.getForeground();
	}
	
	/**
	 * Returns edit view
	 */
	public Component getEditor() {
		return this.panel;
	}

	/**
	 * Returns view for showing-only
	 */
	public Component getView() {
		return this.panel;
	}

	/**
	 * Returns the current value
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Sets the current value
	 */
	public void setValue(Object newValue) {
		if (newValue!=null){

			this.value = (Integer)newValue;
		}
		else{

			this.value = 0;
		}
		this.doubleEdit.setText(this.value.toString());
	}

	/**
	 * Sets label when the name of the edited value has changed
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
	 * When edit field looses focus, the EditPlugin' s value will be updated
	 */
	public void focusLost(FocusEvent e){
		if (e.getComponent().getClass().equals(JTextField.class)){
			try{
				this.value = Integer.parseInt(((JTextField)e.getComponent()).getText());
				((JTextField)e.getComponent()).setBackground(this.textFieldBG);
				this.label.setForeground(this.labelFG);
				this.label.setText(this.getName()+":");
				
			}
			catch (Exception ex){
				this.label.setText("!"+this.getName()+":");
				this.label.setForeground(new Color(255,0,0));
				((JTextField)e.getComponent()).setBackground(new Color(255,0,0));
			}
		}
			
	}
	
}
