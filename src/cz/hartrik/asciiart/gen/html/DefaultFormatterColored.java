
package cz.hartrik.asciiart.gen.html;

import java.awt.Color;

/**
 * Výchozí formátovač pro barevný HTML výstupu.
 *
 * @version 2013-11-07
 * @author Patrik Harag
 */
public class DefaultFormatterColored extends DefaultFormatter implements
        HtmlFormatterColored {

    protected static final String END_TAG = "</font>";
    
    private Color lastColor;
    private boolean isOpen = false;
    private final EntityFilter filter;

    public DefaultFormatterColored() {
        filter = new EntityFilter();
        filter.replaceSpace(false);
    }
    
    @Override
    public String startLine() { return ""; }
    
    @Override
    public String endLine() {
        if (isOpen) { isOpen = false;  return END_TAG; }
        return "";
    }
    
    @Override
    public String nextChar(char character, Color color) {
        if (isOpen) 
            if (lastColor.equals(color))
                return toEntity(character);
            else
                return END_TAG + startTag(character, color);
        else
            return startTag(character, color);
    }
    
    // ----- soukromé metody
    
    private String startTag(char character, Color color) {
        if (character == ' ') {
            isOpen = false;    // mezery se nebarví
            return toEntity(character);
        }
        
        isOpen = true; lastColor = color;
        return "<font color=\"" + HtmlUtil.toHexColor(color) + "\">"
                + toEntity(character);
    }
    
    private String toEntity(char character) {
        return filter.apply(character);
    }
    
}