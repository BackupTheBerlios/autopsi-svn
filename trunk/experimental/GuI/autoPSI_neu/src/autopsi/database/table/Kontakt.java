package autopsi.database.table;

import autopsi.database.dao.GenericDataObject;
import java.sql.Date;

public class Kontakt implements GenericDataObject {

	private Integer global_id;
	private String prename;
	private String surname;
	private Date birth_date;
	private String tel_private;
	private String tel_business;
	private String tel_mobile;
	private Integer a_zipcode;
	private String a_city;
	private String a_adress;
	private String first_email;
	private String second_email;
	
	public Integer getGlobalId(){
		return this.global_id;
	}
	
	public void setGlobalId(Integer globalId){
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
	
	public Integer getAZipCode(){
		return this.a_zipcode;
	}
	
	public void setAZipCode(Integer aZipCode){
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
	
	public String getFirst_Email(){
		return this.first_email;
	}
	
	public void setFirst_Email(String email){
		this.first_email = email;
	}
	
	public String getSecond_Email(){
		return this.second_email;
	}

	public void setSecond_Email (String email){
		this.second_email=email;
	}
	
	public String toString () {
		String str = "";
		if (this != null){
			if (this.global_id != null)
				str += "Global ID: " + this.global_id.toString() + "\n";
			else
				str += "Global ID: NULL\n";
			if (this.prename != null)
				str += "Prename: " + this.prename + "\n";
			else
				str += "Prename: NULL\n";
			if (this.surname != null)
				str += "Surname: " + this.surname + "\n";
			else
				str += "Surname: NULL\n";
			if (this.birth_date != null)
				str += "Birthdate: " + this.birth_date.toString() + "\n";
			else
				str += "Birthdate: NULL\n";
			if (this.tel_private != null)
				str += "Tel Private: " + this.tel_private + "\n";
			else
				str += "Tel Private: NULL\n";
			if (this.tel_business != null)
				str += "Tel Business: " + this.tel_business + "\n";
			else
				str += "Tel Business: NULL\n";
			if (this.tel_mobile != null)
				str += "Tel Mobile: " + this.tel_mobile + "\n";
			else
				str += "Tel Mobile: NULL\n";
			if (this.first_email != null)
				str += "First Email: " + this.first_email + "\n";
			else
				str += "First Email: NULL\n";
			if (this.second_email != null)
				str += "Second Email: " + this.second_email.toString() + "\n";
			else
				str += "Second Email: NULL\n";
			if (this.a_zipcode != null)
				str += "Zip Code: " + this.a_zipcode.toString() + "\n";
			else
				str += "Zip Code: NULL\n";
			if (this.a_city != null)
				str += "City: " + this.a_city + "\n";
			else
				str += "City: NULL\n";
			if (this.a_adress != null)
				str += "Adress: " + this.a_adress + "\n";
			else
				str += "Adress: NULL\n";
		} else {
			str = "THIS OBJECT IS NULL";
		}
		
		return str;
	}
	
}
