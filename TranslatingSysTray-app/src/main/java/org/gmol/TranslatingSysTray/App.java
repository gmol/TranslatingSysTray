package org.gmol.TranslatingSysTray;

import org.gmol.TranslatingSysTray.gui.IGui;
import org.gmol.TranslatingSysTray.gui.Tray; 
import org.gmol.TranslatingSysTray.translator.Translator;

/**
 * 
 * 
 */
public class App {
	
	private IGui gui;
	
	public App() {
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	private void createAndShowGUI() {
		gui = new Tray();
	}
	
	public static void main(String[] args) {		
		for (String string : args) {
			System.out.println("args: " + string);
		}
		String key = args[0];
		Translator.KEY = key;
		new App();	
	}
}
