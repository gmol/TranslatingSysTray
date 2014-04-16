package org.gmol.TranslatingSysTray.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class DisplayFrame extends JFrame implements IFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel mainPane = null;
	private JEditorPane textPane = null;
	JLabel statusLabel = new JLabel("status");

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


		setState(java.awt.Frame.NORMAL);
		setAlwaysOnTop(true);
		toFront();
//		repaint();
		
		mainPane.add(editorScrollPane);
		
		// Status bar
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(getWidth(), 20));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		
		add(mainPane);

		pack();
	}
	
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		textPane.addMouseListener(l);
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

	@Override
	public void showFrame() {
		setVisible(true);
	}

	@Override
	public void hideFrame() {
		setVisible(false);
		setAlwaysOnTop(false);
	}

	@Override
	public void setStatusText(String txt) {
		// TODO Auto-generated method stub
		statusLabel.setText(" " + txt);
		
	}
}
