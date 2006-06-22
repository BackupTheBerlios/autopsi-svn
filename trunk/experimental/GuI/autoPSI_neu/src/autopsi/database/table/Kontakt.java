package autopsi.database.table;

import net.jini.core.entry.Entry;
import autopsi.database.dao.GenericDataObject;
import autopsi.gui.component.GSMethodForeign;
import autopsi.gui.component.GSMethodNormal;
import autopsi.gui.component.GenericData;

import java.sql.Date;

public class Kontakt extends GenericData implements Entry,GenericDataObject {

	public Integer global_id;
	public Integer kategorie_id;
	public String prename;
	public String surname;
	public Date birth_date;
	public String tel_private;
	public String tel_business;
	public String tel_mobile;
	public Integer a_zipcode;
	public String a_city;
	public String a_adress;
	public String first_email;
	public String second_email;
	
	
	public Kontakt(){
		Class cl = this.getClass();
		try{
			this.setObjectName("Kontakt");
//			this.addAttribute("GlobalId",cl.getMethod("getGlobalId", new Class[] {}), cl.getMethod("setGlobalId", new Class[] {Integer.class} ));
			GSMethodForeign meth = new GSMethodForeign();
			meth.getMethod = cl.getMethod("getGlobalId", new Class[] {});
			meth.setMethod = cl.getMethod("setGlobalId", new Class[] {Integer.class} );
			meth.tableName = "attachable_object";
			meth.attribName = "global_id";
			meth.objectClass = AttachableObject.class;
			meth.show = false;
			this.addAttribute("GlobalId", meth);
			
//			this.addAttribute("Kategorie",cl.getMethod("getKategorieId", new Class[] {}), cl.getMethod("setKategorieId", new Class[] {Integer.class} ));
			GSMethodNormal normal = new GSMethodNormal();
			normal.getMethod = cl.getMethod("getKategorieId", new Class[] {});
			normal.setMethod = cl.getMethod("setKategorieId", new Class[] {Integer.class} );
			this.setKategorieId(0);
			
			normal.show = false;
			this.addAttribute("Kategorie", normal);
			
			this.addAttribute("Vorname",cl.getMethod("getPrename", new Class[] {}), cl.getMethod("setPrename", new Class[] {String.class} ));	
			this.addAttribute("Nachname",cl.getMethod("getSurname", new Class[] {}), cl.getMethod("setSurname", new Class[] {String.class} ));
			
			this.addAttribute("Geburstag",cl.getMethod("getBirthDate", new Class[] {}), cl.getMethod("setBirthDate", new Class[] {Date.class} ));	
			this.addAttribute("Telefon privat",cl.getMethod("getTelPrivate", new Class[] {}), cl.getMethod("setTelPrivate", new Class[] {String.class} ));
			this.addAttribute("Telefon geschäftlich",cl.getMethod("getTelBusiness", new Class[] {}), cl.getMethod("setTelBusiness", new Class[] {String.class} ));
			this.addAttribute("Telefon mobil",cl.getMethod("getTelMobile", new Class[] {}), cl.getMethod("setTelMobile", new Class[] {String.class} ));
			this.addAttribute("PLZ",cl.getMethod("getAZipCode", new Class[] {}), cl.getMethod("setAZipCode", new Class[] {Integer.class} ));	
			this.addAttribute("Stadt",cl.getMethod("getACity", new Class[] {}), cl.getMethod("setACity", new Class[] {String.class} ));
			this.addAttribute("Adresse",cl.getMethod("getAAdress", new Class[] {}), cl.getMethod("setAAdress", new Class[] {String.class} ));
			this.addAttribute("Email 1",cl.getMethod("getFirstEmail", new Class[] {}), cl.getMethod("setFirstEmail", new Class[] {String.class} ));
			this.addAttribute("Email 2",cl.getMethod("getSecondEmail", new Class[] {}), cl.getMethod("setSecondEmail", new Class[] {String.class} ));
		}
		catch (Exception e){
			System.out.println("Fehler beim Erstellen des Kontakt-Objekts::"+e.toString());
		}
	}
	
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
	
	public Integer getKategorieId(){
		return this.kategorie_id;
	}
	
	public void setKategorieId(Integer kategorieId){
		this.kategorie_id = newKategorieId;
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
	
	public String getFirstEmail(){
		return this.first_email;
	}
	
	public void setFirstEmail(String email){
		this.first_email = email;
	}
	
	public String getSecondEmail(){
		return this.second_email;
	}

	public void setSecondEmail (String email){
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
