package gui;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
public class SearchFrame extends javax.swing.JPanel {

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
	
	private JCheckBox jKontaktSpaceSucheCheckBox;
	private JCheckBox jKontaktLokalSucheCheckBox;
	private JCheckBox jLVASpaceSucheCheckBox, jLVALokalSucheCheckBox;
	private JCheckBox jTerminSpaceSucheCheckBox, jTerminLokalSucheCheckBox;
	private JCheckBox jTerminCheckBox, jTerminContainerCheckBox;
	
	private JTable jKontaktTable, jLVATable, jTerminTable;
	
	private JButton jKontaktSuchenButton;
	private JButton jLVASuchenButton;
	private JButton jTerminSuchenButton;
	
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
						}
						{
							jKontaktLokalSucheCheckBox = new JCheckBox();
							jkontaktSuchePanel.add(jKontaktLokalSucheCheckBox);
							jKontaktLokalSucheCheckBox.setText("Lokal Suchen");
							jKontaktLokalSucheCheckBox.setBounds(350, 105, 98, 14);
						}
						{
							jKontaktSpaceSucheCheckBox = new JCheckBox();
							jkontaktSuchePanel.add(jKontaktSpaceSucheCheckBox);
							jKontaktSpaceSucheCheckBox.setText("Online Suchen");
							jKontaktSpaceSucheCheckBox.setBounds(350, 119, 98, 14);
						}

						{
							jKontaktScrollPane = new JScrollPane();
							jkontaktSuchePanel.add(jKontaktScrollPane);
							jKontaktScrollPane.setBounds(18, 147, 658, 203);
							jKontaktScrollPane.setWheelScrollingEnabled(true);
							{
								TableModel jKontaktTableModel = new DefaultTableModel(
									new String[][] { { "One", "Two" },
											{ "Three", "Four" } },
									new String[] { "Column 1", "Column 2" });
								jKontaktTable = new JTable();
								jKontaktScrollPane
									.setViewportView(jKontaktTable);
								jKontaktTable.setModel(jKontaktTableModel);
								jKontaktTable.setBounds(7, 252, 644, 56);
								jKontaktTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								jKontaktTable.setShowGrid(true);
								jKontaktTable.setGridColor(Color.LIGHT_GRAY);
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
						
						ComboBoxModel jLVATypeComboBoxModel = new DefaultComboBoxModel(
							new String[] { "VO", "LU" });
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
						}
						{
							jLVALokalSucheCheckBox = new JCheckBox();
							jLVASuchePanel.add(jLVALokalSucheCheckBox);
							jLVALokalSucheCheckBox.setText("Lokal Suchen");
							jLVALokalSucheCheckBox.setBounds(350, 105, 98, 14);
						}
						{
							jLVASpaceSucheCheckBox = new JCheckBox();
							jLVASuchePanel.add(jLVASpaceSucheCheckBox);
							jLVASpaceSucheCheckBox.setText("Online Suchen");
							jLVASpaceSucheCheckBox.setBounds(350, 119, 98, 14);
						}

						{
							jLVAScrollPane = new JScrollPane();
							jLVASuchePanel.add(jLVAScrollPane);
							jLVAScrollPane.setBounds(18, 147, 658, 203);
							jLVAScrollPane.setWheelScrollingEnabled(true);
							{
								TableModel jLVATableModel = new DefaultTableModel(
									new String[][] { { "One", "Two" },
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
						
						ComboBoxModel jTerminTypeComboBoxModel = new DefaultComboBoxModel(
							new String[] { "LVA", "Pr�fung" });
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

							jTerminCheckBox = new JCheckBox();
							jTerminSuchePanel.add(jTerminCheckBox);
							jTerminCheckBox.setText("Termin Suchen");
							jTerminCheckBox.setBounds(183, 77, 98, 14);
	
							jTerminLokalSucheCheckBox = new JCheckBox();
							jTerminSuchePanel.add(jTerminLokalSucheCheckBox);
							jTerminLokalSucheCheckBox.setText("Lokal Suchen");
							jTerminLokalSucheCheckBox.setBounds(350, 105, 98, 14);

							jTerminSpaceSucheCheckBox = new JCheckBox();
							jTerminSuchePanel.add(jTerminSpaceSucheCheckBox);
							jTerminSpaceSucheCheckBox.setText("Online Suchen");
							jTerminSpaceSucheCheckBox.setBounds(350, 119, 98, 14);

							jTerminContainerCheckBox = new JCheckBox();
							jTerminSuchePanel.add(jTerminContainerCheckBox);
							jTerminContainerCheckBox.setText("Termincontainer Suchen");
							jTerminContainerCheckBox.setBounds(306, 77, 140, 14);

						{
							jTerminScrollPane = new JScrollPane();
							jTerminSuchePanel.add(jTerminScrollPane);
							jTerminScrollPane.setBounds(18, 147, 658, 203);
							jTerminScrollPane.setWheelScrollingEnabled(true);
							{
								TableModel jTerminTableModel = new DefaultTableModel(
									new String[][] { { "One", "Two" },
											{ "Three", "Four" } },
									new String[] { "Column 1", "Column 2" });
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

}
