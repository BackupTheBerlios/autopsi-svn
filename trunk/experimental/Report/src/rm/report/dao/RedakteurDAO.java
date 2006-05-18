package rm.report.dao;

import rm.report.data.Redakteur;
import java.util.List;
import rm.report.util.TableColumn;

public interface RedakteurDAO {
	public Redakteur getRedakteur( int svnr);
	public int addRedakteur(Redakteur neuerRedakteur);
	public int updateRedakteur(int svnr, Redakteur newRedakteur);
	public int delRedakteur(int svnr);
	public List<Redakteur> getAlleRedakteure(TableColumn orderColumn, boolean ascending);
}