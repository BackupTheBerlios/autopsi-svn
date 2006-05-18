package rm.report.dao;

import rm.report.data.Artikel;
import java.util.List;
import rm.report.util.TableColumn;

public interface ArtikelDAO {
	public Artikel getArtikel(int id);
	public List<Artikel> getAlleArtikel(TableColumn orderColumn, boolean ascending);
	public int addArtikel(Artikel neuerArtikel, int validSvnr);
	public int updateArtikel(int id, Artikel neuerArtikel);
	public int delArtikel(int id);
}
