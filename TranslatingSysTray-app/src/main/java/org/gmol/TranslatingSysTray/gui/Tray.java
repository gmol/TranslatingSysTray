package org.gmol.TranslatingSysTray.gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;
import org.gmol.TranslatingSysTray.translator.DatasetEx;
import org.gmol.TranslatingSysTray.translator.IDataset;
import org.gmol.TranslatingSysTray.translator.Translator;
import org.gmol.TranslatingSysTray.translator.TranslatorEx;

/**
 * 
 */
public class Tray implements IGui {

	private static final Logger LOGGER = Logger.getLogger(Tray.class);

	private static final String TOOLTIP = "tray test";
	public static final String IMAGE = "cambridge01.png";
	TrayIcon trayIcon;
	SystemTray tray;
	IFrame frame;;
	Translator translator;
	IDataset dataset;

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
			Image trayImage = Toolkit.getDefaultToolkit().getImage(
					Tray.class.getClassLoader().getResource(IMAGE));
			trayIcon = new TrayIcon(trayImage, TOOLTIP, createMenu());
			trayIcon.setImageAutoSize(true);
			try {
				//
				tray.add(trayIcon);
				// Create and set up the window.
				frame = new DisplayFrame("Translator");
			} catch (AWTException e) {
				System.err.println("Error starting tray: " + e);
			}

			LOGGER.debug("Add frame mouse listener");
			frame.addMouseListener(new FrameMouseListener());
			LOGGER.debug("Add tray mouse listener");
			trayIcon.addMouseListener(new TrayMouseListener());
		} else {
			System.err.println("SystemTray not supported");
		}
	}

	private PopupMenu createMenu() {
		PopupMenu menu = new PopupMenu();
//		MenuItem foo = new MenuItem("foo");
		MenuItem exit = new MenuItem("exit");
		exit.addActionListener(new ExitListener());
//		menu.add(foo);
//		menu.addSeparator();
		menu.add(exit);
		return menu;
	}

	class ExitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("exiting...");
			System.exit(0);
		}
	}
	
	class FrameMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			LOGGER.debug(e);
			int buttonPressed = e.getButton();
			int clickCount = e.getClickCount();
			switch (buttonPressed) {
			case MouseEvent.BUTTON1: // left button
				LOGGER.debug("butt 1");
				if (clickCount == 1) {
					try {
						setText(dataset.getNextTranslation());
					} catch (DatasetEx e1) {
						// MYTODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (clickCount >= 2) {
					frame.hideFrame();
				}
				break;
			case MouseEvent.BUTTON2: // middle button
				LOGGER.debug("butt 2");
				if (clickCount == 1) {
					LOGGER.debug("1 click, hide the frame");
					frame.hideFrame();
				} else if (clickCount >= 2) {
				}
				break;
			case MouseEvent.BUTTON3: // rigth button
				LOGGER.debug("butt 3");
				if (clickCount == 1) {
					try {
						setText(dataset.getPreviousTranslation());
					} catch (DatasetEx e1) {
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
	}

	class TrayMouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent event) {
			LOGGER.debug(event);
			switch (event.getButton()) {
			case MouseEvent.BUTTON1:
				LOGGER.debug("clicked butt 1");
				if (event.getClickCount() == 1) {
					String word = getClipboard();
					try {
						// mytodo uncomment
						dataset = translator.translate(word);
					} catch (TranslatorEx e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					LOGGER.debug("entered is false set it to true");
					frame.showFrame();
					try {
	                    setText(dataset.getFirstTranslation());
                    } catch (DatasetEx e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
                    }
					java.awt.EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							frame.showFrame();
						}
					});
				} else if (event.getClickCount() >= 2) {
				}
				break;
			case MouseEvent.BUTTON2:
				LOGGER.debug("butt 2");
				if (event.getClickCount() == 1) {
				} else if (event.getClickCount() >= 2) {
				}
				break;
			case MouseEvent.BUTTON3:
				LOGGER.debug("butt 3");
				if (event.getClickCount() == 1) {
				} else if (event.getClickCount() >= 2) {
				}
				break;
			default:
				break;
			}
			super.mousePressed(event);
		}		
	}
	
	@Override
	public void setText(String txt) {
		LOGGER.debug(txt);
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
