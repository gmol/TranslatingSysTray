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
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;


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
       setSize(300, 200);
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
       javafx.scene.Scene scene = createScene();
       fxPanel.setScene(scene);
   }

   private Scene createScene() {
       Group  root  =  new  Group();
       javafx.scene.Scene  scene  =  new  javafx.scene.Scene(root, Color.ALICEBLUE);
       Text  text  =  new  Text();
       text.setX(40);
       text.setY(100);
       text.setFont(new Font(25));
       text.setText(translation);
       root.getChildren().add(text);
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

}
