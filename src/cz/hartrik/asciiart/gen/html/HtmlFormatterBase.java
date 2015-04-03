package cz.hartrik.asciiart.gen.html;

import cz.hartrik.asciiart.gen.GeneratorSettings;

/**
 * Základ HTML dokumentu.
 *
 * @version 2013-07-10
 * @author Patrik Harag
 */
public abstract class HtmlFormatterBase implements HtmlFormatter {

    @Override
    public String startDocument(String title, GeneratorSettings settings) {
        String foreground = HtmlUtil.toHexColor(settings.getForeground());
        String background = HtmlUtil.toHexColor(settings.getBackground());
        
        int lineHeight = (int)(settings.getLineHeight() * 1.20);
        // nevím proč, ale v HTML je jinak obrázek trochu placatý
        
        return "<html>"
                + "<head>"
                    + "<meta http-equiv=\"Content-Type\" "
                            + "content=\"text/html; charset=UTF-8\">"
                    + "<title>" + title + "</title>"
                + "</head>"
                + "<body style=\""
                        + "padding: 20px 20px 20px 20px; "
                        + "line-height: " + lineHeight + "%; "
                        + "background-color: " + background + "; "
                        + "color: " + foreground + "; "
                        + "font-size: " + settings.getFont().getSize() + "px;"
                + "\">";
    }

    @Override
    public String endDocument() {
        return "</body></html>";
    }

}