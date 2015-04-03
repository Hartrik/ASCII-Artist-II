package cz.hartrik.asciiart.gen;

import java.awt.Color;

/**
 * Nastavení dosazování znaků za barevné body.
 *
 * @version 2013-10-22
 * @author Patrik Harag
 */
public enum CharSetting {
    
    /**
     * Automatický výběr znaků na základě tmavosti barvy.
     * Čím tmavší barvy, tím objemnější znaky.
     */
    AUTO {
        @Override
        public char getChar(Color color, GeneratorSettings settings) {
            int gray = (int)ColorUtils.getGrayscale(color);
            gray = (int)ColorUtils.reverseGrayscale(gray);
            return returnCharacter(settings, gray);
        }
    },
    
    /**
     * Automatický výběr znaků na základě tmavosti barvy.
     * Čím tmavší barvy, tím řidší znaky.
     */
    AUTO_REVERSE {
        @Override
        public char getChar(Color color, GeneratorSettings settings) {
            int gray = (int)ColorUtils.getGrayscale(color);
            return returnCharacter(settings, gray);
        }
    },
    
    /**
     * Jeden znak pro všechny barvy, bez ohedu na jejich tmavost.
     */
    STATIC {
        @Override
        public char getChar(Color color, GeneratorSettings settings) {
            int gray = (int)ColorUtils.getGrayscale(color);
            if (isSpace(gray, settings)) return ' ';
            
            return settings.getCharacter();
        }
    };
    
    // ----------------- METODY --------------------
    
    /**
     * Podle nastanení analizuje barvu a vrátí odpovádající znak.
     * 
     * @param color barva pixelu
     * @param settings nastanení generátoru
     * @return znak odpovídající barvě pixelu,
     *         řetězec je to kvůli podpoře entit v HTML
     */
    public abstract char getChar(Color color, GeneratorSettings settings);
    
    protected final char returnCharacter(GeneratorSettings settings, int gray) {
        if (isSpace(gray, settings)) return ' ';
        char character = settings.getCharList().getChar(gray);
        return character;
    }
    
    protected final boolean isSpace(int value, GeneratorSettings settings) {
        return value <= settings.getSpaceLevel();
    }
    
}