package cz.hartrik.asciiart.gen;

import java.awt.Color;

/**
 * Nástroje pro práci s barvami.
 *
 * @version 2013-07-11
 * @author Patrik Harag
 */
public class ColorUtils {
    
    /**
     * Převeda barvu do stupně šedi.<br/>
     * více informací: 
     * http://en.wikipedia.org/wiki/Grayscale#Converting_color_to_grayscale
     * 
     * @param color barva
     * @return hodnota 0 - 255
     */
    public static double getGrayscale(Color color) {
        return (color.getRed()   * 0.299) +
               (color.getGreen() * 0.587) +
               (color.getBlue()  * 0.114);
    }
    
    /**
     * Převrátí hodnotu šedi.
     * 
     * @param value hodnota šedi
     * @return převrácená hodnota
     */
    public static double reverseGrayscale(double value) {
        return Math.abs(value - 255);
    }
    
}