package cz.hartrik.asciiart.gen.chars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * <p>Počítá hodnotu znaku - množství černých pixelů.</p>
 * 
 * <p>Do výpočtu se zahrnuje i místo pod řádkou (např. patky u "pq") a místo nad
 * řádkou (např. u "ŇŤŘŽÝ") a to na základě informací z použitého fontu.
 * Šířka se získává ze vstupního znaku, a proto může být u proporciálních fontů
 * u různých znaků různý výsledný poměr.</p>
 * 
 * @version 2013-07-11
 * @author Patrik Harag
 */
public class CharValueCounter {
    
    private static final Color COLOR = Color.WHITE;
    
    private final char character;     // znak, který se bude "měřit"
    private final FontData fontData;  // informace o fontu
    
    public CharValueCounter(char character, Font font) {
        this(character, new FontData(character, font));
    }

    public CharValueCounter(char character, FontData fontData) {
        this.character = character;
        this.fontData = fontData;
    }
    
    /**
     * Spočítá počet pixelů, které znak zabírá.
     * 
     * @return počet pixelů
     */
    public int count() {
        BufferedImage image = new BufferedImage
                (fontData.getFontWidth(), fontData.getFontHeight(),
                BufferedImage.TYPE_BYTE_BINARY);
        
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        graphics.setFont(fontData.getFont());
        graphics.setColor(COLOR);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        
        int charPosY = fontData.getFontHeight() - fontData.getFontDescent() + 1;
        graphics.drawString(character + "", 0, charPosY);
        
        int black = countBlackPixels(image);
        
//        view(image);
//        System.out.println(character + " = " + black);
        return black;
    }
    
    private int countBlackPixels(BufferedImage image) {
        int pixels = 0;
        for (int i = 0; i < fontData.getFontHeight(); i++) {
            for (int j = 0; j < fontData.getFontWidth(); j++) {
                int rgb = image.getRGB(j, i);
                if (new Color(rgb).equals(COLOR)) pixels++;
            }
        }
        return pixels;
    }
    
    // ---------------------- TESTY -----------------------------
    public static void main(String[] args) {
        CharValueCounter bvc = new CharValueCounter
                ('Ň', new Font("Courier New", Font.PLAIN, 11));
    }
    
    private static void view(BufferedImage image) {
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new java.awt.FlowLayout());
        frame.getContentPane().add(
                new javax.swing.JLabel(
                        new javax.swing.ImageIcon(image)));
        frame.pack(); frame.setVisible(true);
    }
    
}