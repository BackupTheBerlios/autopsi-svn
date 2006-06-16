package autopsi.gui.component;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class DoubleEditPlugin extends EditPlugin implements FocusListener{

	protected double value = 0;

	protected JPanel panel;
	protected JLabel label;
	protected JTextField doubleEdit;
	protected Color labelFG, textFieldBG;
	
	
	public DoubleEditPlugin(){
		System.out.println("CREATED DoubleEditPlugin");
		panel = new JPanel();
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
	
	public Component getEditor() {
		return this.panel;
	}

	public Component getView() {
		return this.panel;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object newValue) {
		this.value = (Double)newValue;
	}

	public void nameChanged() {
		this.label.setText(this.name+":");
	}

	
	public void focusGained(FocusEvent e){

	}
	
	public void focusLost(FocusEvent e){
		System.out.println("focusLost");
		if (e.getComponent().getClass().equals(JTextField.class)){
			System.out.println("focusLostDobuleEdit::JTextField");
			try{
				System.out.println("focusLostDoubleEditPlugin trying to set value");
				this.value = Double.parseDouble(((JTextField)e.getComponent()).getText());
				System.out.println("focusLostDoubleEditPlugin, value=="+this.value);
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
