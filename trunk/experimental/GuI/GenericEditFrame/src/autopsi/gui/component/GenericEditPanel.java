package autopsi.gui.component;

import java.awt.Component;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.awt.GridLayout;
import javax.swing.JPanel;
import autopsi.database.dao.GenericDataObject;
import java.lang.reflect.Method;
import autopsi.gui.exceptions.EWrongMethod;
import autopsi.gui.exceptions.EClassEditorMissing;
import javax.swing.JButton;
import autopsi.gui.component.GSMethod;
public class GenericEditPanel extends JPanel {

	private GenericData editedObject = null;
	private Map<GSMethod, EditPlugin> methods = null;
	private Map<Class, EditPlugin> plugins = new HashMap<Class, EditPlugin>();
	private JPanel panel = null;
	
	public GenericEditPanel(){
		panel = new JPanel();
		this.add(panel);
		panel.setLayout(new GridLayout(0, 1));
		setDefaultEditors();
	}
	
	private void setDefaultEditors(){
		plugins.put(String.class, new StringEditPlugin());
		EditPlugin edit = new BooleanEditPlugin();
		plugins.put(boolean.class, edit);
		plugins.put(Boolean.class, edit);
	}
	
	public void setObjectToEdit(GenericData obj) throws EClassEditorMissing{
		this.editedObject = obj;
		inspectEditedObject();
		createUI();
	}
	
	public GenericData getEditedObject(){
		return this.editedObject;
	}
	
	private void inspectEditedObject() throws EClassEditorMissing{
		Method[] tempMethods = editedObject.getClass().getDeclaredMethods();
		methods = new HashMap<GSMethod, EditPlugin>();
		Map<String, GSMethod> map = this.editedObject.getAllAttribs();
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()){
			String key = iter.next();
			GSMethod x = map.get(key);
			EditPlugin plug = this.getNewPlugin(x.getMethod);
			plug.setName(key);
			try{
				plug.setValue(x.getMethod.invoke(this.editedObject, new Object[0]));
			}
			catch (Exception e){
				System.out.println("Couldn' t set value in plugin::"+e.toString());
			}
			methods.put(x, plug);
		}
	}
	
	private EditPlugin getNewPlugin(Method method) throws EClassEditorMissing{
		EditPlugin newPlugin = null;
		try{
			newPlugin = (EditPlugin)(plugins.get(method.getReturnType()).clone());
		}
		catch (NullPointerException e){
			throw new EClassEditorMissing();
		}
		//this case should never occur cause only EditPlugins are in the list which extends Cloneable
		catch (CloneNotSupportedException e){
			
		}
		return newPlugin;
	}
	
	public void registerPlugin(EditPlugin newPlugin, Class classToRegister){
		plugins.put(classToRegister, newPlugin);
	}
	
	/**
	 * 
	 *
	 */
	private void createUI(){
		try{
			panel.removeAll();
			Set<GSMethod> set = methods.keySet();
			Iterator<GSMethod> iter = set.iterator();
			while(iter.hasNext()){
				GSMethod method = iter.next();
				EditPlugin plugin = methods.get(method);
				panel.add( plugin.getView() );
			}
		}
		catch (Exception e){
			System.out.println("CreateUI, Exception :: "+e.toString());
		}
	}
	
}
