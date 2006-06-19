package autopsi.gui.component;

import java.awt.Component;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.awt.GridLayout;
import javax.swing.JPanel;
import autopsi.database.dao.GenericDataObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import autopsi.gui.exceptions.EWrongMethod;
import autopsi.gui.exceptions.EClassEditorMissing;
import javax.swing.JButton;
import autopsi.gui.component.GSMethod;
import autopsi.gui.frame.TestClass;

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
		edit = new DoubleEditPlugin();
		plugins.put(double.class, edit);
		plugins.put(Double.class, edit);
		edit = new IntegerEditPlugin();
		plugins.put(int.class, edit);
		plugins.put(Integer.class, edit);
		edit = new DateEditPlugin();
		plugins.put(java.sql.Date.class, edit);
		edit = new TimestampEditPlugin();
		plugins.put(Timestamp.class, edit);
		plugins.put(Object.class, new UnimplementedEditPlugin());
	}
	
	public void setObjectToEdit(GenericData obj) throws EClassEditorMissing{
		System.out.println("GenericEditPanel.setObjectToEdit(...)::1");
		this.editedObject = obj;
		System.out.println("GenericEditPanel.setObjectToEdit(...)::2");
		inspectEditedObject();
		System.out.println("GenericEditPanel.setObjectToEdit(...)::3");
		createUI();
		System.out.println("GenericEditPanel.setObjectToEdit(...)::4");
	}
	
	public GenericData getEditedObject(){
		this.updateEditedObject();
//		System.out.println("TestClass.getOb()=="+((TestClass)this.editedObject).getOb());
		return this.editedObject;
	}
	
	protected void updateEditedObject(){
//		System.out.println("GenericEditPanel::updateEditedObject");
		Set<GSMethod> s = methods.keySet();
		Iterator<GSMethod> iter = s.iterator();
		while(iter.hasNext()){
			GSMethod meth = iter.next();
			try{
//				System.out.println("GenericEditPanel::updateEditedObject::invoke");
				meth.setMethod.invoke(this.editedObject, methods.get(meth).getValue());
//				System.out.println("GenericEditPanel::methods.get(meth)::"+methods.get(meth).getValue().toString());
			}
			catch (Exception e){
				System.out.println("updateEditedObject::"+e.toString());
			}
		}
	}
	
	private void inspectEditedObject() throws EClassEditorMissing{
		Method[] tempMethods = editedObject.getClass().getDeclaredMethods();
		methods = new HashMap<GSMethod, EditPlugin>();
		Map<String, GSMethod> map = this.editedObject.getAllAttribs();
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		System.out.println("obj.size()=="+this.editedObject.getAttribCount());
		while(iter.hasNext()){
			String key = iter.next();
			GSMethod x = map.get(key);
			EditPlugin plug = this.getNewPlugin(x.getMethod.getReturnType());
			if (plug == null)
				plug = this.getNewPlugin(Object.class);
			plug.setName(key);
			System.out.println("GenericEditPanel.inspectEditedObject()::key=="+key);
			try{
				Object obj = null;
				try{
					obj = x.getMethod.invoke(this.editedObject, new Object[] {} );
				}
				catch (Exception e){
					obj = null;
				}			
				plug.setValue(obj);
			}
			catch (Exception e){
				if (e instanceof InvocationTargetException)
					System.out.println("InvocationTargetException::"+((InvocationTargetException)e).getTargetException().toString());
				System.out.println("Couldn' t set value in plugin::"+e.toString());
			}
			methods.put(x, plug);
		}
//		System.out.println("inspectEditedObject 3");
	}
	
	private EditPlugin getNewPlugin(Class cl){
		EditPlugin newPlugin = null;

			if (cl!=null){
				try{
					//newPlugin = (EditPlugin)(plugins.get(cl).clone());
						newPlugin = plugins.get(cl).getClass().newInstance();
				}
				catch (Exception e){
					try{
						newPlugin = plugins.get(Object.class).getClass().newInstance();
					}
					catch (Exception e2){
						System.out.println("couldn't create new Instance of plugin for Object.class::"+e2.toString());
					}
				} 
			}
			else
				newPlugin = (EditPlugin)(plugins.get(Object.class));
			
			if (newPlugin == null)
				newPlugin = (EditPlugin)(plugins.get(Object.class));
		
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
