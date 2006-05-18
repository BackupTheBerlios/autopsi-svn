package rm.report.base;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import rm.report.base.LogConfigurator;

import rm.report.gui.MainFrame;
import javax.swing.*;


public class Report {
	
	private static Log log = LogFactory.getLog("Report");
	
	public static void main(String[] args){
		
		System.out.print("\nReport, the universal tool for reporters will start in a few seconds, please hold the line...\n\n");
		
		try{
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (Exception e){
			log.debug("Fehler beim setzen von LookAndFeel");
		}
		
		LogConfigurator.configureLog();
		MainFrame mFrame = new MainFrame();
		mFrame.setSize(800,600);
		mFrame.setVisible(true);
		String[] test = {"a","b"};
/*		ViewFrame frame = new ViewFrame(test);
		frame.setVisible(true);*/
	}
}
