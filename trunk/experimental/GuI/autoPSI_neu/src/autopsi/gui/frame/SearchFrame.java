package autopsi.gui.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import autopsi.basis.AutopsiConfigurator;
import autopsi.basis.model.KategorieComboBoxModel;
import autopsi.basis.model.KontaktTableModel;
import autopsi.basis.model.LVATableModel;
import autopsi.basis.model.LehrmittelTableModel;
import autopsi.basis.model.NotizTableModel;
import autopsi.basis.model.PruefungTableModel;
import autopsi.basis.model.TerminContainerTableModel;
import autopsi.basis.model.TerminTableModel;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.table.AttachableObjectKategorie;
import autopsi.database.table.Lehrmittel;
import autopsi.database.table.LehrmittelKategorie;
import autopsi.database.table.Notiz;
import autopsi.database.table.Pruefung;
import autopsi.database.table.Termin;
import autopsi.database.table.Kontakt;
import autopsi.database.table.TerminContainer;
import autopsi.database.table.TerminKategorie;
import autopsi.database.table.Lva;
import autopsi.database.table.LvaKategorie;
import autopsi.gui.component.Dialogs;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SearchFrame extends javax.swing.JFrame implements ActionListener, MouseListener, KeyListener {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static final long serialVersionUID = 18310810238219L;
	
	public static final int MAXPLZ = 6;
	public static final int MAXTELEFON = 15;

	private JTabbedPane jTabbedSearchPane;
	
	private JPanel jKontaktSuchePanel;
	private JPanel jLVASuchePanel, jPruefungSuchePanel;
	private JPanel jTerminSuchePanel, jTerminContainerSuchePanel;
	private JPanel jLehrmittelSuchePanel, jNotizSuchePanel;
	
	private JScrollPane jKontaktScrollPane;
	private JScrollPane jLVAScrollPane;
	private JScrollPane jTerminScrollPane;
	private JScrollPane jTerminContainerScrollPane;
	private JScrollPane jLehrmittelScrollPane;
	private JScrollPane jNotizScrollPane;
	private JScrollPane jPruefungScrollPane;
		
	private JTextField jVornameField, jNachnameField;
	private JTextField jTelefonnummerField, jEmailField;
	private JTextField jAdresseField, jPlzField, jOrtField;
	private JTextField jLVATitelField, jLVANummerField, jBeschreibungField;
	private JTextField jTerminTitelField, jTerminBeschreibungField;
	private JTextField jTerminContainerTitelField, jTerminContainerBeschreibungField;
	private JTextField jLehrmittelNameField, jLehrmittelBeschreibungField;
	private JTextField jNotizTitelField, jPruefungLVAField;
	private JTextField jPruefungExaminerField, jNotizNoteField;
	private JTextField jGeburtsdatumField, jDatumField;

	private JLabel jVornameLabel, jNachnameLabel, jGeburtsdatumLabel;
	private JLabel jTelefonnummerLabel, jEmailLabel, jAdresseLabel, jOrtLabel, jPlzLabel;
	private JLabel jTitelLabel, jNummerLabel, jBeschreibungLabel;
	private JLabel jTypeLabel, jDatumLabel, jLehrmittelBeschreibungLabel;
	private JLabel jKontaktGruppeLabel, jLVAGruppeLabel, jTerminGruppeLabel;
	private JLabel jTerminContainerGruppeLabel;
	private JLabel jLehrmittelNameLabel, jLehrmittelGruppeLabel;
	private JLabel jNotizTitelLabel, jNotizNoteLabel, jNotizGruppeLabel;
	private JLabel jPruefungLVALabel, jPruefungExaminerLabel, jPruefungGruppeLabel, jPruefungNoteLabel;
	
	private JTable jKontaktTable, jLVATable, jTerminTable, jTerminContainerTable, jLehrmittelTable;
	private JTable jNotizTable, jPruefungTable;
	
	private JTableHeader jKontaktTableHeader, jLVATableHeader, jLehrmittelTableHeader, jPruefungTableHeader;
	private JTableHeader jNotizTableHeader, jTerminTableHeader, jTerminContainerTableHeader;

	private TerminTableModel jTerminTableModel;
	private TerminContainerTableModel jTerminContainerTableModel;
	private KontaktTableModel jKontaktTableModel;
	private LVATableModel jLVATableModel;
	private LehrmittelTableModel jLehrmittelTableModel;
	private NotizTableModel jNotizTableModel;
	private PruefungTableModel jPruefungTableModel;
	
	private JButton jKontaktSuchenButton, jTerminSuchenButton, jLVASuchenButton;
	private JLabel lupe;
	private JButton jPruefungDownloadButton, jPruefungLoeschenButton, jPruefungWiederherstellenButton;
	private JButton jKontaktDownloadButton, jKontaktLoeschenButton, jKontaktWiederherstellenButton;
	private JButton jLVADownloadButton, jLVALoeschenButton, jLVAWiederherstellenButton;
	private JButton jLehrmittelDownloadButton, jLehrmittelLoeschenButton, jLehrmittelWiederherstellenButton;
	private JButton jTerminDownloadButton, jTerminLoeschenButton, jTerminWiederherstellenButton;
	private JButton jTerminContainerDownloadButton, jTerminContainerLoeschenButton, jTerminContainerWiederherstellenButton;
	private JButton jNotizDownloadButton, jNotizLoeschenButton, jNotizWiederherstellenButton;

	private JComboBox jGradeComboBox;
	private JButton jTerminContainerSuchenButton, jLehrmittelSuchenButton;
	private JButton jNotizSuchenButton, jPruefungSuchenButton;
	private ButtonGroup jKontaktSucheGroup,jLVASucheGroup, jTerminSucheGroup, jTerminContainerSucheGroup, jLehrmittelSucheGroup;
	private ButtonGroup jNotizSucheGroup, jPruefungSucheGroup;
	private JRadioButton jKontaktOnlineSuchenRadioButton, jKontaktLokalSuchenRadioButton;
	private JRadioButton jLVAOnlineSuchenRadioButton, jLVALokalSuchenRadioButton;
	private JRadioButton jTerminOnlineSuchenRadioButton, jTerminLokalSuchenRadioButton;
	private JRadioButton jTerminContainerOnlineSuchenRadioButton, jTerminContainerLokalSuchenRadioButton;
	private JRadioButton jLehrmittelLokalSuchenRadioButton, jLehrmittelOnlineSuchenRadioButton;
	private JRadioButton jNotizLokalSuchenRadioButton, jNotizOnlineSuchenRadioButton;
	private JRadioButton jPruefungLokalSuchenRadioButton, jPruefungOnlineSuchenRadioButton;
	
	private JSeparator jSeparator1,jSeparator2,jSeparator3,jSeparator4,jSeparator5,jSeparator6,jSeparator7;
	
	
	private JComboBox jLVATypeComboBox, jTerminTypeComboBox, jTerminContainerGruppeComboBox;
	private JComboBox jKontaktGruppeComboBox, jLVAGruppeComboBox, jTerminGruppeComboBox;
	private JComboBox jLehrmittelGruppeComboBox, jLehrmittelTypeComboBox;
	private JComboBox jNotizGruppeComboBox, jPruefungGruppeComboBox;
	
	private Dialogs Dialog;
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public SearchFrame() {
		super();
		this.Dialog = new Dialogs ();
		initGUI();
		this.setTitle("Suchen");
		this.setIconImage(new ImageIcon(AutopsiConfigurator.images + "/autopsi.png").getImage() );
		this.setResizable(false);
		//frame.getContentPane().add(this);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setSize(813, 412);

		addWindowListener(new WindowAdapter()
				{
				public void windowClosing(WindowEvent arg0)
				{ //wird das Fenster über den X-Button rechts oben geschlossen
				  //wird die Anwendung beendet.
					
					super.windowClosing(arg0);
					dispose();
					}
				});
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(813, 412));
			this.setLayout(null);
			{
				jTabbedSearchPane = new JTabbedPane();
				this.add(jTabbedSearchPane);
				jTabbedSearchPane.setBounds(7, 7, 798, 364);
				jTabbedSearchPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				jTabbedSearchPane.setTabPlacement(JTabbedPane.LEFT);
				{
					jKontaktSuchePanel = new JPanel();
					jTabbedSearchPane.addTab("Kontakt Suche", null, jKontaktSuchePanel, null);
					jKontaktSuchePanel.setPreferredSize(new java.awt.Dimension(651, 336));
					jKontaktSuchePanel.setLayout(null);
					jKontaktSuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jVornameLabel = new JLabel();
						jKontaktSuchePanel.add(jVornameLabel);
						jVornameLabel.setText("Vorname:");
						jVornameLabel.setBounds(52, 12, 49, 14);

						jNachnameLabel = new JLabel();
						jKontaktSuchePanel.add(jNachnameLabel);
						jNachnameLabel.setText("Nachname:");
						jNachnameLabel.setBounds(44, 34, 56, 14);

						jGeburtsdatumLabel = new JLabel();
						jKontaktSuchePanel.add(jGeburtsdatumLabel);
						jGeburtsdatumLabel.setText("Geburtsdatum:");
						jGeburtsdatumLabel.setBounds(26, 56, 77, 14);

						jTelefonnummerLabel = new JLabel();
						jKontaktSuchePanel.add(jTelefonnummerLabel);
						jTelefonnummerLabel.setText("Telefonnummer:");
						jTelefonnummerLabel.setBounds(20, 81, 84, 14);

						jAdresseLabel = new JLabel();
						jKontaktSuchePanel.add(jAdresseLabel);
						jAdresseLabel.setText("Adresse:");
						jAdresseLabel.setBounds(372, 34, 49, 14);

						jPlzLabel = new JLabel();
						jKontaktSuchePanel.add(jPlzLabel);
						jPlzLabel.setText("Postleitzahl:");
						jPlzLabel.setBounds(357, 56, 63, 14);

						jOrtLabel = new JLabel();
						jKontaktSuchePanel.add(jOrtLabel);
						jOrtLabel.setText("Ort:");
						jOrtLabel.setBounds(395, 80, 21, 14);
						
						jEmailLabel = new JLabel();
						jKontaktSuchePanel.add(jEmailLabel);
						jEmailLabel.setText("Email:");
						jEmailLabel.setBounds(386, 12, 35, 14);

						jVornameField = new JTextField();
						jKontaktSuchePanel.add(jVornameField);
						jVornameField.setBounds(105, 7, 210, 21);
						jVornameField.addKeyListener(this);

						jNachnameField = new JTextField();
						jKontaktSuchePanel.add(jNachnameField);
						jNachnameField.setBounds(105, 30, 210, 21);
						jNachnameField.addKeyListener(this);

						jGeburtsdatumField = new JTextField();
						jKontaktSuchePanel.add(jGeburtsdatumField);
						jGeburtsdatumField.setBounds(105, 53, 210, 21);
						jGeburtsdatumField.setDocument(new PlainDocument() {
					    	private static final long serialVersionUID = 8723098029189851737L;
							public void insertString(int offset, String str, AttributeSet a)
									throws BadLocationException {
								// Eingaben von Buchstaben ist nicht erlaubt...
								if (!str.matches(".*[[0-9]|-].*"))
									return;
								//Höchstens 15 Zeichen
								if (offset>9)
									return;
								if (offset==1)
									super.insertString(offset, "-", a);
								if (offset==4)
									super.insertString(offset, "-", a);
									
								super.insertString(offset, str, a);
							}
						});
						jGeburtsdatumField.addKeyListener(this);
						

						jTelefonnummerField = new JTextField();
						jKontaktSuchePanel.add(jTelefonnummerField);
						jTelefonnummerField.setBounds(105, 76, 210, 21);
						jTelefonnummerField.setDocument(new PlainDocument() {
					    	private static final long serialVersionUID = 8723098029189851737L;
							public void insertString(int offset, String str, AttributeSet a)
									throws BadLocationException {
								// Eingaben von Buchstaben ist nicht erlaubt...
								if (!str.matches(".*[[0-9]|/].*"))
									return;
								//Höchstens 15 Zeichen
								if (offset>MAXTELEFON)
									return;
								super.insertString(offset, str, a);
							}
						});
						jTelefonnummerField.addKeyListener(this);
						
						jEmailField = new JTextField();
						jKontaktSuchePanel.add(jEmailField);
						jEmailField.setBounds(420, 7, 210, 21);
						jEmailField.addKeyListener(this);

						jAdresseField = new JTextField();
						jKontaktSuchePanel.add(jAdresseField);
						jAdresseField.setBounds(420, 30, 210, 21);
						jAdresseField.addKeyListener(this);
						
						jPlzField = new JTextField();
						jKontaktSuchePanel.add(jPlzField);
						jPlzField.setBounds(420, 53, 210, 21);
						jPlzField.setDocument(new PlainDocument() {
					    	private static final long serialVersionUID = 8723098029189851737L;
							public void insertString(int offset, String str, AttributeSet a)
									throws BadLocationException {
								// Eingabe beschraenken
								if (!str.matches(".*[[0-9]].*"))
									return;
								//Höchstens X Zeichen
								if (offset>MAXPLZ)
									return;
								super.insertString(offset, str, a);
							}
						});
						jPlzField.addKeyListener(this);
						
						jOrtField = new JTextField();
						jKontaktSuchePanel.add(jOrtField);
						jOrtField.setBounds(420, 76, 210, 21);
						jOrtField.addKeyListener(this);
						
						{
							jSeparator1 = new JSeparator();
							jKontaktSuchePanel.add(jSeparator1);
							jSeparator1.setBounds(7, 140, 630, 7);
						}
						{
							jKontaktSuchenButton = new JButton();
							jKontaktSuchePanel.add(jKontaktSuchenButton);
							jKontaktSuchenButton.setText("Kontakt Suchen");
							jKontaktSuchenButton.setBounds(68, 105, 154, 28);
							jKontaktSuchenButton.addActionListener(this);
							
							jKontaktLokalSuchenRadioButton = new JRadioButton();
							jKontaktSuchePanel.add(jKontaktLokalSuchenRadioButton);
							jKontaktLokalSuchenRadioButton.setText("Lokal Suchen");
							jKontaktLokalSuchenRadioButton.setBounds(231, 105, 91, 14);
	
							jKontaktOnlineSuchenRadioButton = new JRadioButton();
							jKontaktSuchePanel.add(jKontaktOnlineSuchenRadioButton);
							jKontaktOnlineSuchenRadioButton.setText("Online Suchen");
							jKontaktOnlineSuchenRadioButton.setBounds(231, 119, 98, 14);
							
							jKontaktLokalSuchenRadioButton.setSelected(true);
							jKontaktSucheGroup = new ButtonGroup();
							jKontaktSucheGroup.add(jKontaktLokalSuchenRadioButton);
							jKontaktLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							jKontaktSucheGroup.add(jKontaktOnlineSuchenRadioButton);				
							jKontaktOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							
							jKontaktGruppeLabel = new JLabel();
							jKontaktSuchePanel.add(jKontaktGruppeLabel);
							jKontaktGruppeLabel.setText("Gruppe:");
							jKontaktGruppeLabel.setBounds(348, 112, 42, 14);
		
							KategorieComboBoxModel jKontaktGruppeComboBoxModel = new KategorieComboBoxModel("ATTACHABLE_OBJECT_KATEGORIE", new AttachableObjectKategorie());
							jKontaktGruppeComboBox = new JComboBox();
							jKontaktSuchePanel.add(jKontaktGruppeComboBox);
							jKontaktGruppeComboBox
								.setModel(jKontaktGruppeComboBoxModel);
							jKontaktGruppeComboBox.setBounds(392, 109, 175, 21);
						}

						{
							jKontaktScrollPane = new JScrollPane();
							jKontaktSuchePanel.add(jKontaktScrollPane);
							jKontaktScrollPane.setBounds(21, 147, 609, 175);
							jKontaktScrollPane.setWheelScrollingEnabled(true);
							jKontaktScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jKontaktTableModel = new KontaktTableModel();
								jKontaktTable = new JTable();
								jKontaktScrollPane
									.setViewportView(jKontaktTable);
								jKontaktTable.setModel(jKontaktTableModel);
								jKontaktTable.setBounds(7, 252, 610, 56);
								jKontaktTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jKontaktTable.setShowGrid(true);
								jKontaktTable.setGridColor(Color.LIGHT_GRAY);
								jKontaktTable.setBackground(new java.awt.Color(255,255,255));
								jKontaktTable.addMouseListener(this);
								jKontaktTableHeader = jKontaktTable.getTableHeader();
								jKontaktTableHeader.addMouseListener(this);
								
							}
						}
						
						{
							jKontaktLoeschenButton = new JButton();
							jKontaktSuchePanel.add(jKontaktLoeschenButton);
							jKontaktLoeschenButton.setText("Kontakt(e) Löschen");
							jKontaktLoeschenButton.setBounds(21, 329, 133, 21);
							jKontaktLoeschenButton.addActionListener(this);
						}
						{
							jKontaktWiederherstellenButton = new JButton();
							jKontaktSuchePanel.add(jKontaktWiederherstellenButton);
							jKontaktWiederherstellenButton.setText("Gelöschte Kontakt(e) Wiederherstellen");
							jKontaktWiederherstellenButton.setBounds(161, 329, 231, 21);
							jKontaktWiederherstellenButton.addActionListener(this);
						}
						{
							jKontaktDownloadButton = new JButton();
							jKontaktSuchePanel.add(jKontaktDownloadButton);
							jKontaktDownloadButton.setText("Kontakt Herunterladen");
							jKontaktDownloadButton.setBounds(483, 329, 147, 21);
							jKontaktDownloadButton.addActionListener(this);
						}

					}
					
					
					// #####################################################################
					
					jLVASuchePanel = new JPanel();
					jTabbedSearchPane.addTab("LVA Suche", null, jLVASuchePanel, null);
					jLVASuchePanel.setPreferredSize(new java.awt.Dimension(671, 357));
					jLVASuchePanel.setLayout(null);
					jLVASuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jTitelLabel = new JLabel();
						jLVASuchePanel.add(jTitelLabel);
						jTitelLabel.setText("Titel:");
						jTitelLabel.setBounds(74, 12, 28, 14);

						jNummerLabel = new JLabel();
						jLVASuchePanel.add(jNummerLabel);
						jNummerLabel.setText("Nummer:");
						jNummerLabel.setBounds(54, 35, 49, 14);

						jBeschreibungLabel = new JLabel();
						jLVASuchePanel.add(jBeschreibungLabel);
						jBeschreibungLabel.setText("Beschreibung:");
						jBeschreibungLabel.setBounds(29, 56, 70, 14);

						jTypeLabel = new JLabel();
						jLVASuchePanel.add(jTypeLabel);
						jTypeLabel.setText("Type:");
						jTypeLabel.setBounds(385, 11, 35, 14);

	
						
						jLVATitelField = new JTextField();
						jLVASuchePanel.add(jLVATitelField);
						jLVATitelField.setBounds(105, 7, 210, 21);
						jLVATitelField.addKeyListener(this);


						jLVANummerField = new JTextField();
						jLVASuchePanel.add(jLVANummerField);
						jLVANummerField.setBounds(105, 30, 210, 21);
						jLVANummerField.addKeyListener(this);

						jBeschreibungField = new JTextField();
						jLVASuchePanel.add(jBeschreibungField);
						jBeschreibungField.setBounds(105, 53, 210, 21);
						jBeschreibungField.addKeyListener(this);
						
						KategorieComboBoxModel jLVATypeComboBoxModel = new KategorieComboBoxModel("LVA_KATEGORIE", new LvaKategorie());
						jLVATypeComboBox = new JComboBox();
						jLVASuchePanel.add(jLVATypeComboBox);
						jLVATypeComboBox.setModel(jLVATypeComboBoxModel);
						jLVATypeComboBox.setBounds(420, 7, 210, 21);

						{
							jSeparator2 = new JSeparator();
							jLVASuchePanel.add(jSeparator2);
							jSeparator2.setBounds(7, 140, 630, 7);
						}
						{
							jLVASuchenButton = new JButton();
							jLVASuchePanel.add(jLVASuchenButton);
							jLVASuchenButton.setText("LVA Suchen");
							jLVASuchenButton.setBounds(68, 105, 154, 28);
							jLVASuchenButton.addActionListener(this);
							
							jLVALokalSuchenRadioButton = new JRadioButton();
							jLVASuchePanel.add(jLVALokalSuchenRadioButton);
							jLVALokalSuchenRadioButton.setText("Lokal Suchen");
							jLVALokalSuchenRadioButton.setBounds(231, 105, 91, 14);
	
							jLVAOnlineSuchenRadioButton = new JRadioButton();
							jLVASuchePanel.add(jLVAOnlineSuchenRadioButton);
							jLVAOnlineSuchenRadioButton.setText("Online Suchen");
							jLVAOnlineSuchenRadioButton.setBounds(231, 119, 98, 14);
							
							jLVALokalSuchenRadioButton.setSelected(true);
							jLVASucheGroup = new ButtonGroup();
							jLVASucheGroup.add(jLVALokalSuchenRadioButton);
							jLVALokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							jLVASucheGroup.add(jLVAOnlineSuchenRadioButton);				
							jLVAOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							
							jLVAGruppeLabel = new JLabel();
							jLVASuchePanel.add(jLVAGruppeLabel);
							jLVAGruppeLabel.setText("Gruppe:");
							jLVAGruppeLabel.setBounds(348, 112, 42, 14);
		
							KategorieComboBoxModel jLVAGruppeComboBoxModel = new KategorieComboBoxModel("ATTACHABLE_OBJECT_KATEGORIE", new AttachableObjectKategorie());
							jLVAGruppeComboBox = new JComboBox();
							jLVASuchePanel.add(jLVAGruppeComboBox);
							jLVAGruppeComboBox
								.setModel(jLVAGruppeComboBoxModel);
							jLVAGruppeComboBox.setBounds(392, 109, 175, 21);
						}

						{
							jLVAScrollPane = new JScrollPane();
							jLVASuchePanel.add(jLVAScrollPane);
							jLVAScrollPane.setBounds(21, 147, 609, 175);
							jLVAScrollPane.setWheelScrollingEnabled(true);
							jLVAScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jLVATableModel = new LVATableModel ();
								jLVATable = new JTable();
								jLVAScrollPane
									.setViewportView(jLVATable);
								jLVATable.setModel(jLVATableModel);
								jLVATable.setBounds(7, 252, 610, 56);
								jLVATable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jLVATable.setShowGrid(true);
								jLVATable.setGridColor(Color.LIGHT_GRAY);
								jLVATable.addMouseListener(this);
								jLVATableHeader = jLVATable.getTableHeader();
								jLVATableHeader.addMouseListener(this);
							}
						}
						
						{
							jLVALoeschenButton = new JButton();
							jLVASuchePanel.add(jLVALoeschenButton);
							jLVALoeschenButton.setText("LVA(s) Löschen");
							jLVALoeschenButton.setBounds(21, 329, 112, 21);
							jLVALoeschenButton.addActionListener(this);
						}
						{
							jLVAWiederherstellenButton = new JButton();
							jLVASuchePanel.add(jLVAWiederherstellenButton);
							jLVAWiederherstellenButton.setText("Gelöschte LVA(s) Wiederherstellen");
							jLVAWiederherstellenButton.setBounds(140, 329, 231, 21);
							jLVAWiederherstellenButton.addActionListener(this);
						}
						{
							jLVADownloadButton = new JButton();
							jLVASuchePanel.add(jLVADownloadButton);
							jLVADownloadButton.setText("LVA Herunterladen");
							jLVADownloadButton.setBounds(483, 329, 147, 21);
							jLVADownloadButton.addActionListener(this);
						}

					}
					
