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
	Translator translator = new Translator();
	
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
		App app = new App();
		String translated = app.translator.translate(args[0]);
		app.gui.setText(translated);
		
	
		
	}
}
