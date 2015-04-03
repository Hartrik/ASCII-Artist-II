package cz.hartrik.asciiartist.gui;

import cz.hartrik.util.AwtConverter;
import cz.hartrik.util.MonospacedFonts;
import cz.hartrik.asciiartist.App;
import cz.hartrik.asciiart.gen.*;
import cz.hartrik.asciiart.gen.chars.*;
import java.awt.Font;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

/**
 * Controller k accordionu s nastavením.
 * 
 * @version 2015-04-03
 * @author Patrik Harag
 */
public class SettingsController implements Initializable {
    
    private static final String DEFAULT_FONT = "Courier New";
    
    @FXML private Accordion accordion;
    
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle rb) {
        App.getInstance().setSettigsController(this);
        initBasicSettings();
        initCharSettings();
        initCharset();
        
        accordion.setExpandedPane(accordion.getPanes().get(0));
    }
    
    public GeneratorSettings getSettings() {
        Font font = getFont();
        CharValueList charList = getCharValueList(font);
        return getSettings(font, charList);
    }
    
    public GeneratorSettings getSettings(Font font, CharValueList charList) {
        CharSetting charSetting = getCharSetting();
        ColorSetting colorSetting = getColorSetting();
        
        GeneratorSettings settings =
                new GeneratorSettings(font, charList, colorSetting, charSetting);
        
        String character = tChar.getText().replaceAll("\\s", "");
        if (character.length() > 0) settings.setCharacter(character.charAt(0));
        settings.setBackground(AwtConverter.convertColor(pBackground.getValue()));
        settings.setForeground(AwtConverter.convertColor(pForeground.getValue()));
        settings.setSpaceLevel((int)sSpaceLevel.getValue());
        
        int sliderValue = (int)sLineHeight.getValue();
        if (sliderValue == 0) sliderValue = 1;
        settings.setLineHeight(sliderValue);
        
        return settings;
    }
    
    // --- Obsluha komponent

    //<editor-fold defaultstate="collapsed" desc="Panel - Základní nastavení">
    // znaky
    @FXML private RadioButton rCharsAuto;
    @FXML private RadioButton rCharsFixed;
    @FXML private CheckBox cReversePalette;
    @FXML private TextField tChar;
    
    // pozadí
    @FXML private ColorPicker pBackground;
    
    // popředí
    @FXML private ColorPicker pForeground;
    @FXML private RadioButton rColorAuto;
    @FXML private RadioButton rColorFixed;
    
    private void initBasicSettings() {
        pForeground.setValue(Color.BLACK);
    }
    
    public CharSetting getCharSetting() {
        if (rCharsAuto.isSelected()) {
            if (cReversePalette.isSelected())
                return CharSetting.AUTO_REVERSE;
            else
                return CharSetting.AUTO;
        } else
            return CharSetting.STATIC;
    }
    
    public ColorSetting getColorSetting() {
        if (rColorAuto.isSelected())
            return ColorSetting.COLORED;
        else
            return ColorSetting.STATIC;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Panel - Znaková sada">
    @FXML private MenuButton mbAddChars;
    @FXML private TextArea taUserCharset;
    
    @FXML private RadioButton rCharsetAscii;
    @FXML private RadioButton rCharsetWindows;
    @FXML private RadioButton rCharsetOwn;
    
    private void initCharset() {
        mbAddChars.getItems().clear();
        
        MenuItem iAscii = new MenuItem("ASCII");
        iAscii.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { addChars(Data.ASCII); }
        });
        
        MenuItem iWindows = new MenuItem("Windows 1250");
        iWindows.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { addChars(Data.WINDOWS_1250); }
        });
        
        MenuItem iCzech = new MenuItem("Česká diakritika");
        iCzech.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { addChars(Data.CZECH_CHARS); }
        });
        
        mbAddChars.getItems().addAll(iAscii, iWindows, iCzech);
    }
    
    private void addChars(String chars) {
        if (!taUserCharset.getText().isEmpty())
            taUserCharset.appendText(System.lineSeparator());
        taUserCharset.appendText(chars);
    }
    
    public CharValueList getCharValueList(Font font) {
        String charset;
        if (rCharsetAscii.isSelected()) {
            charset = Data.ASCII;
        } else if (rCharsetWindows.isSelected()) {
            charset = Data.WINDOWS_1250;
        } else {
            charset = taUserCharset.getText().replaceAll("\\s","");
            if (charset.isEmpty()) {
                rCharsetAscii.setSelected(true);
                return getCharValueList(font);
            }
        }
        ListCreator creator = new ListCreator(charset, font);
        return creator.create();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Panel - Nastavení znaků">
    @FXML private ComboBox<String> cFontType;
    @FXML private ComboBox<Integer> cFontSize;
    
    @FXML private Slider sLineHeight;
    @FXML private Slider sSpaceLevel;
    
    private void initCharSettings() {
        cFontSize.getSelectionModel().select(new Integer(12));
        cFontType.getSelectionModel().select(DEFAULT_FONT);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                List<String> fonts = MonospacedFonts.createList();
                cFontType.getItems().addAll(FXCollections.observableArrayList(fonts));
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
    
    public Font getFont() {
        String type = cFontType.getValue();
        Integer value = cFontSize.getValue();
        return new Font(type, Font.PLAIN, value);
    }
    //</editor-fold>
    
}