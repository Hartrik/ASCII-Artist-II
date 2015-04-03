
package cz.hartrik.asciiart.gen.html;

import cz.hartrik.asciiart.gen.GeneratorSettings;
import java.awt.Font;

/**
 * Výchozí formátovač jednobarevného HTML výstupu.
 *
 * @version 2013-10-27
 * @author Patrik Harag
 */
public class DefaultFormatter extends HtmlFormatterBase {

    private final EntityFilter filter;

    public DefaultFormatter() {
        filter = new EntityFilter();
        filter.replaceSpace(false);
    }
    
    @Override
    public String startDocument(String title, GeneratorSettings settings) {
        Font font = settings.getFont();
        
        return super.startDocument(title, settings) + "<pre><span style=\""
                    + "font-family: " + font.getFamily() + ";"        // font
                    + (font.isItalic() ? " font-style: italic;" : "") // kurzíva
                    + (font.isBold()   ? " font-weight: bold;"  : "") // tučné
                + "\">";
    }

    @Override
    public String endDocument() {
        return "<span></pre>" + super.endDocument();
    }
    
    @Override
    public String startLine() { return ""; }
    
    @Override
    public String endLine() { return ""; }
    
    @Override
    public String nextChar(char character) {
        return filter.apply(character);
    }
    
}