package cz.hartrik.asciiart.gen.html;

import java.awt.Color;

/**
 * Nástroje usnadňující práci s HTML výstupem.
 * 
 * @version 2013-07-10
 * @author Patrik Harag
 */
public class HtmlUtil {

    private HtmlUtil() {}
    
    /**
     * Převede barvu do formátu vhodného k zápisu do html.
     * Bez alpha složky barvy.
     * 
     * @param color barva
     * @return barva v šestnáctkové soustavě včetně "#"
     */
    public static String toHexColor(Color color) {
        String hex = Integer.toHexString(color.getRGB()).substring(2);
        return "#" + hex;
    }
    
}