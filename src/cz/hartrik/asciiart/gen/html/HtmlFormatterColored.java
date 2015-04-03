package cz.hartrik.asciiart.gen.html;

import java.awt.Color;

/**
 * Rozhraní formátující barevný HTML výstup.
 *
 * @version 2013-07-10
 * @author Patrik Harag
 */
public interface HtmlFormatterColored extends HtmlFormatter {
    
    /**
     * Vrátí formátovaný, barevný znak.
     * 
     * @param character znak
     * @param color barva znaku
     * @return HTML
     */
    public abstract String nextChar(char character, Color color);
    
}