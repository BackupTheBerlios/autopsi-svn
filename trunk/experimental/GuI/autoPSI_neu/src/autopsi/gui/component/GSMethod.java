package autopsi.gui.component;

import java.lang.reflect.Method;

/**
 * GSMethod is an abstract class; derived classes will represent values of the GenericData - Object. 
 * @author Rudolf
 *
 */
public abstract class GSMethod {
	public Method getMethod;
	public Method setMethod;
	public boolean show = true;
	public int methodId;
	protected static int methodCount;
	
	/**
	 * Initializes the GSMethod
	 *
	 */
	public GSMethod(){
		this.methodId = methodCount;
		methodCount++;
	}
}
