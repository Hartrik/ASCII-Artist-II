package cz.hartrik.asciiartist.gui;

import cz.hartrik.asciiartist.App;
import cz.hartrik.asciiart.AsciiArtMetrics;
import cz.hartrik.util.*;
import cz.hartrik.asciiart.gen.*;
import cz.hartrik.asciiart.gen.html.DefaultFormatterColored;
import cz.hartrik.asciiart.gen.html.DefaultFormatter;
import cz.hartrik.common.io.BufferedImageUtil;
import cz.hartrik.common.io.NioUtil;
import cz.hartrik.common.ui.javafx.ExceptionDialog;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Controller k hlavnímu panelu.
 * 
 * @version 2015-04-03
 * @author Patrik Harag
 */
public class MainController implements Initializable {
    
    private File sourceFile;
    private BufferedImage sourceImage;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle rb) {
        App.getInstance().setMainController(this);
        initDimensions();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Obsluha komponent">

    //<editor-fold defaultstate="collapsed" desc="Bod 1. - vstup">
    @FXML private Label lSource;
    
    @FXML
    private void chooseFile() {
        File file = App.getInstance().getFileChooser().showOpenDialog();
        if (file != null) {
            this.sourceFile = file;
            lSource.setText(sourceFile.getName());
            
            // obrázek se rovnou načte
            try {
                BufferedImage loadImage = ImageIO.read(file);
                sourceImage = loadImage; bCreate.setDisable(false);
            } catch (IOException ex) {
                bCreate.setDisable(true);
                error(ex);
                return;
            }
        }
        updateInfo();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Bod 2. - rozměry">
    @FXML private ComboBox<String> cSizeType;
    @FXML private ComboBox<String> cSize;
    @FXML private TextField tSize;
    
    private void initDimensions() {
        cSizeType.getItems().addAll("Zachovat poměr", "Zachovat rozměry");
        cSizeType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String old, String value) {
                if (value.equals(cSizeType.getItems().get(0))) {
                    cSize.setDisable(false); tSize.setDisable(false);
                } else {
                    cSize.setDisable(true); tSize.setDisable(true);
                }
            }
        });
        cSize.getSelectionModel().selectFirst();
        cSizeType.getSelectionModel().selectFirst();
    }
    
    private int getSize() {
        String text = tSize.getText().replaceAll("\\D+", "");
        int size;
        if (text.isEmpty()) {
            size = 60; tSize.setText("" + size);
        } else
            size = Integer.parseInt(text);
        return size;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Bod 3. - výstup">
    @FXML private RadioButton rOutputHTML;
    @FXML private RadioButton rOutputPicture;
    @FXML private RadioButton rImageJpg;
    @FXML private RadioButton rImagePng;
    
    public String getImageExtension() {
        if (rImageJpg.isSelected())
            return "jpg";
        else
            return "png";
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Bod 4. - vytvoření">
    @FXML private Button bCreate;
    @FXML private Label lCreateInfo;
    
    @FXML
    private void updateInfo() {
        lCreateInfo.setText((sourceFile != null && rOutputPicture.isSelected())
                ? "Obrázek bude uložen do původního adresáře jako: "
                        + NioUtil.removeExtension(sourceFile.getName())
                        + " - ascii." + getImageExtension()
                : null);
    }
    //</editor-fold>
    
    //</editor-fold>
    
    @FXML public void create() {
        SettingsController controller = App.getInstance().getSettigsController();
        GeneratorSettings settings = controller.getSettings();
        
        BufferedImage resized = resizeImage(sourceImage, settings);
        resized = BufferedImageUtil.fillTransparentPixels(resized,
                settings.getBackground());
        
        try {
            if (rOutputPicture.isSelected())
                generateImage(settings, resized);
            else
                generateHTML(settings, resized);
        } catch (Throwable e) {
            error(e);
        }
    }
    
    private void generateImage(GeneratorSettings settings, BufferedImage image)
            throws IOException {
        ImageGenerator generator = new ImageGenerator(settings);
        BufferedImage result = generator.create(image);

        String ext = getImageExtension();

        File save = new File(sourceFile.getParentFile(),
                NioUtil.removeExtension(sourceFile.getName())
                        + " - ascii." + ext);

        if (ext.equals("jpg")) result = BufferedImageUtil
                .fillTransparentPixels(result, settings.getBackground());
        
        ImageIO.write(result, ext, save);
        java.awt.Desktop.getDesktop().open(save);
            // může také vyhodit IOException, ale to je velmi nepravděpodobné
    }
    
    private void generateHTML(GeneratorSettings settings, BufferedImage image) {
        HtmlGenerator generator;
            
        if (settings.getColorSetting() == ColorSetting.COLORED)
            generator = new HtmlGenerator(settings,
                    new DefaultFormatterColored());
        else 
            generator = new HtmlGenerator(settings, new DefaultFormatter());

        String htmlAsciiArt = generator.create(image);
        String title = NioUtil.removeExtension(sourceFile.getName());
        Browser browser = new Browser(title);
        browser.loadContent(htmlAsciiArt);
        browser.show();
    }
    
    private BufferedImage resizeImage(BufferedImage image,
            GeneratorSettings settings) {
        
        int width = getSize(), height = getSize();
        AsciiArtMetrics metrics = new AsciiArtMetrics(settings, image);
        
        int selectedIndex = cSizeType.getSelectionModel().getSelectedIndex();
        if (selectedIndex == 0) {
            if (cSize.getSelectionModel().getSelectedIndex() == 0)
                width = (int)metrics.getWidth(height);
            else
                height = (int)metrics.getHeight(width);
        } else {
            Rectangle keepSize = metrics.keepSize();
            width = keepSize.width;
            height = keepSize.height;
        }
        
        return BufferedImageUtil.resize(image, width, height);
    }
    
    @FXML private void applyOnScreen() {
        // získání nastavení
        SettingsController controller = App.getInstance().getSettigsController();
        GeneratorSettings settings = controller.getSettings();
        
        // snímek obrazovky
        BufferedImage screenShot = new ScreenShot().createImage();
        
        // zmenšení screenshotu, aby byl výsledek správnou velikost
        AsciiArtMetrics metrics = new AsciiArtMetrics(settings, screenShot);
        Rectangle size = metrics.keepSize();
        int w = (int) size.getWidth();
        int h = (int) size.getHeight();
        BufferedImage resizedImage = BufferedImageUtil.resize(screenShot, w, h);
        
        // vytvoření ascii artu
        ImageGenerator generator = new ImageGenerator(settings);
        BufferedImage result = generator.create(resizedImage);
        
        // zobrazení
        FullScreenImage frame = new FullScreenImage(result,
                settings.getBackground());
        frame.setVisible(true);
    }
    
    private void error(Throwable t) {
        Stage stage = (Stage)lSource.getScene().getWindow();
        
        ExceptionDialog dialog = new ExceptionDialog(t);
        dialog.initOwner(stage);
        dialog.setTitle("Chyba");
        dialog.setHeaderText("Nastala chyba");
        dialog.setContentText("Došlo k neočekávané výjimce.");
        dialog.showAndWait();
    }
    
}