// #####################################################################
					
					jTerminSuchePanel = new JPanel();
					jTabbedSearchPane.addTab("Termin Suche", null, jTerminSuchePanel, null);
					jTerminSuchePanel.setPreferredSize(new java.awt.Dimension(671, 357));
					jTerminSuchePanel.setLayout(null);
					jTerminSuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jTitelLabel = new JLabel();
						jTerminSuchePanel.add(jTitelLabel);
						jTitelLabel.setText("Titel:");
						jTitelLabel.setBounds(74, 12, 28, 14);

						jBeschreibungLabel = new JLabel();
						jTerminSuchePanel.add(jBeschreibungLabel);
						jBeschreibungLabel.setText("Beschreibung:");
						jBeschreibungLabel.setBounds(30, 35, 70, 14);

						jDatumLabel = new JLabel();
						jTerminSuchePanel.add(jDatumLabel);
						jDatumLabel.setText("Datum:");
						jDatumLabel.setBounds(377, 13, 35, 14);

						jTypeLabel = new JLabel();
						jTerminSuchePanel.add(jTypeLabel);
						jTypeLabel.setText("Type:");
						jTypeLabel.setBounds(385, 35, 28, 14);

	
						
						jTerminTitelField = new JTextField();
						jTerminSuchePanel.add(jTerminTitelField);
						jTerminTitelField.setBounds(105, 7, 210, 21);
						jTerminTitelField.addKeyListener(this);


						jTerminBeschreibungField = new JTextField();
						jTerminSuchePanel.add(jTerminBeschreibungField);
						jTerminBeschreibungField.setBounds(105, 30, 210, 21);
						jTerminBeschreibungField.addKeyListener(this);

												
						jDatumField = new JTextField();
						jTerminSuchePanel.add(jDatumField);
						jDatumField.setBounds(420, 7, 210, 21);
						jDatumField.setDocument(new PlainDocument() {
					    	private static final long serialVersionUID = 8723098029189851737L;
							public void insertString(int offset, String str, AttributeSet a)
									throws BadLocationException {
								// Eingaben von Buchstaben ist nicht erlaubt...
								if (!str.matches(".*[[0-9]|-].*"))
									return;
								//Höchstens 9 Zeichen
								if (offset>9)
									return;
								if (offset==1)
									super.insertString(offset, "-", a);
								if (offset==4)
									super.insertString(offset, "-", a);
									
								super.insertString(offset, str, a);
							}
						});
						jDatumField.addKeyListener(this);
						
						KategorieComboBoxModel jTerminTypeComboBoxModel = new KategorieComboBoxModel("TERMIN_KATEGORIE", new TerminKategorie());
						jTerminTypeComboBox = new JComboBox();
						jTerminSuchePanel.add(jTerminTypeComboBox);
						jTerminTypeComboBox.setModel(jTerminTypeComboBoxModel);
						jTerminTypeComboBox.setBounds(420, 30, 210, 21);
						
						

						{
							jSeparator3 = new JSeparator();
							jTerminSuchePanel.add(jSeparator3);
							jSeparator3.setBounds(7, 140, 630, 7);
						}
						{
							jTerminSuchenButton = new JButton();
							jTerminSuchePanel.add(jTerminSuchenButton);
							jTerminSuchenButton.setText("Termin Suchen");
							jTerminSuchenButton.setBounds(68, 105, 154, 28);
							jTerminSuchenButton.addActionListener(this);
							
							jTerminLokalSuchenRadioButton = new JRadioButton();
							jTerminSuchePanel.add(jTerminLokalSuchenRadioButton);
							jTerminLokalSuchenRadioButton.setText("Lokal Suchen");
							jTerminLokalSuchenRadioButton.setBounds(231, 105, 91, 14);
							jTerminLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
	
							jTerminOnlineSuchenRadioButton = new JRadioButton();
							jTerminSuchePanel.add(jTerminOnlineSuchenRadioButton);
							jTerminOnlineSuchenRadioButton.setText("Online Suchen");
							jTerminOnlineSuchenRadioButton.setBounds(231, 119, 98, 14);
							jTerminOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							
							jTerminLokalSuchenRadioButton.setSelected(true);
							jTerminSucheGroup = new ButtonGroup();
							jTerminSucheGroup.add(jTerminLokalSuchenRadioButton);
							jTerminSucheGroup.add(jTerminOnlineSuchenRadioButton);				
							
							jTerminGruppeLabel = new JLabel();
							jTerminSuchePanel.add(jTerminGruppeLabel);
							jTerminGruppeLabel.setText("Gruppe:");
							jTerminGruppeLabel.setBounds(348, 112, 42, 14);
		
							KategorieComboBoxModel jTerminGruppeComboBoxModel = new KategorieComboBoxModel("ATTACHABLE_OBJECT_KATEGORIE", new AttachableObjectKategorie());
							jTerminGruppeComboBox = new JComboBox();
							jTerminSuchePanel.add(jTerminGruppeComboBox);
							jTerminGruppeComboBox
								.setModel(jTerminGruppeComboBoxModel);
							jTerminGruppeComboBox.setBounds(392, 109, 175, 21);
						}
							

						{
							jTerminScrollPane = new JScrollPane();
							jTerminSuchePanel.add(jTerminScrollPane);
							jTerminScrollPane.setBounds(21, 147, 609, 175);
							jTerminScrollPane.setWheelScrollingEnabled(true);
							jTerminScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jTerminTableModel = new TerminTableModel();
								jTerminTable = new JTable();
								jTerminScrollPane.setViewportView(jTerminTable);
								jTerminTable.setModel(jTerminTableModel);
								jTerminTable.setBounds(7, 252, 610, 56);
								jTerminTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jTerminTable.setShowGrid(true);
								jTerminTable.setGridColor(Color.LIGHT_GRAY);
								jTerminTable.addMouseListener(this);
								jTerminTableHeader = jTerminTable.getTableHeader();
								jTerminTableHeader.addMouseListener(this);
							}
						}
						
						{
							jTerminLoeschenButton = new JButton();
							jTerminSuchePanel.add(jTerminLoeschenButton);
							jTerminLoeschenButton.setText("Termin Löschen");
							jTerminLoeschenButton.setBounds(21, 329, 112, 21);
							jTerminLoeschenButton.addActionListener(this);
						}
						{
							jTerminWiederherstellenButton = new JButton();
							jTerminSuchePanel.add(jTerminWiederherstellenButton);
							jTerminWiederherstellenButton.setText("Gelöschte(n) Termin(e) Wiederherstellen");
							jTerminWiederherstellenButton.setBounds(140, 329, 231, 21);
							jTerminWiederherstellenButton.addActionListener(this);
						}
						{
							jTerminDownloadButton = new JButton();
							jTerminSuchePanel.add(jTerminDownloadButton);
							jTerminDownloadButton.setText("Termin herunterladen");
							jTerminDownloadButton.setBounds(483, 329, 147, 21);
							jTerminDownloadButton.addActionListener(this);
						}

					}
					
