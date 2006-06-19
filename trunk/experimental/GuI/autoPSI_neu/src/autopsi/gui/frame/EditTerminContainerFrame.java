package autopsi.gui.frame;


import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.GenericDataObject;
import autopsi.database.dao.IGenericDAO;
import autopsi.database.exception.EAttributeNotFound;
import autopsi.database.exception.EDatabase;
import autopsi.database.exception.EDatabaseConnection;
import autopsi.database.table.Termin;
import autopsi.database.table.TerminContainer;

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
public class EditTerminContainerFrame extends javax.swing.JFrame implements java.awt.event.MouseListener{

	/**	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTabbedPane jTabbedPane1;
	private JButton apply_button;
	private JLabel jLabel4;
	private JList jList2;
	private JScrollPane jScrollPane1;
	private JButton newTClist;
	private JLabel jLabel7;
	private JLabel jLabel3;

	private JButton jButton10;
	private JButton jButton9;
	private JButton jButton8;
	private JButton jButton7;
	private JTextField jTextField5;
	private JLabel jLabel6;
	private JTextField jTextField4;
	private JLabel jLabel5;
	private JTextField jTextField3;

	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JTextField jTextField2;
	private JList jList1;
	private JPanel jPanel1;
	private JTextArea desc_area;
	private JLabel jLabel2;
	private JTextField title_field;
	private JLabel jLabel1;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JButton ok_button;
	private JButton abort_button;
	private String title = "";
	private String desc = "";
	private IGenericDAO gdo; 
	private int ID;
	private EditTerminFrame owner;
	
	
	
	private void readData(int id) throws EDatabaseConnection, EAttributeNotFound, EDatabase{
		
		TerminContainer lookup = new TerminContainer();
		lookup.setId(id);
		List<GenericDataObject> list = null;
		list = gdo.getDataObjects(lookup);
		title = ((TerminContainer)list.get(0)).getTitle();
		title_field.setText(title);
		desc = ((TerminContainer)list.get(0)).getDescription();
		desc_area.setText(desc);
	
		
		
		
	}
	private void update(){
		try{

			String query="";
			if (ID<0) query = "insert into termincontainer (title, description) values ('"+title_field.getText()+ "','"+desc_area.getText()+"')";
			else query = "update termincontainer  set title = " + title_field.getText() + ", description='"+desc_area.getText()+" where id="+ID+")";
			Termin vorlage = new Termin();
			System.out.println(query);
			gdo.unsafeQuery(query,vorlage);
			//ok = true;
			
			TerminContainer lookup = new TerminContainer(), updateData = new TerminContainer();
			lookup.setId(ID);
			updateData.setId(ID);
			updateData.setTitle(title);
			updateData.setDescription(desc);
			gdo.updDataObjects(lookup, updateData);
			if(owner!=null) owner.updateTCList();
			
		}
		catch (Exception e){
			System.out.println("Exception beim Updaten=="+e.toString());
		}
	}
	public EditTerminContainerFrame(EditTerminFrame owner, int id) {
		super();
		this.ID = id;
		this.owner = owner;
		gdo = new GenericDAO();
		gdo.setCurrentTable("termincontainer");
		initGUI();
		
		
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
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Termincontainer bearbeiten");
			getContentPane().setLayout(null);
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(7, 14, 427, 294);
				jTabbedPane1.setFocusable(false);
				{
					jPanel1 = new JPanel();
					jTabbedPane1.addTab("allgemeine Informationen", null, jPanel1, null);
					jPanel1.setLayout(null);
					jPanel1.setBackground(new java.awt.Color(255, 255, 255));
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("Titel:");
						jLabel1.setBounds(7, 10, 35, 28);
					}
					{
						
						title_field = new JTextField();
						jPanel1.add(title_field);
						title_field.setBounds(91, 14, 322, 21);
						title_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Beschreibung");
						jLabel2.setBounds(5, 39, 84, 28);
					}
					{
						desc_area = new JTextArea();
						jPanel1.add(desc_area);
						desc_area.setBounds(91, 42, 322, 217);
						desc_area.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						
					}
				}
				{
					jPanel3 = new JPanel();
					jTabbedPane1.addTab(
						"verknüpfte Objekte",
						null,
						jPanel3,
						null);
					jPanel3.setBackground(new java.awt.Color(233,233,233));
					jPanel3.setLayout(null);
					{
						ListModel jList1Model = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						jList1 = new JList();
						jPanel3.add(jList1);
						jList1.setModel(jList1Model);
						jList1.setBounds(21, 35, 392, 189);
						jList1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jList1.setVisibleRowCount(1);
					}
					{
						jTextField2 = new JTextField();
						jPanel3.add(jTextField2);
						jTextField2.setBounds(70, 7, 343, 21);
						jTextField2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jButton4 = new JButton();
						jPanel3.add(jButton4);
						jButton4.setText("Hinzufügen");
						jButton4.setBounds(231, 238, 98, 21);
					}
					{
						jButton5 = new JButton();
						jPanel3.add(jButton5);
						jButton5.setText("Löschen");
						jButton5.setBounds(336, 238, 77, 21);
					}
					{
						jButton6 = new JButton();
						jPanel3.add(jButton6);
						jButton6.setText("Öffnen");
						jButton6.setBounds(21, 238, 70, 21);
					}
					{
						jLabel4 = new JLabel();
						jPanel3.add(jLabel4);
						jLabel4.setText("Suchen:");
						jLabel4.setBounds(14, 5, 49, 28);
					}

				}
				{
					jPanel2 = new JPanel();
					jTabbedPane1.addTab("Termine", null, jPanel2, null);
					jPanel2.setBackground(new java.awt.Color(233,233,233));
					jPanel2.setLayout(null);
				
					{
						jTextField3 = new JTextField();
						jPanel2.add(jTextField3);
						jTextField3.setBounds(56, 7, 357, 21);
						jTextField3.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jLabel5 = new JLabel();
						jPanel2.add(jLabel5);
						jLabel5.setText("Datum eingrenzen von (DD-MM-YYYY)");
						jLabel5.setBounds(7, 32, 196, 28);
					}
					{
						jTextField4 = new JTextField();
						jPanel2.add(jTextField4);
						jTextField4.setBounds(196, 35, 84, 21);
						jTextField4.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jLabel6 = new JLabel();
						jPanel2.add(jLabel6);
						jLabel6.setText("bis");
						jLabel6.setBounds(288, 32, 14, 28);
					}
					{
						jTextField5 = new JTextField();
						jPanel2.add(jTextField5);
						jTextField5.setBounds(308, 35, 84, 21);
						jTextField5.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jButton7 = new JButton();
						jPanel2.add(jButton7);
						jButton7.setText("neuer Termin");
						jButton7.setBounds(147, 217, 98, 21);
					}
					{
						jButton8 = new JButton();
						jPanel2.add(jButton8);
						jButton8.setText("Termin aus Liste entfernen");
						jButton8.setBounds(238, 245, 175, 21);
					}
					{
						jButton9 = new JButton();
						jPanel2.add(jButton9);
						jButton9.setText("Bearbeiten");
						jButton9.setBounds(147, 245, 84, 21);
					}
					{
						jButton10 = new JButton();
						jPanel2.add(jButton10);
						jButton10.setText("Gehe zu Termin");
						jButton10.setBounds(7, 217, 112, 21);
					}
					{
						jLabel3 = new JLabel();
						jPanel2.add(jLabel3);
						jLabel3.setText("Suchen:");
						jLabel3.setBounds(7, 5, 49, 28);
					}
					{
						newTClist = new JButton();
						jPanel2.add(newTClist);
						newTClist.setText("neue Terminreihe");
						newTClist.setBounds(252, 217, 161, 21);
						newTClist.addMouseListener(this);
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel2.add(jScrollPane1);
						jScrollPane1.setBounds(7, 63, 406, 147);
						{
							ListModel jList2Model = new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
							jList2 = new JList();
							jScrollPane1.setViewportView(jList2);
							jList2.setModel(jList2Model);
							jList2.setSize(406, 147);
						}
					}
				}
			}
			{
				abort_button = new JButton();
				getContentPane().add(abort_button);
				abort_button.setText("Abbrechen");
				abort_button.setBounds(133, 315, 105, 21);
				abort_button.addMouseListener(this);
			}
			{
				apply_button = new JButton();
				getContentPane().add(apply_button);
				apply_button.setText("Übernehmen");
				apply_button.setBounds(266, 315, 112, 21);
				apply_button.addMouseListener(this);
			}
			{
				ok_button = new JButton();
				getContentPane().add(ok_button);
				ok_button.setText("OK");
				ok_button.setBounds(385, 315, 49, 21);
				ok_button.addMouseListener(this);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("- Zeigt Informationen an -");
				jLabel7.setBounds(7, 343, 427, 21);
				jLabel7.setBorder(BorderFactory.createTitledBorder(""));
			}
			
			
			
			
			
			if(ID>-1) readData(ID);			
			pack();
			this.setSize(449, 408);
			this.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().equals(abort_button)){
			this.dispose();
		}
		if(arg0.getSource().equals(ok_button)){
			update();
			dispose();
		}
		if(arg0.getSource().equals(apply_button)){
			update();
		}
		if(arg0.getSource().equals(newTClist)){
			TerminReiheFrame terminreihe = new TerminReiheFrame();
			terminreihe.setLocation(this.getLocation().x+20,this.getLocation().y+20);
			terminreihe.setTitle("Terminreihe hinzufügen");
			terminreihe.setVisible(true);
		}
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().equals(apply_button)){
			jLabel7.setText("Änderungen übernehmen");
		}
		if(arg0.getSource().equals(abort_button)){
			jLabel7.setText("Abbrechen");
		}
		if(arg0.getSource().equals(ok_button)){
			jLabel7.setText("OK");
		}
		
	}

	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource().equals(apply_button)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(abort_button)){
			jLabel7.setText("");
		}
		if(arg0.getSource().equals(ok_button)){
			jLabel7.setText("");
		}
		
	}

}

