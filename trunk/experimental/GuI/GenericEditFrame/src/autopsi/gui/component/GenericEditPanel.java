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
		plugins.put(Object.class, new UnimplementedEditPlugin());
	}
	
	public void setObjectToEdit(GenericData obj) throws EClassEditorMissing{
		this.editedObject = obj;
		System.out.println("aa ha");
		inspectEditedObject();
		System.out.println("immernoch hier");
		createUI();
	}
	
	public GenericData getEditedObject(){
		return this.editedObject;
	}
	
	private void inspectEditedObject() throws EClassEditorMissing{
		System.out.println("inspectEditedObject 1");
		Method[] tempMethods = editedObject.getClass().getDeclaredMethods();
		methods = new HashMap<GSMethod, EditPlugin>();
		Map<String, GSMethod> map = this.editedObject.getAllAttribs();
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		System.out.println("inspectEditedObject 2");
		while(iter.hasNext()){
			String key = iter.next();
			GSMethod x = map.get(key);
			System.out.println("before plug = "+key);
			EditPlugin plug = this.getNewPlugin(x.getMethod.getReturnType());
			if (plug == null)
				plug = this.getNewPlugin(Object.class);
			System.out.println("after plug = "+key);
			plug.setName(key);
			System.out.println("after key!!");
			try{
				plug.setValue(x.getMethod.invoke(this.editedObject, new Object[] {} ));
			}
			catch (Exception e){
				System.out.println("Couldn' t set value in plugin::"+e.toString());
			}
			methods.put(x, plug);
		}
		System.out.println("inspectEditedObject 3");
	}
	
	private EditPlugin getNewPlugin(Class cl){
		EditPlugin newPlugin = null;
		try{
			if (cl!=null){
				try{
					newPlugin = (EditPlugin)(plugins.get(cl).clone());
				}
				catch (NullPointerException e){
					newPlugin = (EditPlugin)(plugins.get(Object.class));
				}
			}
			else
				newPlugin = (EditPlugin)(plugins.get(Object.class));
			
			if (newPlugin == null)
				newPlugin = (EditPlugin)(plugins.get(Object.class));
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
