package cz.hartrik.asciiartist.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @version 2013-10-29
 * @author Patrik Harag
 */
public class BrowserController implements Initializable {
    
    private static FileChooser chooser;
    
    @FXML private WebView browser;
    private String html;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    public void save(ActionEvent e) {
        if (chooser == null) setUpFileChooser();
        
        Window window = browser.getScene().getWindow();
        File file = chooser.showSaveDialog(window);
        
        if (file != null) {
            if (!file.getAbsolutePath().endsWith(".html"))
                file = new File(file.getAbsolutePath() + ".html");
            if (file.exists()) file.delete();
            create(file, html);
        }
    }
    
    private void create(File file, String string) {
        try {
            Path target = file.toPath();
            Path newFile = Files.createFile(target);
            Files.newBufferedWriter(newFile, Charset.forName("UTF-8"))
                    .append(string).close();
        } catch (IOException ex) {
            throw new RuntimeException("Chyba při ukládání souboru - ", ex);
        }
    }
    
    public WebView getBrowser() { return browser; }
    
    private void setUpFileChooser() {
        chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.setInitialFileName("ASCII art");
        chooser.getExtensionFilters().setAll(
                new FileChooser.ExtensionFilter(".html", "*.html")
        );
    }

    public void setHtml(String html) {
        this.html = html;
    }
    
}