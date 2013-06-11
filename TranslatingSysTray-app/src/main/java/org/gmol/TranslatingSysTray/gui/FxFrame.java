//package org.gmol.TranslatingSysTray.gui;
//
//import java.awt.event.MouseListener;
//
//import javax.swing.JFrame;
//import javafx.embed.swing.JFXPanel;
//import javafx.application.Platform;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//
//public class FxFrame extends JFrame implements IFrame {
//
//	public FxFrame() {
//		// TODO Auto-generated constructor stub
//	}
//	
//    private static void initAndShowGUI() {
//        // This method is invoked on the EDT thread
//        JFrame frame = new JFrame("Swing and JavaFX");
//        final JFXPanel fxPanel = new JFXPanel();
//        frame.add(fxPanel);
//        frame.setSize(300, 200);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//            initFX(fxPanel);
//            }
//       });
//    }
//    
//    
//    private static void initFX(JFXPanel fxPanel) {
//        // This method is invoked on the JavaFX thread
//        Scene scene = createScene();
//        fxPanel.setScene(scene);
//    }
//
//    private static Scene createScene() {
//        Group  root  =  new  Group();
//        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
//        Text  text  =  new  Text();
//        text.setX(40);
//        text.setY(100);
//        text.setFont(new Font(25));
//        text.setText("Welcome JavaFX!");
//        root.getChildren().add(text);
//        return (scene);
//    }
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void addMouseListener(MouseListener l) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void showFrame() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void hideFrame() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setText(String txt) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
