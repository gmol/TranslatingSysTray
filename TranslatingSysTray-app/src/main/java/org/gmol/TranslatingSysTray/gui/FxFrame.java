package org.gmol.TranslatingSysTray.gui;

import javafx.application.Platform;

import java.awt.event.MouseListener;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

//import java.awt.
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
//import java.io.fil

public class FxFrame extends JFrame implements IFrame {

	private static final Logger LOGGER = Logger.getLogger(FxFrame.class);
	private String translation = "";

	public FxFrame() {
		// TODO Auto-generated constructor stub
		LOGGER.debug("FxFrame");
	}

	private void initAndShowGUI() {
		LOGGER.debug("initAndShowGUI");
		// This method is invoked on the EDT thread
		// JFrame frame = new JFrame("Swing and JavaFX");
		final JFXPanel fxPanel = new JFXPanel();
		add(fxPanel);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Platform.runLater(new Runnable() {
			// @Override
			public void run() {
				initFX(fxPanel);
			}
		});
	}

	private void initFX(JFXPanel fxPanel) {
		LOGGER.debug("initFx");

		// This method is invoked on the JavaFX thread
		Scene scene = createScene();		
		fxPanel.setScene(scene);
	}

	private Scene createScene() {
		// Group root = new Group();
//		Scene scene = new Scene(root, Color.ALICEBLUE);
		LOGGER.debug("css: " + FxFrame.class.getClassLoader().getResource("mystyle.css").toString());
		
		LOGGER.debug("Tranalation:\n" + translation);
		int metaindex = translation.indexOf(">");
		String meta = translation.substring(0, metaindex+1);
		String mystyle = FxFrame.class.getClassLoader().getResource("mystyle.css").toString();
		String css = "<link href=\"" + mystyle + "\" rel=\"stylesheet\" type=\"text/css\" />";
		String rest = translation.substring(metaindex+1);
		String content = meta + css + rest;
		LOGGER.debug("index of meta data: " + metaindex);
		LOGGER.debug("content:\n" + content);
		Browser browser = new Browser(content);
		browser.
		Scene scene = new Scene(browser, 800, 600, Color.web("#666970"));
		// Text text = new Text();
		// text.setX(40);
		// text.setY(100);
		// text.setFont(new Font(25));
		// text.setText(translation);
		// root.getChildren().add(text);
		return (scene);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void addMouseListener(MouseListener l) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void showFrame() {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initAndShowGUI();
			}
		});

	}

	// @Override
	public void hideFrame() {
		// TODO Auto-generated method stub

	}

	// @Override
	public void setText(String txt) {
		// TODO Auto-generated method stub
		translation = txt;

	}

	@Override
	public void setStatusText(String txt) {
		// TODO Auto-generated method stub
		
	}

}

class Browser extends Region {

	final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();

	private static String readFile(String path) throws IOException {

		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

	public Browser(String translation) {
		// apply the styles
		// getStyleClass().add("browser");
		// load the web page
		try {
			// File f = new
			// File("/home/astulka/Documents/workspace/TranslatingSysTray/tmp/index.html");
//			File f = new File("index.html");
			// System.out.println(readFile("/home/astulka/Downloads/index.html"));

			// THAT WHAT I WAS LOOKING FOR
			 webEngine.loadContent(translation);

//			webEngine.load(f.toURI().toURL().toExternalForm());

		} catch (Exception e) {
			System.out.println(e);
		}
		// add the web view to the scene
		browser.setContextMenuEnabled(false);
		browser.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
	    	 
	        @Override
	        public void handle(javafx.scene.input.MouseEvent mouseEvent) {
	        	MouseButton button = mouseEvent.getButton();
	        	
	        	if (button.equals(MouseButton.PRIMARY)) {
	        		LOGGER.debug("PRIMARY button clicked");
	        	} else if (button.equals(MouseButton.SECONDARY)) {
	        		LOGGER.debug("SECONDARY button clicked");
	        	} else if (button.equals(MouseButton.MIDDLE)) {
	        		LOGGER.debug("MIDDLE button clicked");
	        	} else {
	        		LOGGER.debug("Button clicked:" + mouseEvent.toString());	
	        	}
	        	
	            System.out.println(mouseEvent.getEventType() + "\n"
	                    + "X : Y - " + mouseEvent.getX() + " : " + mouseEvent.getY() + "\n"
	                    + "SceneX : SceneY - " + mouseEvent.getSceneX() + " : " + mouseEvent.getSceneY() + "\n"
	                    + "ScreenX : ScreenY - " + mouseEvent.getScreenX() + " : " + mouseEvent.getScreenY());
	        }
	    });
		getChildren().add(browser);

	}

	private Node createSpacer() {
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		return spacer;
	}

	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}

	@Override
	protected double computePrefWidth(double height) {
		return 750;
	}

	@Override
	protected double computePrefHeight(double width) {
		return 500;
	}
}
