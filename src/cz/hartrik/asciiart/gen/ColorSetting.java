package cz.hartrik.asciiart.gen;

import java.awt.Color;

/**
 * Nastavení barev znaků.
 *
 * @version 2013-07-11
 * @author Patrik Harag
 */
public enum ColorSetting {
    
    /**
     * Barevný výstup.
     */
    COLORED {
        @Override
        public Color editColor(Color color, GeneratorSettings settings) {
            return color;
        }
    },
    
    /**
     * Výstup v odstínu šedi.
     */
    GRAYED {
        @Override
        public Color editColor(Color color, GeneratorSettings settings) {
            int gray = (int)ColorUtils.getGrayscale(color);
            return new Color(gray, gray, gray);
        }
    },
    
    /**
     * Jednobarevný výstup.
     */
    STATIC {
        @Override
        public Color editColor(Color color, GeneratorSettings settings) {
            return settings.getForeground();
        }
    };
    
    public abstract Color editColor(Color color, GeneratorSettings settings);
    
}