// #####################################################################
					
					jTerminContainerSuchePanel = new JPanel();
					jTabbedSearchPane.addTab("TerminContainer Suche", null, jTerminContainerSuchePanel, null);
					jTerminContainerSuchePanel.setPreferredSize(new java.awt.Dimension(671, 357));
					jTerminContainerSuchePanel.setLayout(null);
					jTerminContainerSuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jTitelLabel = new JLabel();
						jTerminContainerSuchePanel.add(jTitelLabel);
						jTitelLabel.setText("Titel:");
						jTitelLabel.setBounds(74, 12, 28, 14);

						jBeschreibungLabel = new JLabel();
						jTerminContainerSuchePanel.add(jBeschreibungLabel);
						jBeschreibungLabel.setText("Beschreibung:");
						jBeschreibungLabel.setBounds(30, 35, 70, 14);	
						
						jTerminContainerTitelField = new JTextField();
						jTerminContainerSuchePanel.add(jTerminContainerTitelField);
						jTerminContainerTitelField.setBounds(105, 7, 210, 21);
						jTerminContainerTitelField.addKeyListener(this);


						jTerminContainerBeschreibungField = new JTextField();
						jTerminContainerSuchePanel.add(jTerminContainerBeschreibungField);
						jTerminContainerBeschreibungField.setBounds(105, 30, 210, 21);
						jTerminContainerBeschreibungField.addKeyListener(this);

						{
							jSeparator4 = new JSeparator();
							jTerminContainerSuchePanel.add(jSeparator4);
							jSeparator4.setBounds(7, 140, 630, 7);
						}
						{
							jTerminContainerSuchenButton = new JButton();
							jTerminContainerSuchePanel.add(jTerminContainerSuchenButton);
							jTerminContainerSuchenButton.setText("Termincontainer Suchen");
							jTerminContainerSuchenButton.setBounds(68, 105, 154, 28);
							jTerminContainerSuchenButton.addActionListener(this);
							
							jTerminContainerLokalSuchenRadioButton = new JRadioButton();
							jTerminContainerSuchePanel.add(jTerminContainerLokalSuchenRadioButton);
							jTerminContainerLokalSuchenRadioButton.setText("Lokal Suchen");
							jTerminContainerLokalSuchenRadioButton.setBounds(231, 105, 91, 14);
							jTerminContainerLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
	
							jTerminContainerOnlineSuchenRadioButton = new JRadioButton();
							jTerminContainerSuchePanel.add(jTerminContainerOnlineSuchenRadioButton);
							jTerminContainerOnlineSuchenRadioButton.setText("Online Suchen");
							jTerminContainerOnlineSuchenRadioButton.setBounds(231, 119, 98, 14);
							jTerminContainerOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							
							jTerminContainerLokalSuchenRadioButton.setSelected(true);
							jTerminContainerSucheGroup = new ButtonGroup();
							jTerminContainerSucheGroup.add(jTerminContainerLokalSuchenRadioButton);
							jTerminContainerSucheGroup.add(jTerminContainerOnlineSuchenRadioButton);				
							
							jTerminContainerGruppeLabel = new JLabel();
							jTerminContainerSuchePanel.add(jTerminContainerGruppeLabel);
							jTerminContainerGruppeLabel.setText("Gruppe:");
							jTerminContainerGruppeLabel.setBounds(348, 112, 42, 14);
		
							KategorieComboBoxModel jTerminContainerGruppeComboBoxModel = new KategorieComboBoxModel("ATTACHABLE_OBJECT_KATEGORIE", new AttachableObjectKategorie());
							jTerminContainerGruppeComboBox = new JComboBox();
							jTerminContainerSuchePanel.add(jTerminContainerGruppeComboBox);
							jTerminContainerGruppeComboBox
								.setModel(jTerminContainerGruppeComboBoxModel);
							jTerminContainerGruppeComboBox.setBounds(392, 109, 175, 21);
						}
							

						{
							jTerminContainerScrollPane = new JScrollPane();
							jTerminContainerSuchePanel.add(jTerminContainerScrollPane);
							jTerminContainerScrollPane.setBounds(21, 147, 609, 175);
							jTerminContainerScrollPane.setWheelScrollingEnabled(true);
							jTerminContainerScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jTerminContainerTableModel = new TerminContainerTableModel();
								jTerminContainerTable = new JTable();
								jTerminContainerScrollPane.setViewportView(jTerminContainerTable);
								jTerminContainerTable.setModel(jTerminContainerTableModel);
								jTerminContainerTable.setBounds(7, 252, 610, 56);
								jTerminContainerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jTerminContainerTable.setShowGrid(true);
								jTerminContainerTable.setGridColor(Color.LIGHT_GRAY);
								jTerminContainerTable.addMouseListener(this);
								jTerminContainerTableHeader = jTerminContainerTable.getTableHeader();
								jTerminContainerTableHeader.addMouseListener(this);
							}
						}
						
						{
							jTerminContainerLoeschenButton = new JButton();
							jTerminContainerSuchePanel.add(jTerminContainerLoeschenButton);
							jTerminContainerLoeschenButton.setText("Termincontainer Löschen");
							jTerminContainerLoeschenButton.setBounds(21, 329, 154, 21);
							jTerminContainerLoeschenButton.addActionListener(this);
						}
						{
							jTerminContainerWiederherstellenButton = new JButton();
							jTerminContainerSuchePanel.add(jTerminContainerWiederherstellenButton);
							jTerminContainerWiederherstellenButton.setText("Gelöschte(n) Termincontainer Wiederherstellen");
							jTerminContainerWiederherstellenButton.setBounds(182, 329, 266, 21);
							jTerminContainerWiederherstellenButton.addActionListener(this);
						}
						{
							jTerminContainerDownloadButton = new JButton();
							jTerminContainerSuchePanel.add(jTerminContainerDownloadButton);
							jTerminContainerDownloadButton.setText("Termincontainer herunterladen");
							jTerminContainerDownloadButton.setBounds(483, 329, 147, 21);
							jTerminContainerDownloadButton.addActionListener(this);
						}

					}
					
