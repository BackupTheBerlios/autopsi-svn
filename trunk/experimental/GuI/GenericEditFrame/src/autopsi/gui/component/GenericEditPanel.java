package autopsi.gui.component;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import autopsi.database.dao.GenericDataObject;
import java.lang.reflect.Method;

public class GenericEditPanel extends JPanel {

	private GenericDataObject editedObject = null;
	private Method[] methods = null;

	
	public GenericEditPanel(){
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	}
	
	public void setObjectToEdit(GenericDataObject obj){
		this.editedObject = obj;
		inspectEditedObject();
	}
	
	private void inspectEditedObject(){
		methods = editedObject.getClass().getDeclaredMethods();
		for (int i=0;i<methods.length;i++){
			Class[] params = methods[i].getParameterTypes();
		}
	}
	
}
