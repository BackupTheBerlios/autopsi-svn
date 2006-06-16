package autopsi.gui.frame;


import autopsi.gui.component.GenericData;
import autopsi.database.dao.GenericDataObject;

public class TestClass extends GenericData implements GenericDataObject{

	private String name;
	private Boolean test1;
	private int ob;
	
	public TestClass(){
		try{
			Class cl = this.getClass();
			this.addAttribute("name",cl.getMethod("getName", new Class[] {} ), cl.getMethod("setName",  new Class[] {String.class} ));
			this.addAttribute("test1", cl.getMethod("getTest1", new Class[] {} ), cl.getMethod("setTest1",  new Class[] {Boolean.class}   ));			
			this.addAttribute("ob", cl.getMethod("getOb", new Class[] {} ), cl.getMethod("setOb", new Class[] {int.class} ));
		}
		catch (Exception e){
			System.out.println("TestClass::addAttribute fehlgeschlagen::="+e.toString());
		}
	}
	
	public int getOb(){
		return this.ob;
	}
	
	public void setOb(int ob){
		this.ob = ob;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Boolean getTest1(){
		return test1;
	}
	
	public void setTest1(Boolean test1){
		this.test1 = test1;
	}
}
