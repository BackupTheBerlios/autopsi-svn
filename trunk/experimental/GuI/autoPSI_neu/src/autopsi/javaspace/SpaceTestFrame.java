package autopsi.javaspace;

import javax.swing.JButton;
import javax.swing.JFrame;

import autopsi.database.table.Notiz;

public class SpaceTestFrame extends JFrame {

	protected JButton renewButton;
	protected JButton cancelButton;
	
	public SpaceTestFrame(){
		ServiceCommunicator com = new ServiceCommunicator();
		com.delAllObjects();
		Notiz insert = new Notiz();
		insert.setNote("here we are");
		com.addObject(insert);
		Notiz lookup = new Notiz();
		lookup.setNote(null);
		Notiz out = null;
		out = (Notiz)com.getObject(lookup);
		System.out.println("Notiz       out.getNote()=="+out.getNote());
	}
	
}
