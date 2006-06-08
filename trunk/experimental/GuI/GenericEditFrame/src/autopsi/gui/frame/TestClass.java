package autopsi.gui.frame;


import autopsi.gui.component.GenericData;
import autopsi.database.dao.GenericDataObject;

public class TestClass extends GenericData implements GenericDataObject{

	private String name;
	private Boolean test1;
	
	public TestClass(){
		try{
			Class[] abc = {String.class};
			this.addAttribute("name", this.getClass().getMethod("getName", new Class[0]), this.getClass().getMethod("setName",   abc ));
			Class[] bc = {Boolean.class};
			this.addAttribute("test1", this.getClass().getMethod("getTest1", new Class[0]), this.getClass().getMethod("setTest1",  bc   ));			
		}
		catch (Exception e){
			System.out.println("TestClass::addAttribute fehlgeschlagen::="+e.toString());
		}
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
