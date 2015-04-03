package cz.hartrik.asciiart;

import cz.hartrik.asciiart.gen.GeneratorSettings;
import cz.hartrik.asciiart.gen.chars.FontData;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Obsahuje nástroje pro výpočet rozměrů obrázku, podle kterého se bude
 * generovat ASCII art (předloha). Bere v úvahu poměr stran vstupního obrázku,
 * poměr a rozměry fontu a velikost mezery mezi řádky.
 * 
 * @version 2013-10-23
 * @author Patrik Harag
 */
public class AsciiArtMetrics {
    
    private final GeneratorSettings settings;
    private final BufferedImage image;

    /**
     * @param settings nastavení generátoru
     * @param image obrázek, podle kterého se bude vytvářet ASCII art
     */
    public AsciiArtMetrics(GeneratorSettings settings, BufferedImage image) {
        this.settings = settings;
        this.image = image;
    }
    
    /**
     * Vypočítá šířku předlohy na základě fixní výšky.
     * 
     * @param height fixní výška
     * @return výsledná šířka
     */
    public double getWidth(int height) {
        if (height <= 0) throw new IllegalArgumentException("height < 1");
        
        double ratio = (double)image.getHeight() / height;
        return image.getWidth() / ratio * charRatio();
    }
    
    /**
     * Vypočítá výšku předlohy na základě fixní šířky.
     * 
     * @param width fixní šířka
     * @return výsledná výška
     */
    public double getHeight(int width) {
        if (width <= 0) throw new IllegalArgumentException("width < 1");
        
        double ratio = (double)image.getWidth() / width;
        return image.getHeight() / ratio / charRatio();
    }
    
    private double charRatio() {
        FontData font = getFontData();
        double fontRatio = (double)font.getFontHeight() / font.getFontWidth();
        double lineHeight = settings.getLineHeight() / 100d;
        
        return lineHeight * fontRatio;
    }
    
    /**
     * Vypočítá rozměry předlohy tak, aby měl výsledný ASCII art rozměry co
     * nejvíce shodné se vstupním obrázkem.
     * 
     * @return rozměry
     */
    public Rectangle keepSize() {
        FontData font = getFontData();
        double r = font.getFontHeight() * (settings.getLineHeight() / 100d);
        int height = (int)(image.getHeight() / r);
        int width = (int)getWidth(height);
        
        return new Rectangle(width, height);
    }
    
    private FontData getFontData() {
        return new FontData('M', settings.getFont());
    }
    
}