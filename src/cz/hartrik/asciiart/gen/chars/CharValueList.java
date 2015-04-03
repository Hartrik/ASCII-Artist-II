package cz.hartrik.asciiart.gen.chars;

import java.util.HashMap;
import java.util.Map;

/**
 * Seznam znaků a jejich hodnot.
 *
 * @version 2013-07-08
 * @author Patrik Harag
 */
public class CharValueList {
    
    private final Map<Integer, Character> charMap = new HashMap<>();
    private CharAndValue min = null;
    private CharAndValue max = null;
    
    /**
     * Přidá znak s hodnotou do seznamu.
     * 
     * @param character znak
     * @param value hodnota znaku
     */
    public void add(char character, int value) {
        charMap.put(value, character);
        
        if (max == null || value > max.getValue())
            max = new CharAndValue(value, character);
        
        if (min == null || value < min.getValue())
            min = new CharAndValue(value, character);
    }
    
    /**
     * Vrátí znak s určitou, nebo s nišší hodnotou.
     * 
     * @param value hledaná hodnota
     * @return znak
     */
    public char getChar(int value) {
        if (charMap.isEmpty())
            throw new IllegalStateException("Seznam je prázdný!");
        
        if (value >= max.getValue())
            return max.getCharacter();
        else if (value <= min.getValue())
            return min.getCharacter();
        
        return search(value);
    }
    
    private char search(int i) {
        Character get;
        do {
            get = charMap.get(i--);
        } while (get == null);
        return get;
    }

    /**
     * Vrátí znak a hodnotu znaku s nejvyšší hodnotou.
     * 
     * @return znak a hodnota s nejvyšší hodnotou
     */
    public CharAndValue getMax() {
        return max;
    }

    /**
     * Vrátí znak a hodnotu znaku s nejnišší hodnotou.
     * 
     * @return znak a hodnota s nejnišší hodnotou
     */
    public CharAndValue getMin() {
        return min;
    }

    protected Map<Integer, Character> getCharMap() {
        return charMap;
    }
    
}