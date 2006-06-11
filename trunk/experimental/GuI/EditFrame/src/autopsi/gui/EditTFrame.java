package autopsi.gui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.BorderFactory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import autopsi.database.dao.GenericDAO;
import autopsi.database.dao.IGenericDAO;


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
public class EditTFrame extends javax.swing.JFrame implements java.awt.event.MouseListener{

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
	private JButton jButton2;
	private JLabel jLabel4;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JTextField jTextField5;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JLabel jLabel5;
	private JLabel jLabel3;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JTextField jTextField2;
	private JList jList1;
	private JPanel jPanel1;
	private JTextArea jTextArea1;
	private JLabel jLabel2;
	private JTextField jTextField1;
	private JLabel jLabel1;
	private JPanel jPanel3;
	private JButton jButton3;
	private JButton jButton1;
	
	private String sec_title = "";
	private String date;
	private int duration;
	private String desc = "";
	private Date upd_date;
	

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		EditTFrame inst = new EditTFrame();
		inst.setVisible(true);
	}
	
	public EditTFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Termin bearbeiten");
			getContentPane().setLayout(null);
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(7, 14, 567, 280);
				jTabbedPane1.setTabPlacement(JTabbedPane.LEFT);
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
						jTextField1 = new JTextField();
						jPanel1.add(jTextField1);
						jTextField1.setBounds(91, 14, 322, 21);
						jTextField1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Beschreibung:");
						jLabel2.setBounds(7, 112, 84, 28);
					}
					{
						jTextArea1 = new JTextArea();
						jPanel1.add(jTextArea1);
						jTextArea1.setBounds(91, 112, 322, 84);
						jTextArea1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jTextArea1.addFocusListener(new FocusListener(){

							public void focusGained(FocusEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void focusLost(FocusEvent arg0) {
								desc = ((JTextArea)arg0.getSource()).getText();
								
							}
							
						});
					}
					{
						jTextField3 = new JTextField();
						jPanel1.add(jTextField3);
						jTextField3.setBounds(91, 42, 322, 21);
						jTextField3.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jTextField3.addFocusListener(new FocusListener(){

							public void focusGained(FocusEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void focusLost(FocusEvent arg0) {
								sec_title = ((JTextField)arg0.getSource()).getText();
								
							}
							
						});
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Sekundär Titel:");
						jLabel4.setBounds(7, 42, 77, 21);
					}
					{
						jTextField4 = new JTextField();
						jPanel1.add(jTextField4);
						jTextField4.setBounds(91, 77, 98, 21);
						jTextField4.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jTextField4.addFocusListener(new FocusListener(){

							public void focusGained(FocusEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void focusLost(FocusEvent arg0) {
								date = ((JTextField)arg0.getSource()).getText();
								
							}
							
						});
					}
					{
						jTextField5 = new JTextField();
						jPanel1.add(jTextField5);
						jTextField5.setBounds(294, 77, 84, 21);
						jTextField5.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						jTextField5.addFocusListener(new FocusListener(){

							public void focusGained(FocusEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void focusLost(FocusEvent arg0) {
								try{
								duration = Integer.parseInt(((JTextField)arg0.getSource()).getText());
								}catch(Exception e){
									System.out.println("Fehler mit der Dauer");
								}
							}
							
						});
					}
					{
						jLabel6 = new JLabel();
						jPanel1.add(jLabel6);
						jLabel6.setText("Datum:");
						jLabel6.setBounds(7, 77, 56, 21);
					}
					{
						jLabel7 = new JLabel();
						jPanel1.add(jLabel7);
						jLabel7.setText("Dauer:");
						jLabel7.setBounds(238, 77, 42, 21);
					}
				}
				{
					jPanel3 = new JPanel();
					jTabbedPane1.addTab(
						"verknüpfte Objekte",
						null,
						jPanel3,
						null);
					jPanel3.setBackground(new java.awt.Color(255,255,255));
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
						jLabel3 = new JLabel();
						jPanel3.add(jLabel3);
						jLabel3.setText("Suchen:");
						jLabel3.setBounds(11, 5, 49, 28);
					}

				}
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Abbrechen");
				jButton1.setBounds(294, 301, 105, 21);
				jButton1.addMouseListener(this);
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Übernehmen");
				jButton2.setBounds(406, 301, 112, 21);
				jButton2.addMouseListener(this);
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setText("OK");
				jButton3.setBounds(525, 301, 49, 21);
				jButton3.addMouseListener(this);
			}
			{
				jLabel5 = new JLabel("- Zeigt Informationen an -");
				this.add(jLabel5);
				jLabel5.setBounds(0, 343, 588, 21);
				jLabel5.setBorder(BorderFactory.createTitledBorder(""));
			}

			this.setSize(589, 403);
			this.setPreferredSize(new java.awt.Dimension(589, 403));
			this.setResizable(false);
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource().equals(jButton1)){
			this.dispose();
		}
		if(arg0.getSource().equals(jButton3)){
			IGenericDAO gdo = new GenericDAO();
			gdo.setCurrentTable("tcdatatable");
			try{
				TCData lookup = new TCData(), updateData = new TCData();
				lookup.id = 1;
				updateData.id = 1;
				updateData.title = title;
				updateData.description = desc;
				gdo.updDataObjects(lookup, updateData);
				dispose();
			}
			catch (Exception e){
				System.out.println("Exception beim Updaten=="+e.toString());
			}
		}
		if(arg0.getSource().equals(jButton2)){
			IGenericDAO gdo = new GenericDAO();
			gdo.setCurrentTable("tdatatable");
			try{
				TData lookup = new TData(), updateData = new TData();
				lookup.id = 1;
				updateData.id = 1;
				updateData.sec_title = sec_title;
				updateData.description = desc;
				updateData.duration = duration;
				try{
					//Hier muss noch der Date umgewandelt werden
					
					updateData.date = upd_date;
				}catch(Exception e){
					System.out.println("Fehler mit dem Datum");
				}
				
				gdo.updDataObjects(lookup, updateData);
				
			}
			catch (Exception e){
				System.out.println("Exception beim Updaten=="+e.toString());
			}}
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().equals(jButton2)){
			jLabel5.setText("Änderungen übernehmen");
		}
		if(arg0.getSource().equals(jButton1)){
			jLabel5.setText("Abbrechen");
		}
		if(arg0.getSource().equals(jButton3)){
			jLabel5.setText("OK");
		}
	}

	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource().equals(jButton2)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(jButton1)){
			jLabel5.setText("");
		}
		if(arg0.getSource().equals(jButton3)){
			jLabel5.setText("");
		}
		
	}

}


