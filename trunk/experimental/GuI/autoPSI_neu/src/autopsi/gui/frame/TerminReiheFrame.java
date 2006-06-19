package autopsi.gui.frame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

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
public class TerminReiheFrame extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JCheckBox CheckMonday;
	private JCheckBox checkTuesday;
	private JCheckBox checkThursday;
	private JCheckBox checkSaturday;
	private JLabel jLabel2;
	private JFormattedTextField EndDate_field;
	private JLabel jLabel3;
	private JFormattedTextField beginDate_field;
	private JLabel jLabel1;
	private JTextField name_field;
	private JCheckBox checkSunday;
	private JLabel jLabel4;
	private JTextField ort3;
	private JPanel jPanel2;
	private JPanel panel3;
	private JButton oneForAll2;
	private JTextField dauer2;
	private JTextField ort2;
	private JPanel jPanel1;
	private JPanel panel1;
	private JButton oneForAll1;
	private JTextField dauer1;
	private JTextField ort1;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JSeparator jSeparator1;
	private JCheckBox checkFriday;
	private JCheckBox checkWednesday;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		TerminReiheFrame inst = new TerminReiheFrame();
		inst.setVisible(true);
	}
	
	public TerminReiheFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Terminreihe erstellen");
			{
				CheckMonday = new JCheckBox();
				getContentPane().add(CheckMonday);
				CheckMonday.setText("Montag");
				CheckMonday.setBounds(7, 112, 63, 28);
			}
			{
				checkTuesday = new JCheckBox();
				getContentPane().add(checkTuesday);
				checkTuesday.setText("Dienstag");
				checkTuesday.setBounds(7, 140, 70, 28);
			}
			{
				checkWednesday = new JCheckBox();
				getContentPane().add(checkWednesday);
				checkWednesday.setText("Mittwoch");
				checkWednesday.setBounds(7, 168, 70, 28);
			}
			{
				checkThursday = new JCheckBox();
				getContentPane().add(checkThursday);
				checkThursday.setText("Donnerstag");
				checkThursday.setBounds(7, 196, 84, 28);
			}
			{
				checkFriday = new JCheckBox();
				getContentPane().add(checkFriday);
				checkFriday.setText("Freitag");
				checkFriday.setBounds(7, 224, 63, 28);
			}
			{
				checkSaturday = new JCheckBox();
				getContentPane().add(checkSaturday);
				checkSaturday.setText("Samstag");
				checkSaturday.setBounds(7, 252, 70, 28);
			}
			{
				checkSunday = new JCheckBox();
				getContentPane().add(checkSunday);
				checkSunday.setText("Sonntag");
				checkSunday.setBounds(7, 280, 70, 28);
			}
			{
				name_field = new JTextField();
				getContentPane().add(name_field);
				name_field.setBounds(63, 14, 343, 21);
				name_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Name:");
				jLabel1.setBounds(7, 14, 35, 21);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Zeitraum:");
				jLabel2.setBounds(7, 42, 63, 21);
			}
			{
				beginDate_field = new JFormattedTextField();
				getContentPane().add(beginDate_field);
				beginDate_field.setBounds(63, 42, 77, 21);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("bis");
				jLabel3.setBounds(147, 42, 21, 21);
			}
			{
				EndDate_field = new JFormattedTextField();
				getContentPane().add(EndDate_field);
				EndDate_field.setBounds(168, 42, 77, 21);
			}
			{
				jSeparator1 = new JSeparator();
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(7, 98, 399, 7);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Ort");
				jLabel4.setBounds(119, 84, 28, 14);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Zeit");
				jLabel5.setBounds(259, 84, 35, 14);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Dauer");
				jLabel6.setBounds(322, 84, 42, 14);
			}
			{
				panel1 = new JPanel();
				getContentPane().add(panel1);
				panel1.setBounds(105, 112, 343, 28);
				panel1.setLayout(null);
				panel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					ort1 = new JTextField();
					panel1.add(ort1);
					ort1.setBounds(7, 7, 126, 21);
					ort1.setBorder(new LineBorder(
						new java.awt.Color(0, 0, 0),
						1,
						false));
				}
				{
					dauer1 = new JTextField();
					panel1.add(dauer1);
					dauer1.setBounds(210, 7, 63, 21);
					dauer1.setBorder(new LineBorder(
						new java.awt.Color(0, 0, 0),
						1,
						false));
				}
				{
					oneForAll1 = new JButton();
					panel1.add(oneForAll1);
					oneForAll1.setBounds(287, 0, 49, 28);
					oneForAll1.setIcon(new ImageIcon(getClass()
						.getClassLoader().getResource("images/oneForAll.GIF")));
				}
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(105, 140, 343, 28);
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel1.setLayout(null);
				{
					ort2 = new JTextField();
					jPanel1.add(ort2);
					ort2.setBounds(7, 7, 126, 21);
					ort2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				}
				{
					dauer2 = new JTextField();
					jPanel1.add(dauer2);
					dauer2.setBounds(210, 7, 63, 21);
					dauer2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				}
				{
					oneForAll2 = new JButton();
					jPanel1.add(oneForAll2);
					oneForAll2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/oneForAll.GIF")));
					oneForAll2.setBounds(287, 0, 49, 28);
				}
			}
			{
				panel3 = new JPanel();
				getContentPane().add(panel3);
				panel3.setBounds(161, 231, 63, 28);
			}
			{
				jPanel2 = new JPanel();
				getContentPane().add(jPanel2);
				jPanel2.setBounds(105, 168, 343, 28);
				jPanel2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel2.setLayout(null);
				{
					ort3 = new JTextField();
					jPanel2.add(ort3);
					ort3.setBounds(7, 7, 126, 21);
					ort3.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				}
			}
			pack();
			this.setSize(477, 373);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
