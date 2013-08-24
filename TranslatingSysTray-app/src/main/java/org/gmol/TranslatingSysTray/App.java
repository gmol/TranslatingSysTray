package org.gmol.TranslatingSysTray;

import org.gmol.TranslatingSysTray.gui.Tray;
import org.apache.log4j.Logger;

import static org.kohsuke.args4j.ExampleMode.ALL;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;

/**
 * 
 */
public class App {
	
	private static final Logger LOGGER = Logger.getLogger(App.class);
	
	public String key;
	private boolean isJavafx = false;
	
	@Option(name="-fx", usage="Use Java Fx GUI")
	void setIsJavaFx(boolean javafx) {
		isJavafx = javafx;
	}
	
	public App(String key) {
		this.key = key;
		
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	private void createAndShowGUI() {
		new Tray(key);
	}
	
	private static boolean isJavaFxEnabled() {
		try {
			double jversion = Double.parseDouble(System.getProperty("java.specification.version"));
			if (jversion >= 1.7) {
				LOGGER.info("JavaFX enabled: YES");
				return true;
			}
		} catch (NumberFormatException e) {
			// printStackTrace(e);
			LOGGER.error("Fail to parse java runtime version property");
			System.err.println("ERROR: NumberFormatException");
			System.exit(1);
		}
		LOGGER.info("JavaFX enabled: NO");
		return false;
	}
	
	private void doMain(String[] args) {
		LOGGER.info("doMain");
        CmdLineParser parser = new CmdLineParser(this);
		try {
		    parser.parseArgument(args);
		} catch( CmdLineException e ) {
		    System.err.println(e.getMessage());
		    System.err.println("java -jar myprogram.jar [options...] arguments...");
		    parser.printUsage(System.err);
		    return;
		}
	}
	
	public static void main(String[] args) {		
		for (String string : args) {
			LOGGER.info("args: " + string);
		}
		String key = args[0];
		LogConfig.configureLogging("log4j.properties");
		new App(key);	
	}
}
