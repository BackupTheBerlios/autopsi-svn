package autopsi.gui.component;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class StringEditPlugin extends EditPlugin{

	
	protected String value = "";
	protected JPanel panel = null;
	protected JTextField stringEdit = null;
	protected JLabel label = null;
	
	public StringEditPlugin(){
		panel = new JPanel();
		this.label = new JLabel();
		this.panel.add(this.label);
		this.stringEdit = new JTextField(this.value);
		stringEdit.setPreferredSize(new Dimension(200, stringEdit.getPreferredSize().height));
		panel.add(this.stringEdit);
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
		return null;
	}

	protected void setValue(Object newValue){
		if (newValue != null)
			this.value = (String)newValue;
		else
			this.value = "";
		
		stringEdit.setText(this.value);
	}
	
}
