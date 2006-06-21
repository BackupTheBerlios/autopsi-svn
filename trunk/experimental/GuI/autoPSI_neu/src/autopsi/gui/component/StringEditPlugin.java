package autopsi.gui.component;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class StringEditPlugin extends EditPlugin implements FocusListener{

	
	protected String value = "";
	protected JPanel panel = null;
	protected JTextField stringEdit = null;
	protected JLabel label = null;
	
	public StringEditPlugin(){
		panel = new JPanel();
		this.label = new JLabel();
		this.panel.add(this.label);
//		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panel.setBackground(new Color(255,0,0));
		this.stringEdit = new JTextField(this.value);
		stringEdit.setPreferredSize(new Dimension(200, stringEdit.getPreferredSize().height));
		panel.add(this.stringEdit);
		stringEdit.addFocusListener(this);
	}
	
	public Component getEditor(){
		return panel;
	}
	
	public void nameChanged(){
		this.label.setText(this.name+":");
	}
	
	public Component getView(){
		return panel;
	}

	public Object getValue(){
		return this.value;
	}

	public void setValue(Object newValue){
		if (newValue != null)
			this.value = (String)newValue;
		else
			this.value = "";
		
		stringEdit.setText(this.value);
	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource().equals(this.stringEdit)){
			this.value = stringEdit.getText();
		}
		
	}
	
}
