package autopsi.gui.component;

/**
 * Is used to say that a value is a foreign key and which table and object it references
 * @author Rudolf
 *
 */
public class GSMethodForeign extends GSMethod{
	public String tableName;
	public String attribName;
	public Class objectClass;
}
