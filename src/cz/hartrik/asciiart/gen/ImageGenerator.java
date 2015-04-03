package cz.hartrik.asciiart.gen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Generátor ASCII artu s výstupem do obrázku.
 *
 * @version 2013-07-11
 * @author Patrik Harag
 */
public class ImageGenerator extends Generator<BufferedImage>{

    private BufferedImage output;
    
    private Graphics graphics;
    private boolean createNewImage = true;
    private final double lineHeight;
    
    public ImageGenerator(GeneratorSettings settings) {
        super(settings);
        this.lineHeight = settings.getLineHeight() / 100d;
    }

    public BufferedImage create(BufferedImage image, BufferedImage output) {
        this.output = output;
        this.createNewImage = false;
        return super.create(image);
    }
    
    @Override
    protected void start() {
        if (createNewImage) createImage();
        createGraphics();
        super.start();
        graphics.dispose();
    }
    
    @Override
    protected void nextPixel(Color color, int x, int y) {
        int xPos = x * fontData.getFontWidth();
        int yPos = (y + 1) * fontData.getFontHeight() - fontData.getFontDescent();
            yPos = (int)(yPos * lineHeight);
        
        graphics.setColor(settings.getColorSetting().editColor(color, settings));
        graphics.drawString(settings.getCharSetting().getChar(color, settings)
                + "", xPos, yPos);
    }
    
    protected void createImage() {
        int width = image.getWidth() * fontData.getFontWidth();
        int height = (int)((image.getHeight() * fontData.getFontHeight())
                * lineHeight);
        output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = output.getGraphics();
        graphics.setColor(settings.getBackground());
        graphics.fillRect(0, 0, width, height);
    }
    
    protected void createGraphics() {
        graphics = output.getGraphics();
        graphics.setFont(fontData.getFont());
        graphics.setColor(settings.getForeground());
    }
    
    @Override
    protected BufferedImage doFinal() {
        return output;
    }
    
}