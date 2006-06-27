package autopsi.gui.component;

import java.lang.reflect.Method;

public abstract class GSMethod {
	public Method getMethod;
	public Method setMethod;
	public boolean show = true;
	public int methodId;
	protected static int methodCount;
	
	public GSMethod(){
		this.methodId = methodCount;
		methodCount++;
	}
}
