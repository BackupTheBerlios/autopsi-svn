package rm.report.data;

//import java.util.Date;
import java.sql.Date;

public class Artikel {
	
	
	private int id;
	private String titel="";
	private String untertitel="";
	private String text="";
	private Date datum = new Date(0);
	private boolean fertig;
	private String thema="";
	private String erstellOrt="";
	private int reporterSvnr;
	
	public int getId(){
		return id;
	}
	
	public String getTitel(){
		return titel;
	}
	
	public String getUntertitel(){
		return untertitel;
	}	

	public String getText(){
		return text;
	}

	public Date getDatum(){
		return datum;
	}

	public boolean getFertig(){
		return fertig;
	}
	
	public String getThema(){
		return thema;
	}
	
	public String getErstellOrt(){
		return erstellOrt;
	}
	
	public int getRedakteurSvnr(){
		return reporterSvnr;
	}
	
	
	public void setId(int newId){
		id = newId;
	}
	
	public void setTitel(String newTitel){
		titel = newTitel;
	}
	
	public void setUntertitel(String newUntertitel){
		untertitel = newUntertitel;
	}	

	public void setText(String newText){
		text = newText;
	}

	public void setDate(Date newDatum){
		datum = newDatum;
	}

	public void setFertig(boolean newFertig){
		fertig = newFertig;
	}
	
	public void setThema(String newThema){
		thema = newThema;
	}
	
	public void setErstellOrt( String newErstellOrt){
		erstellOrt = newErstellOrt;
	}	
	
	public void setRedakteurSvnr(int newReporterSvnr){
		reporterSvnr = newReporterSvnr;
	}
	
}
