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
import java.io.FileInputStream;
import java.io.File;
import java.nio.channels.FileChannel ;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import java.io.IOException;

public class Main extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(new Browser(),750,500, Color.web("#666970"));
        stage.setScene(scene);
//        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");        
        stage.show();
    }
 
    public static void main(String[] args){
        launch(args);
    }
}
class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    
private static String readFile(String path) throws IOException {

 FileInputStream stream = new FileInputStream(new File(path));
  try { 
    FileChannel fc = stream.getChannel();
    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
    /* Instead of using default, pass in a decoder. */
    return Charset.defaultCharset().decode(bb).toString();
  }
  finally {
    stream.close();
  }
}

    public Browser() {
        //apply the styles
//        getStyleClass().add("browser");
        // load the web page
try {
	File f = new File("/home/astulka/Downloads/index.html");
	System.out.println(readFile("/home/astulka/Downloads/index.html"));
        webEngine.load(f.toURI().toURL().toExternalForm());
       
} catch (IOException e) {
System.out.println(e);
}
        //add the web view to the scene
        getChildren().add(browser);
 
    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}
