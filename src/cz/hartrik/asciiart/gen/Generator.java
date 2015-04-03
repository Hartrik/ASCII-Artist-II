package cz.hartrik.asciiart.gen;

import cz.hartrik.asciiart.gen.chars.FontData;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Abstraktní třída generátoru.
 *
 * @version 2013-10-27
 * @author Patrik Harag
 * 
 * @param <OUTPUT> výstup generátoru
 */
public abstract class Generator<OUTPUT> {
    
    /** Obrázek, podle kterého se bude generovat výstup. */
    protected BufferedImage image;
    
    /** Nastavení generátoru. */
    protected GeneratorSettings settings;
    
    protected final FontData fontData;

    /**
     * Vytvoří instanci nového generátoru.
     * 
     * @param settings nastavení generátoru
     */
    public Generator(GeneratorSettings settings) {
        this.settings = settings;
        this.fontData = new FontData('M', settings.getFont());
    }
    
    public OUTPUT create(BufferedImage image) {
        this.image = image;
        
        start();
        return doFinal();
    }
    
    protected void start() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                nextPixel(color, j, i);
            }
        }
    };
    
    protected abstract void nextPixel(Color color, int x, int y);
    
    protected abstract OUTPUT doFinal();
    
}