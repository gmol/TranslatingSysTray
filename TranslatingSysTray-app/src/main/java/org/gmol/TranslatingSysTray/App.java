package org.gmol.TranslatingSysTray;

import org.gmol.TranslatingSysTray.gui.Tray;
import org.apache.log4j.Logger;

/**
 * 
 */
public class App {
	
	private static final Logger LOGGER = Logger.getLogger(App.class);
	
	public String key;
	
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
	
	public static void main(String[] args) {		
		for (String string : args) {
			LOGGER.info("args: " + string);
		}
		String key = args[0];
		LogConfig.configureLogging("log4j.properties");
		new App(key);	
	}
}
