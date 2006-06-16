package autopsi.gui.component;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UnimplementedEditPlugin extends EditPlugin {

	protected JPanel panel = null;
	protected JLabel missingLabel = null;
	
	protected final static String missingMsg = "This editor is missing; the following attribute cannot be edited: ";
	
	public UnimplementedEditPlugin(){
		panel = new JPanel();
		missingLabel = new JLabel();
		panel.add(missingLabel);
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
		System.out.println("unimplementedPlugin.nameChanged");
		missingLabel.setText(this.missingMsg+ this.name);

	}

}
