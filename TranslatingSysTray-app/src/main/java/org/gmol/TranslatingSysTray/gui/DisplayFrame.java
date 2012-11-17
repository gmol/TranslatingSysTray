package org.gmol.TranslatingSysTray.gui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.TrayIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class DisplayFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPane = null;
	private JTextPane textPane = new JTextPane();

	public DisplayFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		init();
	}

	public DisplayFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
		init();
	}

	public DisplayFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		init();
	}

	public DisplayFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       //Create and set up the content pane.        
        textPane.setOpaque(true); //content panes must be opaque
        setContentPane(textPane);
 
        //Display the window.
        pack();
	}

	public void setText(String txt) {
		textPane.setText(txt);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
