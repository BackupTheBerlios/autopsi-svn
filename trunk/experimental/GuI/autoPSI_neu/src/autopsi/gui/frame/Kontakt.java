package autopsi.gui.frame;

import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GenericData;

import java.sql.Date;

public class Kontakt extends GenericData implements GenericDataObject {

	
	private int global_id;
	private String prename;
	private String surname;
	private Date birth_date;
	private String tel_private;
	private String tel_business;
	private String tel_mobile;
	private Integer a_zipcode;
	private String a_city;
	private String a_adress;
	
	public Kontakt(){
		Class cl = this.getClass();
		try{
			this.addAttribute("GlobalId", cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {int.class}));
			this.addAttribute("Geburtstag", cl.getMethod("getBirthDate", new Class[] {}), cl.getMethod("setBirthDate", new Class[] {Date.class}));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Kontakt-Objekts::"+e.toString());
		}
	}
	
	
	public int getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(int globalId){
		this.global_id = globalId;
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