// #####################################################################
					
					jLehrmittelSuchePanel = new JPanel();
					jTabbedSearchPane.addTab("Lehrmittel Suche", null, jLehrmittelSuchePanel, null);
					jLehrmittelSuchePanel.setPreferredSize(new java.awt.Dimension(671, 357));
					jLehrmittelSuchePanel.setLayout(null);
					jLehrmittelSuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jLehrmittelNameLabel = new JLabel();
						jLehrmittelSuchePanel.add(jLehrmittelNameLabel);
						jLehrmittelNameLabel.setText("Titel:");
						jLehrmittelNameLabel.setBounds(74, 12, 28, 14);

						jLehrmittelBeschreibungLabel = new JLabel();
						jLehrmittelSuchePanel.add(jLehrmittelBeschreibungLabel);
						jLehrmittelBeschreibungLabel.setText("Beschreibung:");
						jLehrmittelBeschreibungLabel.setBounds(30, 35, 70, 14);

						jTypeLabel = new JLabel();
						jLehrmittelSuchePanel.add(jTypeLabel);
						jTypeLabel.setText("Type:");
						jTypeLabel.setBounds(384, 12, 35, 14);

	
						
						jLehrmittelNameField = new JTextField();
						jLehrmittelSuchePanel.add(jLehrmittelNameField);
						jLehrmittelNameField.setBounds(105, 7, 210, 21);
						jLehrmittelNameField.addKeyListener(this);


						jLehrmittelBeschreibungField = new JTextField();
						jLehrmittelSuchePanel.add(jLehrmittelBeschreibungField);
						jLehrmittelBeschreibungField.setBounds(105, 30, 210, 21);
						jLehrmittelBeschreibungField.addKeyListener(this);
						
						KategorieComboBoxModel jLehrmittelTypeComboBoxModel = new KategorieComboBoxModel("Lehrmittel_KATEGORIE", new LehrmittelKategorie());
						jLehrmittelTypeComboBox = new JComboBox();
						jLehrmittelSuchePanel.add(jLehrmittelTypeComboBox);
						jLehrmittelTypeComboBox.setModel(jLehrmittelTypeComboBoxModel);
						jLehrmittelTypeComboBox.setBounds(420, 7, 210, 21);

						{
							jSeparator5 = new JSeparator();
							jLehrmittelSuchePanel.add(jSeparator5);
							jSeparator5.setBounds(7, 140, 630, 7);
						}
						{
							jLehrmittelSuchenButton = new JButton();
							jLehrmittelSuchePanel.add(jLehrmittelSuchenButton);
							jLehrmittelSuchenButton.setText("Lehrmittel Suchen");
							jLehrmittelSuchenButton.setBounds(68, 105, 154, 28);
							jLehrmittelSuchenButton.addActionListener(this);
							
							jLehrmittelLokalSuchenRadioButton = new JRadioButton();
							jLehrmittelSuchePanel.add(jLehrmittelLokalSuchenRadioButton);
							jLehrmittelLokalSuchenRadioButton.setText("Lokal Suchen");
							jLehrmittelLokalSuchenRadioButton.setBounds(231, 105, 91, 14);
	
							jLehrmittelOnlineSuchenRadioButton = new JRadioButton();
							jLehrmittelSuchePanel.add(jLehrmittelOnlineSuchenRadioButton);
							jLehrmittelOnlineSuchenRadioButton.setText("Online Suchen");
							jLehrmittelOnlineSuchenRadioButton.setBounds(231, 119, 98, 14);
							
							jLehrmittelLokalSuchenRadioButton.setSelected(true);
							jLehrmittelSucheGroup = new ButtonGroup();
							jLehrmittelSucheGroup.add(jLehrmittelLokalSuchenRadioButton);
							jLehrmittelLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							jLehrmittelSucheGroup.add(jLehrmittelOnlineSuchenRadioButton);				
							jLehrmittelOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							
							jLehrmittelGruppeLabel = new JLabel();
							jLehrmittelSuchePanel.add(jLehrmittelGruppeLabel);
							jLehrmittelGruppeLabel.setText("Gruppe:");
							jLehrmittelGruppeLabel.setBounds(348, 112, 42, 14);
		
							KategorieComboBoxModel jLehrmittelGruppeComboBoxModel = new KategorieComboBoxModel("ATTACHABLE_OBJECT_KATEGORIE", new AttachableObjectKategorie());
							jLehrmittelGruppeComboBox = new JComboBox();
							jLehrmittelSuchePanel.add(jLehrmittelGruppeComboBox);
							jLehrmittelGruppeComboBox
								.setModel(jLehrmittelGruppeComboBoxModel);
							jLehrmittelGruppeComboBox.setBounds(392, 109, 175, 21);
						}

						{
							jLehrmittelScrollPane = new JScrollPane();
							jLehrmittelSuchePanel.add(jLehrmittelScrollPane);
							jLehrmittelScrollPane.setBounds(21, 147, 609, 175);
							jLehrmittelScrollPane.setWheelScrollingEnabled(true);
							jLehrmittelScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jLehrmittelTableModel = new LehrmittelTableModel ();
								jLehrmittelTable = new JTable();
								jLehrmittelScrollPane
									.setViewportView(jLehrmittelTable);
								jLehrmittelTable.setModel(jLehrmittelTableModel);
								jLehrmittelTable.setBounds(7, 252, 610, 56);
								jLehrmittelTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jLehrmittelTable.setShowGrid(true);
								jLehrmittelTable.setGridColor(Color.LIGHT_GRAY);
								jLehrmittelTable.addMouseListener(this);
								jLehrmittelTableHeader = jLehrmittelTable.getTableHeader();
								jLehrmittelTableHeader.addMouseListener(this);
							}
						}
						
						{
							jLehrmittelLoeschenButton = new JButton();
							jLehrmittelSuchePanel.add(jLehrmittelLoeschenButton);
							jLehrmittelLoeschenButton.setText("Lehrmittel Löschen");
							jLehrmittelLoeschenButton.setBounds(21, 329, 126, 21);
							jLehrmittelLoeschenButton.addActionListener(this);
						}
						{
							jLehrmittelWiederherstellenButton = new JButton();
							jLehrmittelSuchePanel.add(jLehrmittelWiederherstellenButton);
							jLehrmittelWiederherstellenButton.setText("Gelöschte(s) Lehrmittel Wiederherstellen");
							jLehrmittelWiederherstellenButton.setBounds(154, 329, 231, 21);
							jLehrmittelWiederherstellenButton.addActionListener(this);
						}
						{
							jLehrmittelDownloadButton = new JButton();
							jLehrmittelSuchePanel.add(jLehrmittelDownloadButton);
							jLehrmittelDownloadButton.setText("Lehrmittel Herunterladen");
							jLehrmittelDownloadButton.setBounds(476, 329, 154, 21);
							jLehrmittelDownloadButton.addActionListener(this);
						}
						

					}
					
