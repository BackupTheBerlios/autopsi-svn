package rm.report.data;

public class Redakteur {
	
	private int svnr;
	private String vorname="";
	private String nachname="";
	private int telNr;
	private String funktion="";
	private String kuerzel="";
	private Double gehalt=0.0;
	
	public int getSvnr(){
		return this.svnr;
	}
	
	public String getVorname(){
		return this.vorname;
	}
	
	public String getNachname(){
		return this.nachname;
	}
	
	public int getTelNr(){
		return this.telNr;
	}
	
	public String getFunktion(){
		return this.funktion;
	}
	
	public String getKuerzel(){
		return this.kuerzel;
	}
	
	public Double getGehalt(){
		return this.gehalt;
	}
	
	public void setSvnr(int newSvnr){
	
		this.svnr = newSvnr;
	}
	
	public void setVorname(String newVorname){
		
		this.vorname = newVorname;
	}

	public void setNachname(String newNachname){
	
		this.nachname = newNachname;
	}
	
	public void setTelNr(int newTelNr){
	
		this.telNr = newTelNr;
	}
	
	public void setFunktion(String newFunktion){
		
		this.funktion = newFunktion;
	}
	
	public void setKuerzel(String newKuerzel){
		
		this.kuerzel = newKuerzel;
	}
	
	public void setGehalt(Double newGehalt){
		
		this.gehalt = newGehalt;
	}
	
}