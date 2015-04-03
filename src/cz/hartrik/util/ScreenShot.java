package cz.hartrik.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 * Vytvoří screenshot.
 * 
 * @version 2013-06-23
 * @author Patrik Harag
 */
public class ScreenShot {
    
    private Robot capturer;
    
    public ScreenShot() {
        try {
            capturer = new Robot();
        } catch (AWTException ex) {}
    }
    
    public BufferedImage createImage() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rectangle = new Rectangle
                (0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());  
        BufferedImage image = capturer.createScreenCapture(rectangle);  
        return image;  
    }
    
}