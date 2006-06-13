package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;
import java.sql.Date;

public class Kontakt implements GenericDataObject {

	
	private int global_id;
	private int kategorie_id;
	private String prename;
	private String surname;
	private Date birth_date;
	private String tel_private;
	private String tel_business;
	private String tel_mobile;
	private int a_zipcode;
	private String a_city;
	private String a_adress;
	
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(int globalId){
		this.global_id = globalId;
	}
	
	public int getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(int kategorieId){
		this.kategorie_id = kategorieId;
	}
	
	public String getPrename(){
		return this.prename;
	}
	
	public void setPrename(String prename){
		this.prename = prename;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	public Date getBirthDate(){
		return this.birth_date;
	}
	
	public void setBirthDate(Date birthDate){
		this.birth_date = birthDate;
	}
	
	public String getTelPrivate(){
		return this.tel_private;
	}
	
	public void setTelPrivate(String telPrivate){
		this.tel_private = telPrivate;
	}
	
	public String getTelBusiness(){
		return this.tel_business;
	}
	
	public void setTelBusiness(String telBusiness){
		this.tel_business = telBusiness;
	}
	
	public String getTelMobile(){
		return this.tel_mobile;
	}
	
	public void setTelMobile(String telMobile){
		this.tel_mobile = telMobile;
	}
	
	public int getAZipCode(){
		return this.a_zipcode;
	}
	
	public void setAZipCode(int aZipCode){
		this.a_zipcode = aZipCode;
	}
	
	public String getACity(){
		return this.a_city;
	}
	
	public void setACity(String aCity){
		this.a_city = aCity;
	}
	
	public String getAAdress(){
		return this.a_adress;
	}
	
	public void setAAdress(String aAdress){
		this.a_adress = aAdress;
	}
	
}
