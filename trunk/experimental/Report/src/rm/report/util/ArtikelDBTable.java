package rm.report.util;

public class ArtikelDBTable extends DBTable{

	public ArtikelDBTable(){
		super("Artikel");
		columns.add(new TableColumn("Id",false, this.getName()));
		columns.add(new TableColumn("Thema",true, this.getName()));
		columns.add(new TableColumn("Titel",true, this.getName()));
		columns.add(new TableColumn("Untertitel",true, this.getName()));
		columns.add(new TableColumn("Place_Of_Creation",true, this.getName()));
		columns.add(new TableColumn("Datum",true, this.getName()));
		columns.add(new TableColumn("Fertig",true, this.getName()));
		columns.add(new TableColumn("ReporterSvnr",true,this.getName()));
	}
	
}
