package cz.hartrik.util;

import java.awt.Color;

/**
 * Nástroje pro konverzi objektů mezi frameworky.
 *
 * @author Patrik Harag
 */
public class AwtConverter {

    private AwtConverter() {}
    
    /**
     * Převede instanci barvy z formátu JavaFX na barvu formátu AWT.
     * 
     * @param color barva ve formátu JavaFX
     * @return barva ve formátu AWT
     */
    public static java.awt.Color convertColor(javafx.scene.paint.Color color) {
        return new Color(
                (float)color.getRed(),
                (float)color.getGreen(),
                (float)color.getBlue(),
                (float)color.getOpacity());
    }
    
}