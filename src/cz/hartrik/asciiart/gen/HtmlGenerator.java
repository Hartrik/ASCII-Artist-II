package cz.hartrik.asciiart.gen;

import cz.hartrik.asciiart.gen.html.HtmlFormatter;
import cz.hartrik.asciiart.gen.html.DefaultFormatterColored;
import cz.hartrik.asciiart.gen.html.HtmlFormatterColored;
import cz.hartrik.asciiart.gen.html.HtmlOutput;
import cz.hartrik.asciiart.gen.html.HtmlOutputStringBuilder;
import java.awt.Color;

/**
 * Generátor ascii artu s výstupem do HTML.
 *
 * @version 2013-10-27
 * @author Patrik Harag
 */
public class HtmlGenerator extends Generator<String>{

    protected HtmlOutput output;
    protected HtmlFormatter formatter;
    private boolean colored;

    public HtmlGenerator(GeneratorSettings settings) {
        this(settings, new HtmlOutputStringBuilder());
    }
    
    public HtmlGenerator(GeneratorSettings settings, HtmlOutput output) {
        this(settings, output, new DefaultFormatterColored());
    }

    public HtmlGenerator(GeneratorSettings settings, HtmlFormatter formatter) {
        this(settings, new HtmlOutputStringBuilder(), formatter);
    }
    
    public HtmlGenerator(GeneratorSettings settings, HtmlOutput output,
            HtmlFormatter formatter) {
        
        super(settings);
        this.output = output;
        this.formatter = formatter;
    }
    
    @Override
    protected void start() {
        output.startDocument();
        output.writeLine(formatter.startDocument("title", settings));
        
        colored = (settings.getColorSetting() == ColorSetting.COLORED) &&
                (formatter instanceof HtmlFormatterColored);
        
        for (int i = 0; i < image.getHeight(); i++) {
            output.writeLine(formatter.startLine());
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                nextPixel(color, j, i);
            }
            output.write(formatter.endLine());
        }
    }

    @Override
    protected void nextPixel(Color color, int x, int y) {
        char character = settings.getCharSetting().getChar(color, settings);
        
        if (colored) {
            Color col = settings.getColorSetting().editColor(color, settings);
            String nextChar = 
                    ((HtmlFormatterColored)formatter).nextChar(character, col);
            output.write(nextChar);
        } else
            output.write(formatter.nextChar(character));
    }

    @Override
    protected String doFinal() {
        output.writeLine(formatter.endDocument());
        output.endDocument();
        return output.toString();
    }
    
}