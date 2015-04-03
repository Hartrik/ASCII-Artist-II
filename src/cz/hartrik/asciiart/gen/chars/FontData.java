package cz.hartrik.asciiart.gen.chars;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Zjišťuje a uchovává data o fontu.
 *
 * @version 2013-10-22
 * @author Patrik Harag
 */
public class FontData {
    
    private final Font font;
    private final int fontDescent; // výška znaku od linky dolu
    private final int fontHeight;
    private final int fontWidth;

    public FontData(char character, Font font) {
        this.font = font;
        
        Graphics graphics = createGraphics();
        graphics.setFont(font);
        FontMetrics metrics = graphics.getFontMetrics();
        fontHeight  = metrics.getHeight();
        fontDescent = metrics.getMaxDescent();
        fontWidth   = metrics.charWidth(character);
        graphics.dispose();
    }
    
    private Graphics createGraphics() {
        BufferedImage temp =
                new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_BINARY);
        Graphics graphics = temp.getGraphics();
        return graphics;
    }

    public Font getFont()        { return font; }
    public int  getFontWidth()   { return fontWidth; }
    public int  getFontHeight()  { return fontHeight; }
    public int  getFontDescent() { return fontDescent; }
    
}