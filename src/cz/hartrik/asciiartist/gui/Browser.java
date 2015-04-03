
package cz.hartrik.asciiartist.gui;

import cz.hartrik.asciiartist.App;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Prohlížeč HTML výstupu.
 *
 * @version 2014-03-22
 * @author Patrik Harag
 */
public class Browser extends Stage {

    @FXML private WebView browser;
    private static final String FXML_FILE = App.GUI_PACKAGE + "Browser.fxml";
    
    private BrowserController controller;
    
    public Browser() {
        this("");
    }
    
    public Browser(String fileName) {
        this(fileName, StageStyle.DECORATED);
    }
    
    public Browser(String fileName, StageStyle style) {
        super(style);
        
        URL location = getClass().getResource(FXML_FILE);
        FXMLLoader loader = new FXMLLoader(location);
        try {
            setScene(new Scene((Parent) loader.load()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        controller = loader.getController();
        browser = controller.getBrowser();
        
        setTitle("ASCII art" + (fileName.isEmpty() ? "" : " - " + fileName));
        getIcons().add(new Image(getClass()
                .getResourceAsStream("Icon - planet.png"))); 
    }
    
    public void loadContent(String html) {
        browser.getEngine().loadContent(html);
        controller.setHtml(html);
    }
    
}