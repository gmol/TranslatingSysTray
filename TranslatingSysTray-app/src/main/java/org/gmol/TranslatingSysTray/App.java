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

	public static final int WRONG_CMD_LINE_OPTIONS = 1;
	public static final int NUMBER_FORMAT_EXCEPTION = 2;
	private static final Logger LOGGER = Logger.getLogger(App.class);
	
	@Option(name="-k", usage="set cambridge api key", required=true, aliases="--key")
	public String key;
	
	@Option(name="-fx", usage="use Java Fx GUI", required=false, aliases="--javafx")
	private boolean isJavafx = false;
	
	
//	void setIsJavaFx(boolean javafx) {
//		isJavafx = javafx;
//	}
	
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
	
	public App(String[] args) {
		doMain(args);
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	private void createAndShowGUI() {
		new Tray(key, isJavafx);
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
			System.exit(NUMBER_FORMAT_EXCEPTION);
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
			System.err.println();
			System.err.println("***********************************************************");
		    System.err.println(e.getMessage());
		    System.err.println("-----------------------------------------------------------");
		    parser.printUsage(System.err);
		    System.err.println("-----------------------------------------------------------");
            System.err.println("Example: $JAVA_HOME/bin/java -jar ./TranslatingSysTray-app/target/TranslatingSysTray-0.0.1-SNAPSHOT-jar-with-dependencies.jar"+parser.printExample(ALL));
            System.err.println("***********************************************************");
            System.err.println();
            System.exit(WRONG_CMD_LINE_OPTIONS);
		    return;
		}
	}
	
	public static void main(String[] args) {		
		for (String string : args) {
			LOGGER.info("args: " + string);
		}		
		LogConfig.configureLogging("log4j.properties");
		new App(args);	
	}
}