// #####################################################################
					
// #####################################################################
					
					jNotizSuchePanel = new JPanel();
					jNotizSuchePanel.setPreferredSize(new java.awt.Dimension(671, 357));
					jNotizSuchePanel.setLayout(null);
					jNotizSuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jNotizTitelLabel = new JLabel();
						jNotizSuchePanel.add(jNotizTitelLabel);
						jNotizTitelLabel.setText("Titel:");
						jNotizTitelLabel.setBounds(74, 12, 28, 14);

						jNotizNoteLabel = new JLabel();
						jNotizSuchePanel.add(jNotizNoteLabel);
						jNotizNoteLabel.setText("Notiz:");
						jNotizNoteLabel.setBounds(70, 35, 35, 14);


	
						
						jNotizTitelField = new JTextField();
						jNotizSuchePanel.add(jNotizTitelField);
						jNotizTitelField.setBounds(105, 7, 210, 21);
						jNotizTitelField.addKeyListener(this);


						jNotizNoteField = new JTextField();
						jNotizSuchePanel.add(jNotizNoteField);
						jNotizNoteField.setBounds(105, 30, 210, 21);
						jNotizNoteField.addKeyListener(this);


						{
							jSeparator6 = new JSeparator();
							jNotizSuchePanel.add(jSeparator6);
							jSeparator6.setBounds(7, 140, 630, 7);
						}
						{
							jNotizSuchenButton = new JButton();
							jNotizSuchePanel.add(jNotizSuchenButton);
							jNotizSuchenButton.setText("Notiz Suchen");
							jNotizSuchenButton.setBounds(68, 105, 154, 28);
							jNotizSuchenButton.addActionListener(this);
							
							jNotizLokalSuchenRadioButton = new JRadioButton();
							jNotizSuchePanel.add(jNotizLokalSuchenRadioButton);
							jNotizLokalSuchenRadioButton.setText("Lokal Suchen");
							jNotizLokalSuchenRadioButton.setBounds(231, 105, 91, 14);
	
							jNotizOnlineSuchenRadioButton = new JRadioButton();
							jNotizSuchePanel.add(jNotizOnlineSuchenRadioButton);
							jNotizOnlineSuchenRadioButton.setText("Online Suchen");
							jNotizOnlineSuchenRadioButton.setBounds(231, 119, 98, 14);
							
							jNotizLokalSuchenRadioButton.setSelected(true);
							jNotizSucheGroup = new ButtonGroup();
							jNotizSucheGroup.add(jNotizLokalSuchenRadioButton);
							jNotizLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							jNotizSucheGroup.add(jNotizOnlineSuchenRadioButton);				
							jNotizOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							
							jNotizGruppeLabel = new JLabel();
							jNotizSuchePanel.add(jNotizGruppeLabel);
							jNotizGruppeLabel.setText("Gruppe:");
							jNotizGruppeLabel.setBounds(348, 112, 42, 14);
		
							KategorieComboBoxModel jNotizGruppeComboBoxModel = new KategorieComboBoxModel("ATTACHABLE_OBJECT_KATEGORIE", new AttachableObjectKategorie());
							jNotizGruppeComboBox = new JComboBox();
							jNotizSuchePanel.add(jNotizGruppeComboBox);
							jNotizGruppeComboBox
								.setModel(jNotizGruppeComboBoxModel);
							jNotizGruppeComboBox.setBounds(392, 109, 175, 21);
						}

						{
							jNotizScrollPane = new JScrollPane();
							jNotizSuchePanel.add(jNotizScrollPane);
							jNotizScrollPane.setBounds(21, 147, 609, 175);
							jNotizScrollPane.setWheelScrollingEnabled(true);
							jNotizScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jNotizTableModel = new NotizTableModel ();
								jNotizTable = new JTable();
								jNotizScrollPane
									.setViewportView(jNotizTable);
								jNotizTable.setModel(jNotizTableModel);
								jNotizTable.setBounds(7, 252, 610, 56);
								jNotizTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jNotizTable.setShowGrid(true);
								jNotizTable.setGridColor(Color.LIGHT_GRAY);
								jNotizTable.addMouseListener(this);
								jNotizTableHeader = jNotizTable.getTableHeader();
								jNotizTableHeader.addMouseListener(this);
							}
						}
						
						{
							jNotizLoeschenButton = new JButton();
							jNotizSuchePanel.add(jNotizLoeschenButton);
							jNotizLoeschenButton.setText("Notiz(en) Löschen");
							jNotizLoeschenButton.setBounds(21, 329, 119, 21);
							jNotizLoeschenButton.addActionListener(this);
						}
						{
							jNotizWiederherstellenButton = new JButton();
							jNotizSuchePanel.add(jNotizWiederherstellenButton);
							jNotizWiederherstellenButton.setText("Gelöschte Notiz(en) Wiederherstellen");
							jNotizWiederherstellenButton.setBounds(147, 329, 231, 21);
							jNotizWiederherstellenButton.addActionListener(this);
						}
						{
							jNotizDownloadButton = new JButton();
							jNotizSuchePanel.add(jNotizDownloadButton);
							jNotizDownloadButton.setText("Notiz herunterladen");
							jNotizDownloadButton.setBounds(483, 329, 147, 21);
							jNotizDownloadButton.addActionListener(this);
						}

					}
					
