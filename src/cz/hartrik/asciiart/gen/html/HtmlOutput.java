
package cz.hartrik.asciiart.gen.html;

/**
 * Výstup generátoru.
 *
 * @version 2013-10-25
 * @author Patrik Harag
 */
public interface HtmlOutput {
    
    /** Vytvoří instance, otevře proudy... */
    public abstract void startDocument();
    
    public abstract void write(String text);
    
    public abstract void writeLine(String line);
    
    /** Ukončí zápis, zavře proudy... */
    public abstract void endDocument();
    
}