package autopsi.gui.frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
public class CreditsFrame extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JButton okbutton;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CreditsFrame inst = new CreditsFrame(frame);
		inst.setVisible(true);
	}
	
	public CreditsFrame(JFrame frame) {
		super(frame);
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
			{
				getContentPane().setLayout(null);
				this.setTitle("AutoPSI 1.0 - Personal Student Information v1.0");
				getContentPane().setBackground(new java.awt.Color(0,0,64));
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setIcon(new ImageIcon("src/images/credits.png"));
					jLabel1.setBounds(0, -7, 399, 315);
				}
				{
					okbutton = new JButton();
					getContentPane().add(okbutton);
					okbutton.setText("OK");
					okbutton.setBounds(154, 307, 84, 21);
					okbutton.setOpaque(false);
				}
			}
			this.setSize(407, 365);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
