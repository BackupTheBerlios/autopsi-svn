package autopsi.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import autopsi.database.table.Termin;
import autopsi.database.table.Kontakt;
import autopsi.database.table.TerminKategorie;
import autopsi.database.table.LvaKategorie;
import autopsi.gui.components.KategorieComboBoxModel;

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
public class SearchFrame extends javax.swing.JPanel implements ActionListener {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static final long serialVersionUID = 18310810238219L;

	private JTabbedPane jTabbedSearchPane;
	
	private JPanel jkontaktSuchePanel;
	private JPanel jLVASuchePanel;
	private JPanel jTerminSuchePanel;
	
	private JScrollPane jKontaktScrollPane;
	private JScrollPane jLVAScrollPane;
	private JScrollPane jTerminScrollPane;
		
	private JTextField jVornameField, jNachnameField, jGeburtsdatumField;
	private JTextField jTelefonnummerField, jEmailField;
	private JTextField jAdresseField, jPlzField, jOrtField;
	private JTextField jTitelField, jLVANummerField, jBeschreibungField;
	private JTextField jTerminTitelField, jTerminBeschreibungField, jDatumField;

	private JLabel jVornameLabel;
	private JLabel jNachnameLabel;
	private JLabel jGeburtsdatumLabel;
	private JLabel jTelefonnummerLabel;
	private JLabel jEmailLabel;
	private JLabel jAdresseLabel;
	private JLabel jOrtLabel;
	private JLabel jPlzLabel;
	private JLabel jTitelLabel;
	private JLabel jKategorieLabel;
	private JLabel jNummerLabel;
	private JLabel jBeschreibungLabel;
	private JLabel jTypeLabel;
	private JLabel jDatumLabel;

	private JCheckBox jTerminCheckBox, jTerminContainerCheckBox;
	
	private JTable jKontaktTable, jLVATable, jTerminTable;
	
	private TerminTableModel jTerminTableModel;
	private KontaktTableModel jKontaktTableModel;
	
