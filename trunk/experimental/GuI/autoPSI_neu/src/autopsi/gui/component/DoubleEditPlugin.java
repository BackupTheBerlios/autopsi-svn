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

public class DoubleEditPlugin extends EditPlugin implements FocusListener{

	protected Double value = 0.0;

	protected JPanel panel;
	protected JLabel label;
	protected JTextField doubleEdit;
	protected Color labelFG, textFieldBG;
	
	
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
		if (newValue != null)
			this.value = (Double)newValue;
		else
			this.value = 0.0;
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
