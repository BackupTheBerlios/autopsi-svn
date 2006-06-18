package autopsi.javaspace;

import net.jini.core.entry.Entry;


public class HelloWorldMessage implements Entry {
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	public String text;
	
	public HelloWorldMessage(){
		
	}
	
	public String toString(){
		return "toString::"+text;
	}

	public String getTitle() {
		return text;
	}

	public void setTitle(String text) {
		this.text = text;
	}
}
