package rm.report.base;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;


public class LogConfigurator {
	
	private final static String LOGFILENAME = "log.properties";
	
	private static Log log = LogFactory.getLog("rm.report.base");
	
	public static void configureLog(){
		File file = new File(LOGFILENAME);
		if (!file.exists())
			System.out.println("log configuration file not found!");
		PropertyConfigurator.configure(LOGFILENAME);
		log.info("Logger started...");
	}
	
}
