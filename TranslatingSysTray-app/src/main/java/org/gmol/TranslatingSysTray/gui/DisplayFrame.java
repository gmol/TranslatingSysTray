package org.gmol.TranslatingSysTray.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class DisplayFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel mainPane = null;
	private JEditorPane textPane = null;

	public DisplayFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super("Translator");
		init();
	}

	public DisplayFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// Create main panel
		mainPane = new JPanel(new BorderLayout());

		// Create an editor pane.
		textPane = createEditorPane();
		JScrollPane editorScrollPane = new JScrollPane(textPane);
		// editorScrollPane.setVerticalScrollBarPolicy(
		// JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(800, 600));
		editorScrollPane.setMinimumSize(new Dimension(10, 10));

		mainPane.add(editorScrollPane);
		add(mainPane);

		pack();
	}

	private JEditorPane createEditorPane() {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);

		editorPane.setText("www.wp.pl");

		// java.net.URL helpURL = null;
		// try {
		// helpURL = new java.net.URL("http://www.wp.pl");
		// } catch (MalformedURLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// if (helpURL != null) {
		// try {
		// editorPane.setPage(helpURL);
		// } catch (IOException e) {
		// System.err.println("Attempted to read a bad URL: " + helpURL);
		// }
		// } else {
		// System.err.println("Couldn't find file: TextSampleDemoHelp.html");
		// }

		return editorPane;
	}

	public void setText(String txt) {
		textPane.setContentType("text/html;UTF-16");
		textPane.setText(txt);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
