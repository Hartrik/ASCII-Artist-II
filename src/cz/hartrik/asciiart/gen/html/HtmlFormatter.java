package cz.hartrik.asciiart.gen.html;

import cz.hartrik.asciiart.gen.GeneratorSettings;

/**
 * Rozhraní formátující HTML výstup.
 *
 * @version 2013-07-10
 * @author Patrik Harag
 */
public interface HtmlFormatter {
    
    /**
     * Začátek dokumentu. Vytvoří se začátek HTML dokumentu, po něm už přijde
     * samotný obsah.
     * 
     * @param title nadpis dokumentu
     * @param settings nastavení generátoru
     * @return HTML
     */
    public abstract String startDocument(String title,
            GeneratorSettings settings);
    
    /**
     * Začátek nové řádky.
     * 
     * @return HTML
     */
    public abstract String startLine();
    
    /**
     * Další znak. (bez barvy)
     * 
     * @param character znak
     * @return HTML
     */
    public abstract String nextChar(char character);
    
    /**
     * Konec řádky.
     * 
     * @return HTML
     */
    public abstract String endLine();
    
    /**
     * Zakončení HTML dokumentu.
     * 
     * @return HTML
     */
    public abstract String endDocument();
    
}