package rm.report.util;

public class RedakteurDBTable extends DBTable{
	
	public RedakteurDBTable(){
		super("Redakteur");
		
		columns.add(new TableColumn("Svnr",false,this.getName()));
		columns.add(new TableColumn("Vorname",true,this.getName()));
		columns.add(new TableColumn("Nachname",true,this.getName()));
		columns.add(new TableColumn("TelNr",true,this.getName()));
		columns.add(new TableColumn("Funktion",true,this.getName()));
		columns.add(new TableColumn("Kuerzel",true,this.getName()));
		columns.add(new TableColumn("Gehalt",true,this.getName()));
	}
}