	private JButton jKontaktSuchenButton, jTerminSuchenButton, jLVASuchenButton;
	private ButtonGroup jKontaktSucheGroup,jLVASucheGroup, jTerminSucheGroup;
	private JRadioButton jKontaktOnlineSuchenRadioButton, jKontaktLokalSuchenRadioButton;
	private JRadioButton jLVAOnlineSuchenRadioButton, jLVALokalSuchenRadioButton;
	private JRadioButton jTerminOnlineSuchenRadioButton, jTerminLokalSuchenRadioButton;
	
	
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JSeparator jSeparator3;
	
	
	private JComboBox jLVAKatComboBox;
	private JComboBox jLVATypeComboBox;
	private JComboBox jTerminTypeComboBox;
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public SearchFrame() {
		super();
		initGUI();
		
		JFrame frame = new JFrame("Suchen");
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(812, 406));
			this.setLayout(null);
			{
				jTabbedSearchPane = new JTabbedPane();
				this.add(jTabbedSearchPane);
				jTabbedSearchPane.setBounds(7, 7, 798, 392);
				jTabbedSearchPane.setTabPlacement(JTabbedPane.LEFT);
				jTabbedSearchPane.setBorder(BorderFactory.createTitledBorder("Suche"));
				{
					jkontaktSuchePanel = new JPanel();
					jTabbedSearchPane.addTab("Kontakt Suche", null, jkontaktSuchePanel, null);
					jkontaktSuchePanel.setPreferredSize(new java.awt.Dimension(671, 357));
					jkontaktSuchePanel.setLayout(null);
					jkontaktSuchePanel.setBackground(new java.awt.Color(255,255,255));
					{
						jVornameLabel = new JLabel();
						jkontaktSuchePanel.add(jVornameLabel);
						jVornameLabel.setText("Vorname:");
						jVornameLabel.setBounds(52, 12, 49, 14);

						jNachnameLabel = new JLabel();
						jkontaktSuchePanel.add(jNachnameLabel);
						jNachnameLabel.setText("Nachname:");
						jNachnameLabel.setBounds(44, 34, 56, 14);

						jGeburtsdatumLabel = new JLabel();
						jkontaktSuchePanel.add(jGeburtsdatumLabel);
						jGeburtsdatumLabel.setText("Geburtsdatum:");
						jGeburtsdatumLabel.setBounds(26, 56, 77, 14);

						jTelefonnummerLabel = new JLabel();
						jkontaktSuchePanel.add(jTelefonnummerLabel);
						jTelefonnummerLabel.setText("Telefonnummer:");
						jTelefonnummerLabel.setBounds(20, 81, 84, 14);

						jAdresseLabel = new JLabel();
						jkontaktSuchePanel.add(jAdresseLabel);
						jAdresseLabel.setText("Adresse:");
						jAdresseLabel.setBounds(372, 34, 49, 14);

						jPlzLabel = new JLabel();
						jkontaktSuchePanel.add(jPlzLabel);
						jPlzLabel.setText("Postleitzahl:");
						jPlzLabel.setBounds(357, 56, 63, 14);

						jOrtLabel = new JLabel();
						jkontaktSuchePanel.add(jOrtLabel);
						jOrtLabel.setText("Ort:");
						jOrtLabel.setBounds(395, 80, 21, 14);

						jVornameField = new JTextField();
						jkontaktSuchePanel.add(jVornameField);
						jVornameField.setBounds(105, 7, 210, 21);

						jNachnameField = new JTextField();
						jkontaktSuchePanel.add(jNachnameField);
						jNachnameField.setBounds(105, 30, 210, 21);

						jGeburtsdatumField = new JTextField();
						jkontaktSuchePanel.add(jGeburtsdatumField);
						jGeburtsdatumField.setBounds(105, 53, 210, 21);

						jTelefonnummerField = new JTextField();
						jkontaktSuchePanel.add(jTelefonnummerField);
						jTelefonnummerField.setBounds(105, 76, 210, 21);
						
						jEmailField = new JTextField();
						jkontaktSuchePanel.add(jEmailField);
						jEmailField.setBounds(420, 7, 210, 21);

						jAdresseField = new JTextField();
						jkontaktSuchePanel.add(jAdresseField);
						jAdresseField.setBounds(420, 30, 210, 21);

						jPlzField = new JTextField();
						jkontaktSuchePanel.add(jPlzField);
						jPlzField.setBounds(420, 53, 210, 21);

						jOrtField = new JTextField();
						jkontaktSuchePanel.add(jOrtField);
						jOrtField.setBounds(420, 76, 210, 21);

						{
							jSeparator1 = new JSeparator();
							jkontaktSuchePanel.add(jSeparator1);
							jSeparator1.setBounds(7, 140, 679, 7);
						}
						{
							jKontaktSuchenButton = new JButton();
							jkontaktSuchePanel.add(jKontaktSuchenButton);
							jKontaktSuchenButton.setText("Kontakt Suchen");
							jKontaktSuchenButton.setBounds(182, 105, 154, 28);
							jKontaktSuchenButton.addActionListener(this);
						}
						{
							jKontaktLokalSuchenRadioButton = new JRadioButton();
							jkontaktSuchePanel.add(jKontaktLokalSuchenRadioButton);
							jKontaktLokalSuchenRadioButton.setText("Lokal Suchen");
							jKontaktLokalSuchenRadioButton.setBounds(343, 103, 91, 14);
	
							jKontaktOnlineSuchenRadioButton = new JRadioButton();
							jkontaktSuchePanel.add(jKontaktOnlineSuchenRadioButton);
							jKontaktOnlineSuchenRadioButton.setText("Online Suchen");
							jKontaktOnlineSuchenRadioButton.setBounds(343, 119, 105, 14);
							
							jKontaktLokalSuchenRadioButton.setSelected(true);
							jKontaktSucheGroup = new ButtonGroup();
							jKontaktSucheGroup.add(jKontaktLokalSuchenRadioButton);
							jKontaktLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							jKontaktSucheGroup.add(jKontaktOnlineSuchenRadioButton);				
							jKontaktOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
						}

						{
							jKontaktScrollPane = new JScrollPane();
							jkontaktSuchePanel.add(jKontaktScrollPane);
							jKontaktScrollPane.setBounds(18, 147, 658, 203);
							jKontaktScrollPane.setWheelScrollingEnabled(true);
							jKontaktScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jKontaktTableModel = new KontaktTableModel();
								jKontaktTable = new JTable();
								jKontaktScrollPane
									.setViewportView(jKontaktTable);
								jKontaktTable.setModel(jKontaktTableModel);
								jKontaktTable.setBounds(7, 252, 644, 56);
								jKontaktTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jKontaktTable.setShowGrid(true);
								jKontaktTable.setGridColor(Color.LIGHT_GRAY);
								jKontaktTable.setBackground(new java.awt.Color(255,255,255));
							}
						}
						{
							jEmailLabel = new JLabel();
							jkontaktSuchePanel.add(jEmailLabel);
							jEmailLabel.setText("Email:");
							jEmailLabel.setBounds(386, 12, 35, 14);
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
						
						jKategorieLabel = new JLabel();
						jLVASuchePanel.add(jKategorieLabel);
						jKategorieLabel.setText("Kategorie:");
						jKategorieLabel.setBounds(364, 13, 56, 14);

						jTypeLabel = new JLabel();
						jLVASuchePanel.add(jTypeLabel);
						jTypeLabel.setText("Type:");
						jTypeLabel.setBounds(385, 35, 28, 14);

	
						
						jTitelField = new JTextField();
						jLVASuchePanel.add(jTitelField);
						jTitelField.setBounds(105, 7, 210, 21);


						jLVANummerField = new JTextField();
						jLVASuchePanel.add(jLVANummerField);
						jLVANummerField.setBounds(105, 30, 210, 21);

						jBeschreibungField = new JTextField();
						jLVASuchePanel.add(jBeschreibungField);
						jBeschreibungField.setBounds(105, 53, 210, 21);		
						
						ComboBoxModel jLVAKatComboBoxModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						jLVAKatComboBox = new JComboBox();
						jLVASuchePanel.add(jLVAKatComboBox);
						jLVAKatComboBox.setModel(jLVAKatComboBoxModel);
						jLVAKatComboBox.setBounds(420, 7, 210, 21);
						
						KategorieComboBoxModel jLVATypeComboBoxModel = new KategorieComboBoxModel("LVA_KATEGORIE", new LvaKategorie(),true );
						jLVATypeComboBox = new JComboBox();
						jLVASuchePanel.add(jLVATypeComboBox);
						jLVATypeComboBox.setModel(jLVATypeComboBoxModel);
						jLVATypeComboBox.setBounds(420, 30, 210, 21);

						{
							jSeparator2 = new JSeparator();
							jLVASuchePanel.add(jSeparator2);
							jSeparator2.setBounds(7, 140, 679, 7);
						}
						{
							jLVASuchenButton = new JButton();
							jLVASuchePanel.add(jLVASuchenButton);
							jLVASuchenButton.setText("LVA Suchen");
							jLVASuchenButton.setBounds(182, 105, 154, 28);
							jLVASuchenButton.addActionListener(this);
						}
						{
							jLVALokalSuchenRadioButton = new JRadioButton();
							jLVASuchePanel.add(jLVALokalSuchenRadioButton);
							jLVALokalSuchenRadioButton.setText("Lokal Suchen");
							jLVALokalSuchenRadioButton.setBounds(343, 103, 91, 14);
	
							jLVAOnlineSuchenRadioButton = new JRadioButton();
							jLVASuchePanel.add(jLVAOnlineSuchenRadioButton);
							jLVAOnlineSuchenRadioButton.setText("Online Suchen");
							jLVAOnlineSuchenRadioButton.setBounds(343, 119, 105, 14);
							
							jLVALokalSuchenRadioButton.setSelected(true);
							jLVASucheGroup = new ButtonGroup();
							jLVASucheGroup.add(jLVALokalSuchenRadioButton);
							jLVALokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							jLVASucheGroup.add(jLVAOnlineSuchenRadioButton);				
							jLVAOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
						}

						{
							jLVAScrollPane = new JScrollPane();
							jLVASuchePanel.add(jLVAScrollPane);
							jLVAScrollPane.setBounds(18, 147, 658, 203);
							jLVAScrollPane.setWheelScrollingEnabled(true);
							jLVAScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								TableModel jLVATableModel = new DefaultTableModel(
									new String[][] { 
											{ "One", "Two" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" },
											{ "Three", "Four" } },
									new String[] { "Column 1", "Column 2" });
								jLVATable = new JTable();
								jLVAScrollPane
									.setViewportView(jLVATable);
								jLVATable.setModel(jLVATableModel);
								jLVATable.setBounds(7, 252, 644, 56);
								jLVATable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jLVATable.setShowGrid(true);
								jLVATable.setGridColor(Color.LIGHT_GRAY);
							}
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


						jTerminBeschreibungField = new JTextField();
						jTerminSuchePanel.add(jTerminBeschreibungField);
						jTerminBeschreibungField.setBounds(105, 30, 210, 21);

												
						jDatumField = new JTextField();
						jTerminSuchePanel.add(jDatumField);
						jDatumField.setBounds(420, 7, 210, 21);
						
						KategorieComboBoxModel jTerminTypeComboBoxModel = new KategorieComboBoxModel("TERMIN_KATEGORIE", new TerminKategorie(),true );
						jTerminTypeComboBox = new JComboBox();
						jTerminSuchePanel.add(jTerminTypeComboBox);
						jTerminTypeComboBox.setModel(jTerminTypeComboBoxModel);
						jTerminTypeComboBox.setBounds(420, 30, 210, 21);
						
						

						{
							jSeparator3 = new JSeparator();
							jTerminSuchePanel.add(jSeparator3);
							jSeparator3.setBounds(7, 140, 679, 7);
						}
						
						
							jTerminSuchenButton = new JButton();
							jTerminSuchePanel.add(jTerminSuchenButton);
							jTerminSuchenButton.setText("Termin Suchen");
							jTerminSuchenButton.setBounds(182, 105, 154, 28);
							jTerminSuchenButton.addActionListener(this);

							jTerminCheckBox = new JCheckBox();
							jTerminSuchePanel.add(jTerminCheckBox);
							jTerminCheckBox.setText("Termin Suchen");
							jTerminCheckBox.setBounds(183, 77, 98, 14);
							jTerminCheckBox.setBackground(new java.awt.Color(255,255,255));

							jTerminContainerCheckBox = new JCheckBox();
							jTerminSuchePanel.add(jTerminContainerCheckBox);
							jTerminContainerCheckBox.setText("Termincontainer Suchen");
							jTerminContainerCheckBox.setBounds(306, 77, 140, 14);
							jTerminContainerCheckBox.setBackground(new java.awt.Color(255,255,255));

							{
								jTerminLokalSuchenRadioButton = new JRadioButton();
								jTerminSuchePanel.add(jTerminLokalSuchenRadioButton);
								jTerminLokalSuchenRadioButton.setText("Lokal Suchen");
								jTerminLokalSuchenRadioButton.setBounds(343, 103, 91, 14);
		
								jTerminOnlineSuchenRadioButton = new JRadioButton();
								jTerminSuchePanel.add(jTerminOnlineSuchenRadioButton);
								jTerminOnlineSuchenRadioButton.setText("Online Suchen");
								jTerminOnlineSuchenRadioButton.setBounds(343, 119, 105, 14);
								
								jTerminLokalSuchenRadioButton.setSelected(true);
								jTerminSucheGroup = new ButtonGroup();
								jTerminSucheGroup.add(jTerminLokalSuchenRadioButton);
								jTerminLokalSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
								jTerminSucheGroup.add(jTerminOnlineSuchenRadioButton);				
								jTerminOnlineSuchenRadioButton.setBackground(new java.awt.Color(255,255,255));
							}
							

						{
							jTerminScrollPane = new JScrollPane();
							jTerminSuchePanel.add(jTerminScrollPane);
							jTerminScrollPane.setBounds(18, 147, 658, 203);
							jTerminScrollPane.setWheelScrollingEnabled(true);
							jTerminScrollPane.setBackground(new java.awt.Color(255,255,255));
							{
								jTerminTableModel = new TerminTableModel();
								jTerminTable = new JTable();
								jTerminScrollPane.setViewportView(jTerminTable);
								jTerminTable.setModel(jTerminTableModel);
								jTerminTable.setBounds(7, 252, 644, 56);
								jTerminTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jTerminTable.setShowGrid(true);
								jTerminTable.setGridColor(Color.LIGHT_GRAY);
							}
						}

					}
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand();
		//System.out.println( "e.getActionCommand() = " + cmd );
		if (cmd.equals("Kontakt Suchen")) {
			if (jKontaktLokalSuchenRadioButton.isSelected()){
				System.out.println("Kontakt wird lokal gesucht...");
				Kontakt kont = new Kontakt();
				if (!jVornameField.getText().equals("")){
					kont.setPrename(jVornameField.getText());
				} else {
					kont.setPrename(null);
				}
				jKontaktTableModel.setSuchKontakt(kont);
				jKontaktTableModel.fireDataChanged();
				
			} else if (jKontaktOnlineSuchenRadioButton.isSelected()) {
				System.out.println("Kontakt wird online gesucht...");
			}
		} else if(cmd.equals("LVA Suchen")) {
			if (jLVALokalSuchenRadioButton.isSelected()){
				System.out.println("LVA wird lokal gesucht...");
			} else if (jLVAOnlineSuchenRadioButton.isSelected()) {
				System.out.println("LVA wird online gesucht...");
			}
		} else if(cmd.equals("Termin Suchen")) {
			if (jTerminLokalSuchenRadioButton.isSelected()){
				System.out.println("Termin wird lokal gesucht...");
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
				jTerminTableModel.setSuchTermin(ter);
				jTerminTableModel.fireDataChanged();
				
				
			} else if (jTerminOnlineSuchenRadioButton.isSelected()) {
				System.out.println("Termint wird online gesucht...");
			}
		}
	}
}
