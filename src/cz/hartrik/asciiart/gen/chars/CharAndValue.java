package cz.hartrik.asciiart.gen.chars;

/**
 * Drží pohromadě znak a jeho hodnotu.
 * Slouží pouze jako přepravka.
 *
 * @version 2013-07-05
 * @author Patrik Harag
 */
public class CharAndValue {
    
    private final int value;
    private final char character;

    public CharAndValue(int value, char character) {
        this.value = value;
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    public int getValue() {
        return value;
    }
    
}