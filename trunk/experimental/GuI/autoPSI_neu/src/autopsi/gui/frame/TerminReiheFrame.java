package autopsi.gui.frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

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
public class TerminReiheFrame extends javax.swing.JFrame implements java.awt.event.MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6652693923046118447L;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JCheckBox check1;
	private JLabel jLabel2;
	private JFormattedTextField EndDate_field;
	private JLabel jLabel3;
	private JFormattedTextField beginDate_field;
	private JLabel jLabel1;
	private JTextField name_field;
	private JLabel jLabel4;
	private JButton ok_button;
	private JButton abort_button;
	private JLabel infoLabel;
	private JSeparator jSeparator2;
	private JFormattedTextField zeit1;
	private JButton oneForAll1;
	private JTextField dauer1;
	private JTextField ort1;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JSeparator jSeparator1;
	
	private JTextField[] ortArray = new JTextField[7];
	private JFormattedTextField[] zeitArray = new JFormattedTextField[7];
	private JTextField[] dauerArray = new JTextField[7];
	private JButton[] buttonArray = new JButton[7];
	private JCheckBox[] checkArray = new JCheckBox[7];


	public TerminReiheFrame() {
		super();
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
			getContentPane().setLayout(null);
			
			{
				for (int i=0;i<7;i++)
				{
					check1 = new JCheckBox();
					getContentPane().add(check1);
					check1.setText("Montag");
					check1.setBounds(7, 112+i*28, 93, 28);
					checkArray[i]=check1;
					checkArray[i].addMouseListener(this);
					checkArray[i].addMouseMotionListener(this);
				}
				checkArray[0].setText("Montag");
				checkArray[1].setText("Dienstag");
				checkArray[2].setText("Mittwoch");
				checkArray[3].setText("Donnerstag");
				checkArray[4].setText("Freitag");
				checkArray[5].setText("Samstag");
				checkArray[6].setText("Sonntag");
			}
			{
				name_field = new JTextField();
				getContentPane().add(name_field);
				name_field.setBounds(63, 14, 371, 21);
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
				beginDate_field = new JFormattedTextField(createFormatter("##-##-#####"));
				getContentPane().add(beginDate_field);
				beginDate_field.setBounds(63, 42, 77, 21);
				beginDate_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("bis");
				jLabel3.setBounds(147, 42, 21, 21);
			}
			{
				EndDate_field = new JFormattedTextField(createFormatter("##-##-#####"));
				getContentPane().add(EndDate_field);
				EndDate_field.setBounds(168, 42, 77, 21);
				EndDate_field.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				jSeparator1 = new JSeparator();
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(7, 98, 427, 7);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Ort");
				jLabel4.setBounds(112, 84, 28, 14);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Zeit");
				jLabel5.setBounds(252, 84, 35, 14);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Dauer (min)");
				jLabel6.setBounds(315, 84, 56, 14);
			}
			{	
				for (int i=0;i<7;i++)
				{
					ort1 = new JTextField();
					getContentPane().add(ort1);
					ort1.setBounds(112, 112+i*28, 126, 21);
					ort1.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
					ort1.setEnabled(false);
					ortArray[i]=ort1;
				}
			}
			{
				for (int i=0;i<7;i++)
				{
					zeit1 = new JFormattedTextField(createFormatter("##:##"));
					getContentPane().add(zeit1);
					zeit1.setBounds(252, 112+i*28, 49, 21);
					zeit1.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
					zeit1.setEnabled(false);
					zeitArray[i]=zeit1;
				}
				
			}
			{
				for (int i=0;i<7;i++)
				{
					dauer1 = new JTextField();
					getContentPane().add(dauer1);
					dauer1.setBounds(315, 112+i*28, 49, 21);
					dauer1.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
					dauer1.setEnabled(false);
					dauerArray[i]=dauer1;
				}
				
			}
			{
				for (int i=0;i<7;i++)
				{
					oneForAll1 = new JButton();
					getContentPane().add(oneForAll1);
					oneForAll1.setBounds(378, 112+i*28, 35, 21);
					oneForAll1.setIcon(new ImageIcon("src/images/oneForAll.GIF"));
					oneForAll1.setEnabled(false);
					buttonArray[i]=oneForAll1;
					buttonArray[i].addMouseListener(this);
				}
				
			}
			{
				infoLabel = new JLabel();
				getContentPane().add(infoLabel);
				infoLabel.setIcon(new ImageIcon("src/images/info.GIF"));
				infoLabel.setBounds(7, 343, 420, 21);
			}
			{
				abort_button = new JButton();
				getContentPane().add(abort_button);
				abort_button.setText("Abbrechen");
				abort_button.setBounds(266, 322, 91, 21);
			}
			{
				ok_button = new JButton();
				getContentPane().add(ok_button);
				ok_button.setText("OK");
				ok_button.setBounds(364, 322, 63, 21);
			}
			{
				jSeparator2 = new JSeparator();
				getContentPane().add(jSeparator2);
				jSeparator2.setBounds(7, 315, 427, 7);
			}
			pack();
			this.setSize(449, 401);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected MaskFormatter createFormatter(String s) {
		 MaskFormatter formatter = null;
		 try {
			 formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}

	public void mouseClicked(MouseEvent arg0) {
		for(int i=0;i<7;i++)
		{
			if(arg0.getSource().equals(buttonArray[i]))
			{
				for(int j = 0;j<7;j++)
				{
					ortArray[j].setText(ortArray[i].getText());
					zeitArray[j].setText(zeitArray[i].getText());
					dauerArray[j].setText(dauerArray[i].getText());	
				}
			}
		}
		for(int i=0;i<7;i++) //Checkboxen überprüfen
		{
			if(arg0.getSource().equals(checkArray[i]))
			{
				boolean check;
				if(checkArray[i].getSelectedObjects()!=null) check = true;
				else check = false;
				ortArray[i].setEnabled(check);
				zeitArray[i].setEnabled(check);
				dauerArray[i].setEnabled(check);
				buttonArray[i].setEnabled(check);
			}
		}

	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {		
		for(int i=0;i<7;i++)
		{
			if(arg0.getSource().equals(buttonArray[i]))
			{
				infoLabel.setText("Setzt alle Ort-,Zeit- und Dauer-Felder auf die Werte dieses Tags.");
			}
		}
		
	}

	public void mouseExited(MouseEvent arg0) {
		for(int i=0;i<7;i++)
		{
			if(arg0.getSource().equals(buttonArray[i]))
			{
				infoLabel.setText("");
			}
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		
	}

	public void mouseMoved(MouseEvent arg0) {
		for(int i=0;i<7;i++) //Checkboxen überprüfen
		{
			if(arg0.getSource().equals(checkArray[i]))
			{
				boolean check;
				if(checkArray[i].getSelectedObjects()!=null) check = true;
				else check = false;
				ortArray[i].setEnabled(check);
				zeitArray[i].setEnabled(check);
				dauerArray[i].setEnabled(check);
				buttonArray[i].setEnabled(check);
			}
		}
		
	}

}