// #####################################################################					
					
// #####################################################################
					
					jPruefungSuchePanel = new JPanel();
					jTabbedSearchPane.addTab("Notiz Suche", null, jNotizSuchePanel, null);
					jTabbedSearchPane.addTab("Pruefung Suche", null, jPruefungSuchePanel, null);
					jPruefungSuchePanel.setPreferredSize(new java.awt.Dimension(671, 357));
					jPruefungSuchePanel.setLayout(null);
					jPruefungSuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jPruefungLVALabel = new JLabel();
						jPruefungSuchePanel.add(jPruefungLVALabel);
						jPruefungLVALabel.setText("LVA:");
						jPruefungLVALabel.setBounds(74, 12, 28, 14);

						jPruefungExaminerLabel = new JLabel();
						jPruefungSuchePanel.add(jPruefungExaminerLabel);
						jPruefungExaminerLabel.setText("Prüfer:");
						jPruefungExaminerLabel.setBounds(61, 35, 35, 14);
						
						jPruefungNoteLabel = new JLabel();
						jPruefungSuchePanel.add(jPruefungNoteLabel);
						jPruefungNoteLabel.setText("Note:");
						jPruefungNoteLabel.setBounds(384, 11, 35, 14);
						
						jPruefungLVAField = new JTextField();
						jPruefungSuchePanel.add(jPruefungLVAField);
						jPruefungLVAField.setBounds(105, 7, 210, 21);
						jPruefungLVAField.addKeyListener(this);


						jPruefungExaminerField = new JTextField();
						jPruefungSuchePanel.add(jPruefungExaminerField);
						jPruefungExaminerField.setBounds(105, 30, 210, 21);
						jPruefungExaminerField.addKeyListener(this);
						
						{
							ComboBoxModel jGradeComboBoxModel = new DefaultComboBoxModel(
								new String[] { "-", "1", "2", "3", "4", "5" });
							jGradeComboBox = new JComboBox();
							jPruefungSuchePanel.add(jGradeComboBox);
							jGradeComboBox.setModel(jGradeComboBoxModel);
							jGradeComboBox.setBounds(420, 7, 42, 21);
						}


						{
							jSeparator7 = new JSeparator();
							jPruefungSuchePanel.add(jSeparator7);
							jSeparator7.setBounds(7, 140, 630, 7);
						}
						{
							jPruefungSuchenButton = new JButton();
							jPruefungSuchePanel.add(jPruefungSuchenButton);
							jPruefungSuchenButton.setText("Prüfung Suchen");
							jPruefungSuchenButton.setBounds(68, 105, 154, 28);
							jPruefungSuchenButton.addActionListener(this);
							
							jPruefungLokalSuchenRadioButton = new JRadioButton();
							jPruefungSuchePanel.add(jPruefungLokalSuchenRadioButton);
							jPruefungLokalSuchenRadioButton.setText("Lokal Suchen");
							jPruefungLokalSuchenRadioButton.setBounds(231, 105, 91, 14);
	
							jPruefungOnlineSuchenRadioButton = new JRadioButton();
							jPruefungSuchePanel.add(jPruefungOnlineSuchenRadioButton);
							jPruefungOnlineSuchenRadioButton.setText("Online Suchen");
							jPruefungOnlineSuchenRadioButton.setBounds(231, 119, 98, 14);
							
							jPruefungLokalSuchenRadioButton.setSelected(true);
							jPruefungSucheGroup = new ButtonGroup();
							jPruefungSucheGroup.add(jPruefungLokalSuchenRadioButton);
							jPruefungLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							jPruefungSucheGroup.add(jPruefungOnlineSuchenRadioButton);				
							jPruefungOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							
							jPruefungGruppeLabel = new JLabel();
							jPruefungSuchePanel.add(jPruefungGruppeLabel);
							jPruefungGruppeLabel.setText("Gruppe:");
							jPruefungGruppeLabel.setBounds(348, 112, 42, 14);
		
							KategorieComboBoxModel jPruefungGruppeComboBoxModel = new KategorieComboBoxModel("ATTACHABLE_OBJECT_KATEGORIE", new AttachableObjectKategorie());
							jPruefungGruppeComboBox = new JComboBox();
							jPruefungSuchePanel.add(jPruefungGruppeComboBox);
							jPruefungGruppeComboBox
								.setModel(jPruefungGruppeComboBoxModel);
							jPruefungGruppeComboBox.setBounds(392, 109, 175, 21);
						}

						{
							jPruefungScrollPane = new JScrollPane();
							jPruefungSuchePanel.add(jPruefungScrollPane);
							jPruefungScrollPane.setBounds(21, 147, 609, 175);
							jPruefungScrollPane.setWheelScrollingEnabled(true);
							jPruefungScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jPruefungTableModel = new PruefungTableModel ();
								jPruefungTable = new JTable();
								jPruefungScrollPane
									.setViewportView(jPruefungTable);
								jPruefungTable.setModel(jPruefungTableModel);
								jPruefungTable.setBounds(7, 252, 610, 56);
								jPruefungTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jPruefungTable.setShowGrid(true);
								jPruefungTable.setGridColor(Color.LIGHT_GRAY);
								jPruefungTable.addMouseListener(this);
								jPruefungTableHeader = jPruefungTable.getTableHeader();
								jPruefungTableHeader.addMouseListener(this);
							}
						}
						{
							jPruefungLoeschenButton = new JButton();
							jPruefungSuchePanel.add(jPruefungLoeschenButton);
							jPruefungLoeschenButton.setText("Prüfung(en) Löschen");
							jPruefungLoeschenButton.setBounds(21, 329, 133, 21);
							jPruefungLoeschenButton.addActionListener(this);
						}
						{
							jPruefungWiederherstellenButton = new JButton();
							jPruefungSuchePanel.add(jPruefungWiederherstellenButton);
							jPruefungWiederherstellenButton.setText("Gelöschte Prüfung(en) Wiederherstellen");
							jPruefungWiederherstellenButton.setBounds(162, 329, 231, 21);
							jPruefungWiederherstellenButton.addActionListener(this);
						}
						{
							jPruefungDownloadButton = new JButton();
							jPruefungSuchePanel.add(jPruefungDownloadButton);
							jPruefungDownloadButton.setText("Prüfung Herunterladen");
							jPruefungDownloadButton.setBounds(483, 329, 147, 21);
							jPruefungDownloadButton.addActionListener(this);
						}

					}
					
					
