package autopsi.gui.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import autopsi.database.dao.GenericDataObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import autopsi.gui.exceptions.EWrongMethod;
import autopsi.gui.exceptions.EClassEditorMissing;
import javax.swing.JButton;
import autopsi.gui.component.GSMethod;
import autopsi.gui.component.BooleanEditPlugin;
import autopsi.gui.component.DateEditPlugin;
import autopsi.gui.component.DoubleEditPlugin;
import autopsi.gui.component.EditPlugin;
import autopsi.gui.component.ForeignKeyEditPlugin;
import autopsi.gui.component.IntegerEditPlugin;
import autopsi.gui.component.StringEditPlugin;
import autopsi.gui.component.TimestampEditPlugin;
import autopsi.gui.component.UnimplementedEditPlugin;
import autopsi.gui.frame.TestClass;

public class GenericEditPanel extends JPanel {

	private GenericData editedObject = null;
	private Map<GSMethod, EditPlugin> methods = null;
	private Map<Class, EditPlugin> plugins;
	private JPanel panel = null;
	private JDialog parentFrame;
	
	public GenericEditPanel(JDialog parentFrame){
		plugins = new HashMap<Class, EditPlugin>();
		panel = new JPanel();
		this.parentFrame = parentFrame;
		this.add(panel);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
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
//		System.out.println("GenericEditPanel.setObjectToEdit(...)::1");
		this.editedObject = obj;
//		System.out.println("GenericEditPanel.setObjectToEdit(...)::2");
		inspectEditedObject();
//		System.out.println("GenericEditPanel.setObjectToEdit(...)::3");
		createUI();
//		System.out.println("GenericEditPanel.setObjectToEdit(...)::4");
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
			if ( (meth instanceof GSMethodNormal) || (meth instanceof GSMethodForeign)){
				try{
					meth.setMethod.invoke(this.editedObject, methods.get(meth).getValue());
				}
				catch (Exception e){
					System.out.println("updateEditedObject::"+e.toString());
				}
			}
			if (meth instanceof GSMethodPrimary){
				try{
					meth.setMethod.invoke(this.editedObject, new Object[] {null});
//					meth.setMethod.invoke(this.editedObject, methods.get(meth).getValue());
				}
				catch (Exception e){
					System.out.println("GenericEditPanel.updateEditedObject::Konnte Primary Key nicht updaten::"+e.toString());
				}
			}
		}
	}
	
	private void inspectEditedObject() throws EClassEditorMissing{
		Method[] tempMethods = editedObject.getClass().getDeclaredMethods();
//		methods = new HashMap<GSMethod, EditPlugin>();
		methods = new HashMap<GSMethod, EditPlugin>();
		Map<String, GSMethod> map = this.editedObject.getAllAttribs();
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
//		System.out.println("obj.size()=="+this.editedObject.getAttribCount());
		while(iter.hasNext()){
			String key = iter.next();
			GSMethod x = map.get(key);
			EditPlugin plug = this.getNewPlugin(x.getMethod.getReturnType());
			if (!x.show)
				continue;
			if (plug == null)
				plug = this.getNewPlugin(Object.class);
			plug.setName(key);
//			System.out.println("GenericEditPanel.inspectEditedObject()::key=="+key);
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
			if (x instanceof GSMethodForeign){
				EditPlugin original = plug;
				plug = new ForeignKeyEditPlugin();
				((ForeignKeyEditPlugin)plug).setEditPlugin(original);
				((ForeignKeyEditPlugin)plug).setEditedTable(((GSMethodForeign)x).tableName);
				((ForeignKeyEditPlugin)plug).setEditedAttrib(((GSMethodForeign)x).attribName);
				((ForeignKeyEditPlugin)plug).setEditedClass(((GSMethodForeign)x).objectClass);
				((ForeignKeyEditPlugin)plug).setParentFrame(this.parentFrame);
			}
			if (x instanceof GSMethodPrimary){
				//do nothing
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
			Set<GSMethod> set = this.methods.keySet();
			Map<GSMethod, EditPlugin> localMethods = new HashMap<GSMethod, EditPlugin>();	
			Iterator<GSMethod> iter = set.iterator();
			List<GSMethod> sortedMethods = new ArrayList<GSMethod>();
			
			while(iter.hasNext()){
				GSMethod meth = iter.next();
				EditPlugin plugin = this.methods.get(meth);
				localMethods.put(meth, plugin);
			}
				
			set = localMethods.keySet();
				
			int size = set.size();
			for(int i=0;i<size;i++){
				set = localMethods.keySet();
				iter = set.iterator();
				GSMethod minMethod = null;
				
				while(iter.hasNext()){
					GSMethod method = iter.next();
					if (minMethod == null)
						minMethod = method;
					
					if (method.methodId <= minMethod.methodId)
						minMethod = method;
				}
				localMethods.remove(minMethod);
				sortedMethods.add(minMethod);
			}
			
			iter = sortedMethods.iterator();
			while(iter.hasNext()){
				GSMethod method = iter.next();
				EditPlugin plugin = methods.get(method);
				panel.add( plugin.getView());
			}
			
			
		}
		catch (Exception e){
			System.out.println("CreateUI, Exception :: "+e.toString());
		}
	}
	
}
