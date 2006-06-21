package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class BooleanEditPlugin extends EditPlugin implements ItemListener{

	
	protected Boolean selected = false;
	protected JPanel panel = null;
	protected JCheckBox checkBox = null;
	
	public BooleanEditPlugin(){
		panel = new JPanel();
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
		this.panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panel.setBackground(new Color(255,0,0));
		this.panel.setForeground(new Color(0,0,0));
		checkBox = new JCheckBox();
		panel.add(checkBox);
		checkBox.addItemListener(this);
	}
	
	public Component getEditor(){
		return panel;
	}
	
	public void nameChanged(){
		checkBox.setText(name);
	}
	
	public void setValue(Object newValue){
		System.out.println("boolean edit plugin setValue");
		if(newValue != null)
			this.selected = (Boolean)newValue;
		else
			this.selected = true;
		checkBox.setSelected(this.selected);
	}
	
	public Component getView(){
		return panel;
	}
	
	
	public Object getValue(){
		return this.selected;
	}

	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource().equals(this.checkBox)){
			this.selected = this.checkBox.isSelected();
		}
		
	}
	
}
