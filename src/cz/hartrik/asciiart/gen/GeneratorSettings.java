package cz.hartrik.asciiart.gen;

import cz.hartrik.asciiart.gen.chars.CharValueList;
import java.awt.Color;
import java.awt.Font;

/**
 * Nastavení generátoru.
 *
 * @version 2013-10-22
 * @author Patrik Harag
 */
public class GeneratorSettings {
    
    private final Font font;                 // použitý font
    private Color background = Color.WHITE;  // pozadí obrázku
    private Color foreground = Color.BLACK;  // popředí obrázku - nemusí být použito
    private char character = 'X';            // pokud má být jen z jednoho znaku
    private final CharValueList charList;    // seznam použitelných znaků a jejich hodnot
    
    private int lineHeight = 100;  // výška řádek v %, 100% = default
    private int spaceLevel = 25;   // úroveň mezer - pod jakou úrovní šedé se už
                                   //   znaky nebudou zobrazovat (mezery)
    
    private final ColorSetting colorSetting;  // nastavení barev
    private final CharSetting charSetting;    // nastavení znaků
    
    public GeneratorSettings(Font font, CharValueList charList,
            ColorSetting colorSetting, CharSetting charSetting) {
        
        this.font = font;
        this.charList = charList;
        this.colorSetting = colorSetting;
        this.charSetting = charSetting;
    }
    
    public Font getFont() {
        return font;
    }

    // pozadí, popředí
    public void setBackground(Color bg) { this.background = bg; }
    public void setForeground(Color fg) { this.foreground = fg; }
    public Color getForeground() { return foreground; }
    public Color getBackground() { return background; }

    // nastavení znaků, barev
    public ColorSetting getColorSetting() { return colorSetting; }
    public CharSetting  getCharSetting()  { return charSetting; }

    public void setCharacter(char character) { this.character = character; }
    public char getCharacter() { return character; }

    public CharValueList getCharList() { return charList; }
    
    public void setLineHeight(int height) { this.lineHeight = height; }

    public int getLineHeight() { return lineHeight; }
    
    public void setSpaceLevel(int spaceLevel) { this.spaceLevel = spaceLevel; }
    public int getSpaceLevel() { return spaceLevel; }
    
}