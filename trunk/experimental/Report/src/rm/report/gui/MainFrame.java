package rm.report.gui;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import rm.report.data.*;
import rm.report.gui.table.*;
import rm.report.util.*;


import java.sql.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

	public class MainFrame extends JFrame implements ActionListener{
	
	private static Log log = LogFactory.getLog(MainFrame.class);
	private DatabaseTableModel tableModel;
	private JTable table = null;
	private JComboBox sortColumn = null;
	private JScrollPane tableScrollPane = null;
	
	public MainFrame(){
		super();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				super.windowClosing(arg0);
				terminateApplication();
			}
		});
		this.tableModel = new DatabaseTableModel();
		initialize();	
	}

	private void initialize(){
		JPanel mainPanel = new JPanel();
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.PAGE_START);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Anwendung");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("beenden");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu = new JMenu("Ansicht");
		menuBar.add(menu);
		menuItem = new JMenuItem("Spalte entfernen");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Spalte hinzufügen...");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Sortieren nach...");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Tabelle Redakteur anzeigen");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Tabelle Artikel anzeigen");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu = new JMenu("Datensatz");
		menuBar.add(menu);
		menuItem = new JMenuItem("hinzufügen");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("löschen");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		this.setJMenuBar(menuBar);
		
		
		table = new JTable();
		table.setShowGrid(true);
		table.setModel(this.tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setGridColor(Color.LIGHT_GRAY);

		table.setDefaultEditor(Integer.class, new IntegerCellEditor());
		table.setDefaultEditor(Date.class, new DateCellEditor());
		table.setDefaultEditor(Boolean.class, new BooleanCellEditor());
		table.setRowHeight(30);
		
		tableScrollPane = new JScrollPane(table);
		this.add(tableScrollPane, BorderLayout.CENTER);
//		tableScrollPane.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));
		
		
		JPanel viewBar = new JPanel();
		mainPanel.add(viewBar, BorderLayout.LINE_START);
		
		JSplitPane viewSplitPane = new JSplitPane();
		mainPanel.add(viewBar, BorderLayout.LINE_START);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand();
		
		log.info("Action Performed \"" + cmd + "\"");
		
		if (cmd.equals("Spalte entfernen")){
			if (table.getSelectedColumnCount() == 1){
				tableModel.visibleColumns.remove(table.getSelectedColumn());
				tableModel.fireTableStructureChanged();
			}
		}
		
		if (cmd.equals("Spalte hinzufügen...")){
			AddColumnDialog d = null;
			if (tableModel.getCurrentTable() == Constants.TABLE_ARTIKEL)
				d = new AddColumnDialog(this, new ArtikelDBTable().getColumns());
			if (tableModel.getCurrentTable() == Constants.TABLE_REDAKTEUR)
				d = new AddColumnDialog(this, new RedakteurDBTable().getColumns());			
			
			if (d!=null)
				tableModel.addColumn(d.tc);
		}
		
		if (cmd.equals("Sortieren nach...")){
			OrderByColumnDialog d = null;
			
			if (tableModel.getCurrentTable() == Constants.TABLE_ARTIKEL)
				d = new OrderByColumnDialog(this, new ArtikelDBTable().getColumns());
			if (tableModel.getCurrentTable() == Constants.TABLE_REDAKTEUR)
				d = new OrderByColumnDialog(this, new RedakteurDBTable().getColumns());
			
			tableModel.setSort(d.tc, d.ascending);
			
			
			tableModel.updateData();
			tableModel.fireTableDataChanged();
			
		}
		
		if (cmd.equals("Tabelle Redakteur anzeigen")){
			tableModel.setVisibleColumns(Constants.TABLE_REDAKTEUR);
			tableModel.updateData();
			tableModel.fireTableStructureChanged();
		}
		
		if (cmd.equals("Tabelle Artikel anzeigen")){
			tableModel.setVisibleColumns(Constants.TABLE_ARTIKEL);
			tableModel.updateData();
			tableModel.fireTableStructureChanged();
		}		
		
		if (cmd.equals("löschen")){
			if (tableModel.getCurrentTable()==Constants.TABLE_REDAKTEUR){
				tableModel.deleteData(table.getSelectedRows());
				tableModel.fireTableDataChanged();
			}
			if (tableModel.getCurrentTable()==Constants.TABLE_ARTIKEL){
				tableModel.deleteData(table.getSelectedRows());
				tableModel.fireTableDataChanged();
			}
		}
		
		if(cmd.equals("hinzufügen")){
			tableModel.insertData();
			tableModel.fireTableDataChanged();
			tableModel.fireTableRowsInserted(0,tableModel.getRowCount());			
		}
		
		if (cmd.equals("beenden")) {
			terminateApplication();
		}

	}
		
	private void terminateApplication() {
		log.info("Closing MainFrame and Exit");
		System.exit(0);
	}
	
}
