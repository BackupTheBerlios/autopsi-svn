/**
 * filename: Main.java
 * description: testing application for the experimental GenericDAO
 * @author: Rudolf Mildner
 */


import autopsi.database.dao.*;
import autopsi.database.sql.*;
import autopsi.database.exception.*;

import java.lang.reflect.Field;
import java.util.List;


public class Main {
	
	public static void main(String[] args){
		System.out.println("GenericDAO testing application is ok!");
		
		
		/*SQLFields fs = new SQLFields();
		fs.add(new SQLField("name", "He%"));
		fs.add(new SQLField("name2", "x"));
		SQLOperator op = new SQLOperatorLike(fs);
		SQLConjunctor con = new SQLConjunctorOr();
		con.addOperator(op);
		con.beginTraversal();
		SQLWhere w = new SQLWhere();
		w.addConjunctor(con);
		w.beginTraversal();
		SQLStatement sel = new SQLInsert(new SQLTable("testdatatable"), fs);
		System.out.println(sel.getQuery());
		*/
		
		IGenericDAO gdo = new GenericDAO();
		gdo.setCurrentTable("testdatatable");
		
		

		List<GenericDataObject> objs = null;

		
		/*try{
			objs = gdo.complexQuery(sel, new TestData());
		}
		catch (Exception e){
			System.out.println("exception in main::"+e.toString());
		}
		for(int i=0;i<objs.size();i++){
			System.out.println("Yeehaw!::Name=="+((TestData)objs.get(i)).name);
		}
		*/
		
		//adding an object to the database
		try{
			TestData obj = new TestData();
			obj.name = "eingefügtes Objekt";
			obj.name2 = "ich bin das erste per GenericDAO eingefügte Objekt; ich hab sogar Umlaute!!!!";
			obj.dval = 999.9;
			gdo.addDataObject(obj);
		}
		catch (Exception e){
			System.out.println("Exception beim Einfügen=="+e.toString());
		}

		//updating an object in the database
		try{
			TestData lookup = new TestData();
			TestData updateData = new TestData();
			lookup.dval = 999.9;
			updateData.dval = 33.3;
			updateData.name = "Juhuu! Damit bin ich auch das erste upgedatete Objekt!";
			gdo.updDataObjects(lookup, updateData);
		}
		catch (Exception e){
			System.out.println("Exception beim Updaten=="+e.toString());
		}
		
		//removing an object from the database
		try{
			TestData obj = new TestData();
			obj.dval = 33.3;
			gdo.delDataObjects(obj);
		}
		catch (Exception e){
			System.out.println("Exception beim Löschen=="+e.toString());
		}
		
		
		//get objects from the database
		List<GenericDataObject> list = null;
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
