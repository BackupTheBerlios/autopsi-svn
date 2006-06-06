package model;


public class Kontakt {
	public static final int ALLGEMEIN = 1;
	public static final int PRIVATE = 2;
	
	private Integer kategorie_id;
	private String prename;
	private String surname;
	private java.util.Date birth_date;
	//.....
	


	
	
	public Integer getKategorieID() {
		return kategorie_id;
	}

	
	public String toString() {
		String erg;
		erg = "Kategorie: " + kategorie_id;
		return erg;
	}

}
