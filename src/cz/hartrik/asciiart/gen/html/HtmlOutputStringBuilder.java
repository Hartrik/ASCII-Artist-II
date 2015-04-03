
package cz.hartrik.asciiart.gen.html;

/**
 * Výstup do string builderu.
 *
 * @version 2013-10-27
 * @author Patrik Harag
 */
public class HtmlOutputStringBuilder implements HtmlOutput {

    protected StringBuilder builder;
    
    @Override
    public void startDocument() {
        builder = new StringBuilder();
    }

    @Override
    public void write(String text) {
        builder.append(text);
    }

    @Override
    public void writeLine(String line) {
        builder.append(System.lineSeparator()).append(line);
    }

    @Override
    public void endDocument() {}

    @Override
    public String toString() {
        return builder.toString();
    }
    
}