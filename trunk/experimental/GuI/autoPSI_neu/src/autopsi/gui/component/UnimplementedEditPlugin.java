package autopsi.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UnimplementedEditPlugin extends EditPlugin {

	protected JPanel panel = null;
	protected JLabel missingLabel = null;
	
	protected final static String missingMsg = "Attribute cannot be edited: ";
	
	public UnimplementedEditPlugin(){
		panel = new JPanel();
		this.panel.setAlignmentY(Component.LEFT_ALIGNMENT);
//		this.panel.setBackground(new Color(255,0,0));
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		missingLabel = new JLabel();
		panel.add(missingLabel);
		missingLabel.setForeground(new Color(255,0,0));
	}
	
	@Override
	public Component getEditor() {
		return panel;
	}

	@Override
	public Component getView() {
		return panel;
	}

	@Override
	public Object getValue() {
		return "";
	}

	@Override
	public void setValue(Object newValue) {

	}

	@Override
	public void nameChanged() {
		missingLabel.setText(this.missingMsg+ this.name);

	}

}
