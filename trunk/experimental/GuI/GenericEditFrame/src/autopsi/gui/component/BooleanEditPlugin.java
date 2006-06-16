package autopsi.gui.component;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class BooleanEditPlugin extends EditPlugin{

	
	protected Boolean selected = false;
	protected JPanel panel = null;
	protected JCheckBox checkBox = null;
	
	public BooleanEditPlugin(){
		panel = new JPanel();
		checkBox = new JCheckBox();
		panel.add(checkBox);
	}
	
	public Component getEditor(){
		return panel;
	}
	
	public void nameChanged(){
		checkBox.setText(name);
	}
	
	public void setValue(Object newValue){
		if(newValue != null){
			this.selected = (Boolean)newValue;
			checkBox.setSelected(this.selected);
		}
	}
	
	public Component getView(){
		return panel;
	}
	
	
	public Object getValue(){
		return this.selected;
	}
	
}
