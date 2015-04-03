package cz.hartrik.asciiartist;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Okno pro výběr složky.
 * 
 * @version 2013-10-29
 * @author Patrik Harag
 */
public class DirChooser extends Chooser {

    private DirectoryChooser chooser;
    
    public DirChooser(Stage stage) {
        super(null);
        init();
    }

    private void init() {
        chooser = new DirectoryChooser();
        chooser.setTitle("Výběr složky");
        chooser.setInitialDirectory(initialDir);
    }

    @Override
    public File showOpenDialog() {
        return chooser.showDialog(stage);
    }
    
}