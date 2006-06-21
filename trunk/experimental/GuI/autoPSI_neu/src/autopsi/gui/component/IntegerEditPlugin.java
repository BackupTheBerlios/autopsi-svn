package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class IntegerEditPlugin extends EditPlugin implements FocusListener {

	protected Integer value = 0;

	protected JPanel panel;
	protected JLabel label;
	protected JTextField doubleEdit;
	protected Color labelFG, textFieldBG;
	
	
	public IntegerEditPlugin(){
//		System.out.println("CREATED IntegerEditPlugin");
		panel = new JPanel();
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
		if (newValue!=null){
//			System.out.println("IntegerEditPlugin.setValue() newValue!=null");
			this.value = (Integer)newValue;
		}
		else{
//			System.out.println("IntegerEditPlugin.setValue() newValue==null");
			this.value = 0;
		}
		this.doubleEdit.setText(this.value.toString());
	}

	public void nameChanged() {
		this.label.setText(this.name+":");
	}

	
	public void focusGained(FocusEvent e){

	}
	
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