// #####################################################################					

				}
			}
			{
				lupe = new JLabel();
				getContentPane().add(lupe);
				lupe.setIcon(new ImageIcon(AutopsiConfigurator.images_icons+"/lupe.png"));
				lupe.setBounds(7, 150, 196, 182);
			}
		} catch (Exception e) {
			this.Dialog.showErrorDialog("Error: " +e.toString(), "Error!");
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2 && arg0.getButton()==MouseEvent.BUTTON1 && !(arg0.getSource() instanceof JTableHeader)) {
			JTable Table = (JTable) arg0.getSource();
			TableModel TM = Table.getModel();
			if (arg0.getSource().equals(jKontaktTable)){
				KontaktTableModel otm = (KontaktTableModel) TM;
				GenericDataObject object = otm.getObjectAt(Table.getSelectedRow()); 
				GenericEditFrame gedit = new GenericEditFrame(this);
				gedit.setTableToEdit("KONTAKT");
				gedit.setObjectToEdit(object,false);
				gedit.pack();
				gedit.setVisible(true);
				otm.fireDataChanged();
			}
			if (arg0.getSource().equals(jLVATable)){
				LVATableModel otm = (LVATableModel) TM;
				GenericDataObject object = otm.getObjectAt(Table.getSelectedRow()); 
				GenericEditFrame gedit = new GenericEditFrame(this);
				gedit.setTableToEdit(otm.tablename);
				gedit.setObjectToEdit(object,false);
				gedit.pack();
				gedit.setVisible(true);
				otm.fireDataChanged();
			}
			
			if (arg0.getSource().equals(jLehrmittelTable)){
				LehrmittelTableModel otm = (LehrmittelTableModel) TM;
				GenericDataObject object = otm.getObjectAt(Table.getSelectedRow()); 
				GenericEditFrame gedit = new GenericEditFrame(this);
				gedit.setTableToEdit(otm.tablename);
				gedit.setObjectToEdit(object,false);
				gedit.pack();
				gedit.setVisible(true);
				otm.fireDataChanged();
			}
			if (arg0.getSource().equals(jNotizTable)){
				NotizTableModel otm = (NotizTableModel) TM;
				GenericDataObject object = otm.getObjectAt(Table.getSelectedRow()); 
				GenericEditFrame gedit = new GenericEditFrame(this);
				gedit.setTableToEdit(otm.tablename);
				gedit.setObjectToEdit(object,false);
				gedit.pack();
				gedit.setVisible(true);
				otm.fireDataChanged();
			}
			if (arg0.getSource().equals(jPruefungTable)){
				PruefungTableModel otm = (PruefungTableModel) TM;
				GenericDataObject object = otm.getObjectAt(Table.getSelectedRow()); 
				GenericEditFrame gedit = new GenericEditFrame(this);
				gedit.setTableToEdit(otm.tablename);
				gedit.setObjectToEdit(object,false);
				gedit.pack();
				gedit.setVisible(true);
				otm.fireDataChanged();
			}
			if (arg0.getSource().equals(jTerminContainerTable)){
				TerminContainerTableModel otm = (TerminContainerTableModel) TM;
				GenericDataObject object = otm.getObjectAt(Table.getSelectedRow()); 
//				GenericEditFrame gedit = new GenericEditFrame(this);
//				gedit.setTableToEdit(otm.tablename);
//				gedit.setObjectToEdit(object,false);
//				gedit.pack();
//				gedit.setVisible(true);
//				otm.fireDataChanged();
				
				TerminContainer tc = (TerminContainer) object;
				Integer tcID = tc.id;
				
				EditTerminContainerFrame frame = new EditTerminContainerFrame(this,tcID);
				//frame.setLocation(this.getLocation().x+20,this.getLocation().y+20);
				frame.setTitle("Termincontainer bearbeiten");
				frame.setVisible(true);
			}
			if (arg0.getSource().equals(jTerminTable)){
				TerminTableModel otm = (TerminTableModel) TM;
				GenericDataObject object = otm.getObjectAt(Table.getSelectedRow()); 
//				GenericEditFrame gedit = new GenericEditFrame(this);
//				gedit.setTableToEdit(otm.tablename);
//				gedit.setObjectToEdit(object,false);
//				gedit.pack();
//				gedit.setVisible(true);
//				otm.fireDataChanged();
				Termin t = (Termin) object;
				Integer terminId = t.id;
				
				EditTerminFrame newTermin = new EditTerminFrame(this,null, terminId, false);
				newTermin.setTitle("Termin bearbeiten");
				newTermin.setLocation(this.getLocation().x+30,this.getLocation().y+30);
				newTermin.setVisible(true);
			}
			
		}
		
		if (arg0.getSource() instanceof JTableHeader) {
			JTableHeader tableh = (JTableHeader)arg0.getSource();
			TableColumnModel columnModel = tableh.getColumnModel();
			int viewColumn = columnModel.getColumnIndexAtX(arg0.getX());
			int column = columnModel.getColumn(viewColumn).getModelIndex();
				if (column != -1) {
					if (arg0.getSource().equals(jKontaktTableHeader))
						jKontaktTableModel.setOrder(column);
					if (arg0.getSource().equals(jLVATableHeader))
						jLVATableModel.setOrder(column);
					if (arg0.getSource().equals(jLehrmittelTableHeader))
						jLehrmittelTableModel.setOrder(column);
					if (arg0.getSource().equals(jNotizTableHeader))
						jNotizTableModel.setOrder(column);
					if (arg0.getSource().equals(jPruefungTableHeader))
						jPruefungTableModel.setOrder(column);
					if (arg0.getSource().equals(jTerminContainerTableHeader))
						jTerminContainerTableModel.setOrder(column);
					if (arg0.getSource().equals(jTerminTableHeader))
						jTerminTableModel.setOrder(column);
				}
		}
	}
	
	public void mousePressed(MouseEvent arg0) {
		
	}
	
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	public void mouseEntered(MouseEvent arg0) {
		
	}
	
	public void mouseExited(MouseEvent arg0) {
		
	}
	
	public void mouseDragged(MouseEvent arg0){
		
	}

	public void mouseMoved(MouseEvent arg0) {
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object cmd = ae.getSource();
		starteSuche(cmd);
	}
	
	public void setOrder (JTable table, int order){
		if (table.equals(jKontaktTable)){
			this.jKontaktTableModel.setOrder(order);
		}
	}
	
	public void starteSuche (Object cmd){
		if (cmd.equals(this.jKontaktSuchenButton)) {
			starteKontaktSuche();
		} else if(cmd.equals(this.jLVASuchenButton)) {
			starteLVASuche();
		} else if(cmd.equals(this.jTerminSuchenButton)) {
			starteTerminSuche();			
		}  else if(cmd.equals(this.jTerminContainerSuchenButton)) {
			starteTerminContainerSuche();
		} else if(cmd.equals(this.jLehrmittelSuchenButton)) {
			starteLehrmittelSuche();
		} else if(cmd.equals(this.jNotizSuchenButton)) {
			starteNotizSuche();
		} else if(cmd.equals(this.jPruefungSuchenButton)) {
			startePruefungSuche();
		} else if(cmd.equals(this.jKontaktLoeschenButton)) {
			jKontaktTableModel.deleteSelectedRow(jKontaktTable);
		} else if(cmd.equals(this.jKontaktWiederherstellenButton)) {
			jKontaktTableModel.restoreLastDeletedObjects();
		} else if(cmd.equals(this.jKontaktDownloadButton)) {
			jKontaktTableModel.downloadObject();
		} else if(cmd.equals(this.jLVALoeschenButton)) {
			jLVATableModel.deleteSelectedRow(jLVATable);
		} else if(cmd.equals(this.jLVAWiederherstellenButton)) {
			jLVATableModel.restoreLastDeletedObjects();
		} else if(cmd.equals(this.jLVADownloadButton)) {
			jLVATableModel.downloadObject();
		} else if(cmd.equals(this.jLehrmittelLoeschenButton)) {
			jLehrmittelTableModel.deleteSelectedRow(jLehrmittelTable);
		} else if(cmd.equals(this.jKontaktWiederherstellenButton)) {
			jLehrmittelTableModel.restoreLastDeletedObjects();
		} else if(cmd.equals(this.jKontaktDownloadButton)) {
			jLehrmittelTableModel.downloadObject();
		} else if(cmd.equals(this.jNotizLoeschenButton)) {
			jNotizTableModel.deleteSelectedRow(jNotizTable);
		} else if(cmd.equals(this.jNotizWiederherstellenButton)) {
			jNotizTableModel.restoreLastDeletedObjects();
		} else if(cmd.equals(this.jNotizDownloadButton)) {
			jNotizTableModel.downloadObject();
		} else if(cmd.equals(this.jPruefungLoeschenButton)) {
			jPruefungTableModel.deleteSelectedRow(jPruefungTable);
		} else if(cmd.equals(this.jPruefungWiederherstellenButton)) {
			jPruefungTableModel.restoreLastDeletedObjects();
		} else if(cmd.equals(this.jPruefungDownloadButton)) {
			jPruefungTableModel.downloadObject();
		} else if(cmd.equals(this.jTerminContainerLoeschenButton)) {
			jTerminContainerTableModel.deleteSelectedRow(jTerminContainerTable);
		} else if(cmd.equals(this.jTerminContainerWiederherstellenButton)) {
			jTerminContainerTableModel.restoreLastDeletedObjects();
		} else if(cmd.equals(this.jTerminContainerDownloadButton)) {
			jTerminContainerTableModel.downloadObject();
		} else if(cmd.equals(this.jTerminLoeschenButton)) {
			jTerminTableModel.deleteSelectedRow(jTerminTable);
		} else if(cmd.equals(this.jTerminWiederherstellenButton)) {
			jTerminTableModel.restoreLastDeletedObjects();
		} else if(cmd.equals(this.jTerminDownloadButton)) {
			jTerminTableModel.downloadObject();
		} else {
			this.Dialog.showErrorDialog("Error: " +cmd.toString(), "Command not found!");
		}
	}

	public void keyTyped(KeyEvent arg0) {
		
	}
	
	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() instanceof JTextField || arg0.getSource() instanceof JFormattedTextField) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				Object Field = arg0.getSource();
				if (Field.equals(this.jVornameField) || Field.equals(this.jNachnameField) 
						|| Field.equals(this.jTelefonnummerField) || Field.equals(this.jEmailField) 
						|| Field.equals(this.jAdresseField) || Field.equals(this.jPlzField) 
						|| Field.equals(this.jOrtField) || Field.equals(this.jGeburtsdatumField)) {
					starteKontaktSuche();
				}
				if (Field.equals(this.jLVATitelField) || Field.equals(this.jLVANummerField) 
						|| Field.equals(this.jBeschreibungField)) {
					starteLVASuche();
				}
				if (Field.equals(this.jTerminTitelField) || Field.equals(this.jTerminBeschreibungField) 
						|| Field.equals(this.jDatumField)) {
					starteTerminSuche();
				}
				if (Field.equals(this.jTerminContainerBeschreibungField) || Field.equals(this.jTerminContainerTitelField)) {
					starteTerminContainerSuche();
				}
				if (Field.equals(this.jLehrmittelBeschreibungField) || Field.equals(this.jLehrmittelNameField)) {
					starteLehrmittelSuche();
				}
				if (Field.equals(this.jNotizNoteField) || Field.equals(this.jNotizTitelField)) {
					starteNotizSuche();
				}
				if (Field.equals(this.jPruefungExaminerField) || Field.equals(this.jPruefungLVAField)) {
					startePruefungSuche();
				}
			}
		}
		 
		
	}

	private void startePruefungSuche() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Pruefung p = new Pruefung();
		if (!jPruefungLVAField.getText().equals("")) {
			jPruefungTableModel.setLvaName(jPruefungLVAField.getText());
		} else {
			jPruefungTableModel.setLvaName(null);
		}
		if (!jPruefungExaminerField.getText().equals("")) {
			p.setExaminer(jPruefungExaminerField.getText());
		} else {
			p.setExaminer(null);
		}
		if (!jGradeComboBox.getSelectedItem().toString().equals("-")) {
			p.setGrade(new Integer(jGradeComboBox.getSelectedItem().toString()));
		} else {
			p.setGrade(null);
		}
		jPruefungTableModel.setGroup(jPruefungGruppeComboBox.getSelectedItem().toString());
		jPruefungTableModel.setSearchObject(p);
		if (jPruefungLokalSuchenRadioButton.isSelected()){
			jPruefungTableModel.fireDataChanged();
		} else if (jPruefungOnlineSuchenRadioButton.isSelected()) {
			jPruefungTableModel.fireOnlineDataChanged();
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void starteNotizSuche() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Notiz not = new Notiz();
		if (!jNotizTitelField.getText().equals("")) {
			not.setTitle(jNotizTitelField.getText());
		} else {
			not.setTitle(null);
		}
		if (!jNotizNoteField.getText().equals("")) {
			not.setNote(jNotizNoteField.getText());
		} else {
			not.setNote(null);
		}
		jNotizTableModel.setGroup(jNotizGruppeComboBox.getSelectedItem().toString());
		jNotizTableModel.setSearchObject(not);
		if (jNotizLokalSuchenRadioButton.isSelected()){
			this.jNotizTableModel.fireDataChanged();
		} else if (jNotizOnlineSuchenRadioButton.isSelected()) {
			this.jNotizTableModel.fireOnlineDataChanged();
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void starteLehrmittelSuche() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Lehrmittel lm = new Lehrmittel();
		if (!jLehrmittelNameField.getText().equals("")) {
			lm.setName(jLehrmittelNameField.getText());
		} else {
			lm.setName(null);
		}
		if (!jLehrmittelBeschreibungField.getText().equals("")) {
			lm.setDescription(jLehrmittelBeschreibungField.getText());
		} else {
			lm.setDescription(null);
		}
		jLehrmittelTableModel.setType(jLehrmittelTypeComboBox.getSelectedItem().toString());
		jLehrmittelTableModel.setGroup(jLehrmittelGruppeComboBox.getSelectedItem().toString());
		jLehrmittelTableModel.setSearchObject(lm);
		
		if (jLehrmittelLokalSuchenRadioButton.isSelected()){
			jLehrmittelTableModel.fireDataChanged();
		} else if (jLehrmittelOnlineSuchenRadioButton.isSelected()) {
			this.jLehrmittelTableModel.fireOnlineDataChanged();
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void starteTerminContainerSuche() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		TerminContainer terc = new TerminContainer();
		if (!jTerminContainerTitelField.getText().equals("")) {
			terc.setTitle(jTerminContainerTitelField.getText());
		} else {
			terc.setTitle(null);
		}
		if (!jTerminContainerBeschreibungField.getText().equals("")){
			terc.setDescription(jTerminContainerBeschreibungField.getText());
		} else {
			terc.setDescription(null);
		}
		jTerminContainerTableModel.setGroup(jTerminContainerGruppeComboBox.getSelectedItem().toString());
		jTerminContainerTableModel.setSearchObject(terc);
		
		if (jTerminContainerLokalSuchenRadioButton.isSelected()){
			jTerminContainerTableModel.fireDataChanged();
		} else if (jTerminContainerOnlineSuchenRadioButton.isSelected()) {
			this.jTerminContainerTableModel.fireOnlineDataChanged();
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void starteLVASuche() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Lva lva = new Lva();
		if (!jLVANummerField.getText().equals("")){
			lva.setLvaNr(jLVANummerField.getText());
		} else {
			lva.setLvaNr(null);
		}
		if (!jLVATitelField.getText().equals("")){
			lva.setTitle(jLVATitelField.getText());
		} else {
			lva.setTitle(null);
		}
		if (!jBeschreibungField.getText().equals("")){
			lva.setDescription(jBeschreibungField.getText());
		} else {
			lva.setDescription(null);
		}
		jLVATableModel.setType(jLVATypeComboBox.getSelectedItem().toString());
		jLVATableModel.setGroup(jLVATypeComboBox.getSelectedItem().toString());
		jLVATableModel.setSearchObject(lva);
		
		if (jLVALokalSuchenRadioButton.isSelected()){
			this.jLVATableModel.fireDataChanged();
		} else if (jLVAOnlineSuchenRadioButton.isSelected()) {
			this.jLVATableModel.fireOnlineDataChanged();
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void starteKontaktSuche() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			Kontakt kont = new Kontakt();
			if (!jVornameField.getText().equals("")){
				kont.setPrename(jVornameField.getText());
			} else {
				kont.setPrename(null);
			} 
			if (!jNachnameField.getText().equals("")) {
				kont.setSurname(jNachnameField.getText());
			} else {
				kont.setSurname(null);
			}
			
			//JOptionPane.showMessageDialog(null, "laenge: " +jGeburtsdatumField.getText().length(), "Error!" , JOptionPane.ERROR_MESSAGE);
			if (jGeburtsdatumField.getText().length()==10){
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				Date geburtsdatum = sf.parse(jGeburtsdatumField.getText());
				java.sql.Date sqlgeburtsdatum = new java.sql.Date( geburtsdatum.getTime());
				kont.setBirthDate(sqlgeburtsdatum);
			} else {
				kont.setBirthDate(null);
			}
			if (!jTelefonnummerField.getText().equals("")){
				kont.setTelBusiness(jTelefonnummerField.getText());
			} else {
				kont.setTelBusiness(null);
			}
			if (!jEmailField.getText().equals("")) {
				kont.setFirstEmail(jEmailField.getText());
			} else {
				kont.setFirstEmail(null);
			}
			if (!jPlzField.getText().equals("")){
				Integer plz = new Integer(jPlzField.getText());
				kont.setAZipCode(plz);
			} else {
				kont.setAZipCode(null);
			}
			if (!jAdresseField.getText().equals("")){
				kont.setAAdress(jAdresseField.getText());
			} else {
				kont.setAAdress(null);
			}
			if (!jOrtField.getText().equals("")) {
				kont.setACity(jOrtField.getText());
			} else {
				kont.setACity(null);
			}
			jKontaktTableModel.setGroup(jKontaktGruppeComboBox.getSelectedItem().toString());
			jKontaktTableModel.setSearchObject(kont);
			
			if (jKontaktLokalSuchenRadioButton.isSelected()){
				jKontaktTableModel.fireDataChanged();
				
			} else if (jKontaktOnlineSuchenRadioButton.isSelected()) {
				jKontaktTableModel.fireOnlineDataChanged();
			}
		} catch (ParseException ps) {
			this.Dialog.showErrorDialog("Parse-Error: " +ps.getMessage(), "Error!");
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void starteTerminSuche(){
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Termin ter = new Termin();
		if (!jTerminTitelField.getText().equals("")) {
			ter.setSecondaryTitle(jTerminTitelField.getText());
		} else {
			ter.setSecondaryTitle(null);
		}
		if (!jTerminBeschreibungField.getText().equals("")){
			ter.setDescription(jTerminBeschreibungField.getText());
		} else {
			ter.setDescription(null);
		}
		if (!jDatumField.getText().equals("")){
			jTerminTableModel.setDatum(jDatumField.getText());
		} else {
			jTerminTableModel.setDatum(null);
		}
		jTerminTableModel.setType(jTerminTypeComboBox.getSelectedItem().toString());
		jTerminTableModel.setGroup(jTerminGruppeComboBox.getSelectedItem().toString());
		jTerminTableModel.setSearchObject(ter);
		
		if (jTerminLokalSuchenRadioButton.isSelected()){
			this.jTerminTableModel.fireDataChanged();
		} else if (jTerminOnlineSuchenRadioButton.isSelected()) {
			this.jTerminTableModel.fireOnlineDataChanged();
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
