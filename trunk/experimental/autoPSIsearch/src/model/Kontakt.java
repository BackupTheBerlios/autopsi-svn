package model;
import java.sql.Date;

import autopsi.database.dao.GenericDataObject;

public class Kontakt implements GenericDataObject {
	public static final int ALLGEMEIN = 1;
	public static final int PRIVATE = 2;
	public static final int GESCHAEFTLICH = 3;
	
	private Integer kategorie_id;
	private String prename;
	private String surname;
	private Date birth_date;
	private Integer telnumber;
	private Integer a_zipcode;
	private String a_city;
	private String a_adress;
	
	
	public Integer getKategorieID() {
		return kategorie_id;
	}
	public String getPrename() {
		return this.prename;
	}
	public String getSurname() {
		return this.surname;
	}
	public Date getBirthdate(){
		return this.birth_date;
	}
	

	
	public String toString() {
		String erg;
		erg = "Kategorie: " + kategorie_id;
		return erg;
	}

}
