
package cz.hartrik.asciiartist.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Okno s obrázkem.
 *
 * @version 2013-10-23
 * @author Patrik Harag
 */
public class FullScreenImage extends FullscreenFrame {
    private static final long serialVersionUID = 641314848544645478L;

    private Image image;
    private boolean center = true;

    public FullScreenImage(Image image) {
        this(image, Color.WHITE);
    }
    
    public FullScreenImage(Image image, Color background) {
        super();
        this.image = image;
        setBackground(background);
    }

    @Override
    protected void onClick() {
        this.dispose();
    }

    @Override
    public void paint(Graphics g) {
        if (getImage().getWidth(null) < getWidth() ||
                getImage().getHeight(null) < getHeight()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
        if (isCenter()) {
            int x = (getWidth() - image.getWidth(null)) / 2;
            int y = (getHeight() - image.getHeight(null)) / 2;
            g.drawImage(getImage(), x, y, null);
        } else
            g.drawImage(getImage(), 0, 0, null);
    }

    public Image getImage() { return image; }
    public boolean isCenter() { return center; }
    public void setImage(Image image) { this.image = image; }
    public void setCenter(boolean center) { this.center = center; }

}