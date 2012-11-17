package org.gmol.TranslatingSysTray.gui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

;

/**
 * Hello world!
 * 
 */
public class Tray implements IGui {

	private static final String TOOLTIP = "tray test";
	private static final String IMAGE = "/usr/share/pixmaps/gnome-gmush.png";
	TrayIcon trayIcon = null;
	SystemTray tray = null;
	DisplayFrame frame = null;
	private boolean entered = false;

	public Tray() {
		open();
	}
	
	

	public static void main(String[] args) {
		// new Tray().open();
	}

	private void open() {
		if (SystemTray.isSupported()) {
			
	        
			
			tray = SystemTray.getSystemTray();
			Image trayImage = Toolkit.getDefaultToolkit().getImage(IMAGE);
			trayIcon = new TrayIcon(trayImage, TOOLTIP, createMenu());
			trayIcon.setImageAutoSize(true);
			try {
				tray.add(trayIcon);

				//Create and set up the window.
		        frame = new DisplayFrame("Translator");	 
		        frame.setVisible(true);

			} catch (AWTException e) {
				System.err.println("Error starting tray: " + e);
			}

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					System.out.println(e);
					int buttonPressed = e.getButton();
					int clickCount = e.getClickCount();
					switch (buttonPressed) {
					case MouseEvent.BUTTON1:
						if (clickCount == 1) {

						} else if (clickCount >= 2) {

						}
						break;
					case MouseEvent.BUTTON2:
						if (clickCount == 1) {

						} else if (clickCount >= 2) {

						}
						break;
					case MouseEvent.BUTTON3:
						if (clickCount == 1) {

						} else if (clickCount >= 2) {

						}
						break;
					default:
						break;
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseReleased(e);
				}
			});

			trayIcon.addMouseMotionListener(new MouseAdapter() {
				public void mouseMoved(MouseEvent e) {
					if (!entered) {
						System.out.println("entered is false set it to true");
						entered = true;

						System.out.println("Clipboard:\n" + getClipboard()
								+ "\n");

						long delay = 10 * 1000;
						new java.util.Timer().schedule(new TimerTask() {
							public void run() {
								System.out
										.println("TimerTask: Set entered to false");
								entered = false;
							}
						}, delay);
						System.out.println(e);
					}
				}
			});

		} else {
			System.err.println("SystemTray not supported");
		}
	}

	private PopupMenu createMenu() {
		PopupMenu menu = new PopupMenu();
		MenuItem foo = new MenuItem("foo");
		MenuItem exit = new MenuItem("exit");
		exit.addActionListener(new exitListener());
		menu.add(foo);
		menu.addSeparator();
		menu.add(exit);
		return menu;
	}
	
	  

	class exitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("exiting...");
			System.exit(0);
		}
	}

	@Override
	public void setText(String txt) {
		trayIcon.displayMessage("caption", txt, TrayIcon.MessageType.INFO);
		System.out.println(txt);
		System.out.println("Get clipboard:");
		System.out.println(getClipboard());
		frame.setText(txt);
		

	}

	private String getClipboard() {
		Transferable clipData = Toolkit.getDefaultToolkit()
				.getSystemClipboard().getContents(trayIcon);
		String s;
		try {
			s = (String) (clipData.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception ee) {
			s = ee.toString();
		}
		return s;
	}
}
