package cz.hartrik.asciiartist;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Okno pro výběr obrázku.
 * 
 * @version 2013-10-29
 * @author Patrik Harag
 */
public class ImgChooser extends Chooser {

    protected FileChooser fileChooser;
    
    public ImgChooser(Stage stage) {
        super(null);
        init();
    }

    private void init() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Výběr obrázku");
        fileChooser.setInitialDirectory(initialDir);
        fileChooser.getExtensionFilters().setAll(
                new FileChooser.ExtensionFilter("Všechny obrázky", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter(".jpg", "*.jpg"),
                new FileChooser.ExtensionFilter(".png", "*.png")
        );
    }

    @Override
    public File showOpenDialog() {
        return fileChooser.showOpenDialog(stage);
    }
    
}