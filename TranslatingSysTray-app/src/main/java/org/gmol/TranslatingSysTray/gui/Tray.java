package org.gmol.TranslatingSysTray.gui;

import java.awt.AWTException;
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
import java.awt.event.MouseMotionListener;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.TimerTask;

import org.gmol.TranslatingSysTray.translator.Translator;
import org.gmol.TranslatingSysTray.translator.TranslatorEx;

;

/**
 * Hello world!
 * 
 */
public class Tray implements IGui {

	private static final String TOOLTIP = "tray test";
	public static final String IMAGE = "/usr/share/pixmaps/gnome-gmush.png";
	public static final int DELAY = (5 * 1000);
	TrayIcon trayIcon = null;
	SystemTray tray = null;
	DisplayFrame frame = null;
	private boolean entered = false;
	Translator translator = null;
	String prevWord = "";
	String prevTranstalation = "";

	public Tray(String key) {		
		translator = new Translator(key);
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
				//
				tray.add(trayIcon);
				//Create and set up the window.
		        frame = new DisplayFrame("Translator");	 

			} catch (AWTException e) {
				System.err.println("Error starting tray: " + e);
			}			

			System.out.println("Add frame mouse listener");
			frame.addMouseListener(new MouseAdapter() {
		        @Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(e);
					int buttonPressed = e.getButton();
					int clickCount = e.getClickCount();
					switch (buttonPressed) {
					case MouseEvent.BUTTON1: // left button
						System.out.println("butt 1"); 
						if (clickCount == 1) {
							try {
								setText(translator.getNextTranslation());
							} catch (TranslatorEx e1) {
								// MYTODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (clickCount >= 2) {
						}
						break;
					case MouseEvent.BUTTON2: //middle button
						System.out.println("butt 2");
						if (clickCount == 1) {
						} else if (clickCount >= 2) {
						}
						break;
					case MouseEvent.BUTTON3: // rigth button
						System.out.println("butt 3");
						if (clickCount == 1) {
							try {
								setText(translator.getPreviousTranslation());
							} catch (TranslatorEx e1) {
								// MYTODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (clickCount >= 2) {
						}
						break;
					default:
						break;
					}
					super.mouseClicked(e);
				}
			});

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					System.out.println(e);
					int buttonPressed = e.getButton();
					int clickCount = e.getClickCount();
					switch (buttonPressed) {
					case MouseEvent.BUTTON1:
						System.out.println("butt 1");
						if (clickCount == 1) {
						} else if (clickCount >= 2) {
						}
						break;
					case MouseEvent.BUTTON2:
						System.out.println("butt 2");
						if (clickCount == 1) {
						} else if (clickCount >= 2) {
						}
						break;
					case MouseEvent.BUTTON3:
						System.out.println("butt 3");
						if (clickCount == 1) {
						} else if (clickCount >= 2) {
						}
						break;
					default:
						break;
					}
				}
			});

			trayIcon.addMouseMotionListener(new MouseAdapter() {
				public void mouseMoved(MouseEvent e) {
					System.out.println("Mouse entered");
					if (!entered) {					
						String word = getClipboard();
//						System.out.println("prev word(" + prevWord + ") word(" + word + ")");
						if (prevWord.equals(word)) {
							word = prevTranstalation;
						} else {
							prevWord = word;
							try {
								// mytodo uncomment
								translator.translate(word);
								word = translator.getNextTranslation();

							} catch (TranslatorEx e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							prevTranstalation = word;
						}
						System.out.println("entered is false set it to true");
						entered = true;
						frame.setVisible(entered);
						setText(word);

						long delay = DELAY;
						new java.util.Timer().schedule(new TimerTask() {
							public void run() {
								System.out
										.println("TimerTask: Set entered to false");
								entered = false;
//								frame.setVisible(entered);
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
		System.out.println(txt);
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
