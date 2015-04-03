package cz.hartrik.asciiartist;

import cz.hartrik.asciiartist.gui.MainController;
import cz.hartrik.asciiartist.gui.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Vstupní třída.
 *
 * @version 2014-03-22
 * @author Patrik Harag
 */
public class App extends Application {
    
    public static final String GUI_PACKAGE = "/cz/hartrik/asciiartist/gui/";
    public static final String MAIN_PANEL = GUI_PACKAGE + "Main.fxml";
    public static final String SETTINGS_PANEL = GUI_PACKAGE + "Settings.fxml";
    
    private static App instance;
    
    private MainController mainController;
    private SettingsController settController;
    
    private Chooser fileChooser, directoryChooser;
    
    @Override
    public void start(Stage stage) throws Exception {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        instance = this;
        
        Parent main = FXMLLoader.load(getClass().getResource(MAIN_PANEL));
        Parent settings = FXMLLoader.load(getClass().getResource(SETTINGS_PANEL));
        
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(main, settings);
        
        Scene scene = new Scene(splitPane);
        
        fileChooser = new ImgChooser(stage);
        directoryChooser = new DirChooser(stage);
        
        stage.getIcons().add(
                new Image(App.class.getResourceAsStream("Icon - palette.png"))); 
        stage.setTitle("ASCII Artist II");
        stage.setScene(scene);
        stage.setMinHeight(550); stage.setMinWidth(850);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the appliction can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static App getInstance() { return instance; }
    public Chooser getFileChooser() { return fileChooser; }
    public Chooser getDirectoryChooser() { return directoryChooser; }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setSettigsController(SettingsController settigsController) {
        this.settController = settigsController;
    }

    public MainController getMainController() { return mainController; }
    public SettingsController getSettigsController() { return settController; }
    
}