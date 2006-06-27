package autopsi.gui.frame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class SecurityDialog extends javax.swing.JDialog implements ActionListener{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel textField;
	private JButton ok_button;
	private JButton abort_button;
	private String title = "";
	private String text = "";
	private JTextPane textpane;
	private boolean ok = false;
	private JFrame owner;

	/**
	* Auto-generated main method to display this JDialog
	*/
	
	
	public SecurityDialog(JFrame owner,String title, String text) {
		super(owner, true);
		this.title = title;
		this.text = text;
		this.owner = owner;
		initGUI();
		
		addWindowListener(new WindowAdapter()
				{
				public void windowClosing(WindowEvent arg0)
				{ //wird das Fenster über den X-Button rechts oben geschlossen
				  //wird die Anwendung beendet.
					enableOwner();
					super.windowClosing(arg0);
					dispose();
					}
				});
	}
	private void enableOwner()
	{
		owner.setEnabled(true);
	}
	private void initGUI() {
		try {
			{
				owner.setEnabled(false);
				this.setAlwaysOnTop(true);
				this.setTitle(title);
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					textField = new JLabel();
					getContentPane().add(textField);
					textField.setBounds(7, 14, 35, 35);			
					textField.setIcon(new ImageIcon("src/images/warning.GIF"));
					textField.setVisible(true);
				}
				{
					abort_button = new JButton();
					getContentPane().add(abort_button);
					abort_button.setText("Nein");
					abort_button.setBounds(238, 70, 56, 28);
					abort_button.addActionListener(this);
				}
				{
					ok_button = new JButton();
					getContentPane().add(ok_button);
					ok_button.setText("Ja");
					ok_button.setBounds(301, 70, 63, 28);
					ok_button.addActionListener(this);
				}
				{
					textpane = new JTextPane();
					getContentPane().add(textpane);
					textpane.setBounds(49, 7, 315, 56);
					textpane.setOpaque(false);
					textpane.setText(text);
					textpane.setEditable(false);
				}
			}
			this.setSize(379, 135);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getOk(){
		
		return ok;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(ok_button)){
			if(owner instanceof mainFrame)
			{
				mainFrame owner2 = (mainFrame)owner;
				owner2.delete_ok = true;
				owner2.updateInfoBar(true);
			}
			enableOwner();		
			dispose();
		}
		if(arg0.getSource().equals(abort_button))
			enableOwner();
			dispose();	
	}
}
