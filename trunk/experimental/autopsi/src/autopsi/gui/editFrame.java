package autopsi.gui;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

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
public class editFrame extends javax.swing.JFrame {

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
	private JButton jButton10;
	private JButton jButton9;
	private JButton jButton8;
	private JButton jButton7;
	private JList jList2;
	private JTextField jTextField5;
	private JLabel jLabel6;
	private JTextField jTextField4;
	private JLabel jLabel5;
	private JTextField jTextField3;
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
	private JPanel jPanel2;
	private JButton jButton3;
	private JButton jButton1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		editFrame inst = new editFrame();
		inst.setVisible(true);
	}
	
	public editFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Termincontainer bearbeiten");
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
						jLabel2.setText("Beschreibung");
						jLabel2.setBounds(5, 39, 84, 28);
					}
					{
						jTextArea1 = new JTextArea();
						jPanel1.add(jTextArea1);
						jTextArea1.setBounds(91, 42, 322, 217);
						jTextArea1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
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
						jLabel3.setBounds(21, 7, 49, 21);
					}
				}
				{
					jPanel2 = new JPanel();
					jTabbedPane1.addTab("Termine", null, jPanel2, null);
					jPanel2.setBackground(new java.awt.Color(233,233,233));
					jPanel2.setLayout(null);
					{
						jLabel4 = new JLabel();
						jPanel2.add(jLabel4);
						jLabel4.setText("Suchen:");
						jLabel4.setBounds(7, 7, 63, 21);
					}
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
						ListModel jList2Model = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
						jList2 = new JList();
						jPanel2.add(jList2);
						jList2.setModel(jList2Model);
						jList2.setBounds(7, 63, 406, 147);
						jList2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					}
					{
						jButton7 = new JButton();
						jPanel2.add(jButton7);
						jButton7.setText("neuer Termin");
						jButton7.setBounds(231, 217, 98, 21);
					}
					{
						jButton8 = new JButton();
						jPanel2.add(jButton8);
						jButton8.setText("Termin aus Liste entfernen");
						jButton8.setBounds(231, 245, 182, 21);
					}
					{
						jButton9 = new JButton();
						jPanel2.add(jButton9);
						jButton9.setText("Bearbeiten");
						jButton9.setBounds(329, 217, 84, 21);
					}
					{
						jButton10 = new JButton();
						jPanel2.add(jButton10);
						jButton10.setText("Gehe zu Termin");
						jButton10.setBounds(7, 217, 112, 21);
					}
				}
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Abbrechen");
				jButton1.setBounds(294, 301, 105, 21);
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Übernehmen");
				jButton2.setBounds(406, 301, 112, 21);
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setText("OK");
				jButton3.setBounds(525, 301, 49, 21);
			}
			pack();
			this.setSize(589, 361);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
