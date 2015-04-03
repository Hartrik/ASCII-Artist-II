package cz.hartrik.asciiartist;

import java.io.File;
import javafx.stage.Stage;

/**
 * Okno pro výběr souborů / složek.
 * 
 * @version 2013-10-29
 * @author Patrik Harag
 */
public abstract class Chooser {
    
    protected final Stage stage;
    
    public static final File initialDir = new File(
            System.getProperty("user.dir"));

    public Chooser(Stage stage) {
        this.stage = stage;
    }
    
    public abstract File showOpenDialog();
    
}