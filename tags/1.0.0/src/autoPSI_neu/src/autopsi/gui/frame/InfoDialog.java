package autopsi.gui.frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JTextPane;

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
public class InfoDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JButton okbutton;
	private JFrame owner;
	String title = "";
	String text = "";
	private JTextPane textpane;

	/**
	* Auto-generated main method to display this JDialog
	*/
	
	public InfoDialog(JFrame owner, String title,String text) {
		this.title = title;
		this.text = text;
		this.owner = owner;
		this.setAlwaysOnTop(true);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				owner.setEnabled(false);
				this.setResizable(false);
				getContentPane().setLayout(null);
				{
					okbutton = new JButton();
					getContentPane().add(okbutton);
					okbutton.setText("OK");
					okbutton.setBounds(105, 77, 63, 21);
					okbutton.addActionListener(new ActionListener(){

						public void actionPerformed(ActionEvent arg0) {
							owner.setEnabled(true);
							dispose();
						}	
					});
				}
				{
					textpane = new JTextPane();
					getContentPane().add(textpane);
					textpane.setText(text);
					textpane.setBounds(7, 7, 266, 63);
					textpane.setOpaque(false);
					textpane.setEditable(false);
				}
			}
			this.setSize(288, 135);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
