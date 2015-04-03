package cz.hartrik.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Slouží k vytvoření seznamu dostupných neproporcionálních fontů.
 *
 * @version 2013-07-22
 * @author Patrik Harag
 */
public class MonospacedFonts {

    private MonospacedFonts() {}
    
    /**
     * Vytvoří seznam dostupných neproporcionálních písem.
     * 
     * @return seznam fontů
     */
    public static List<String> createList() {
        Font fonts[] = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getAllFonts();
        
        List<String> fontList = new ArrayList<>();
        
        FontRenderContext frc = new FontRenderContext(null,
                RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
                RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
        
        for (Font font : fonts) {
            Rectangle2D iBounds = font.getStringBounds("i", frc);
            Rectangle2D mBounds = font.getStringBounds("m", frc);
            
            if (iBounds.getWidth() == mBounds.getWidth())
                fontList.add(font.getName());
        }
        return fontList;
    }
    
    public static void main(String[] args) {
        StopWatch s = new StopWatch();
        s.start();
        
        createList();
        
        s.stop();
        System.out.println("s.getMili() = " + s.getMili());
    }
    
}