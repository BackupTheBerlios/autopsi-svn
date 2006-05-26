/**
 * filename: Main.java
 * description: testing application for the experimental GenericDAO
 * @author: Rudolf Mildner
 */


import autopsi.database.dataobject.*;
import autopsi.database.attribute.*;
import autopsi.database.dao.*;
import autopsi.database.exception.*;

import java.lang.reflect.Field;
import java.util.List;


public class Main {
	
	public static void main(String[] args){
		System.out.println("GenericDAO testing application is ok!");
		IGenericDAO gdo = new GenericDAO();
		gdo.setCurrentTable("testtable");
		List<GenericDataObject> list = null;
		((GenericDAO)gdo).getWhere(new TestData());
		try{
			list = gdo.getDataObjects(new TestData());
		}
		catch (Exception e){
			System.out.println("AAARGH;"+e.toString());
		}
		System.out.println("size of list=="+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println( ((TestData)list.get(i)).dval);
		}
	}
	
}
