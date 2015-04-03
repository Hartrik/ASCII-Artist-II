package cz.hartrik.asciiartist.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Prázdné okno přes celou obrazovku.
 * 
 * @version 2013-06-23
 * @author Patrik Harag
 */
public class FullscreenFrame extends JFrame {
    private static final long serialVersionUID = 185564899684648L;
    
    public FullscreenFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        setType(Window.Type.UTILITY);
        setUndecorated(true);
        setAlwaysOnTop(true);
        
        // neviditelný kurzor
        Cursor cursor = getToolkit().createCustomCursor(new BufferedImage(1, 1,
                BufferedImage.TYPE_INT_ARGB), new Point(), null);
        setCursor(cursor);
        
        // ukončení po kliknutí
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClick();
            }
        });
    }
    
    protected void onClick() {}
    
}