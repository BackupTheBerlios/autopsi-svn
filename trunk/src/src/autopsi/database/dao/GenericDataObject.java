/**
 * filename: GenericDataObject.java
 * description: GenericDataObject, can hold attributes; 
 * this class is normally used to derive classes that add attributes in their constructor
 * @author: Rudolf Mildner
 */

package autopsi.database.dao;

/**
 * GenericDataObject is the object that one has to implement if he wants to get data from a table; 
 * the GenericDataObject itself should hold only attributes and methods directly related to the date
 * from the database table. Attributes can be private; the GenericDAO is able to access them anyway
 * (this isn' t considered harmful because
 * 1st as your implementation should check with set functions that
 * only valid Data will be stored and 
 * 2nd the database will always (i know it' s not true, but normally this is a valid assumption as)
 * If you derive an object from GenericDataObject that should hold data from a table, please make
 * sure that only attributes are in that object that are also in the database table; otherwise you 
 * risk getting an EAttributeNotFoundException and database operations won' t be possible.
 * @author Rudolf
 *
 */
public abstract class GenericDataObject{

}


