package cz.hartrik.asciiart.gen.chars;

import java.awt.Font;
import java.util.Map;

/**
 * Slouží k vytvoření seznamu znaků a jejich hodnot.
 * Všechny hodnoty jsou vynásobeny tak, aby odpovídaly stupnici šedi.
 *
 * @version 2014-02-09
 * @author Patrik Harag
 */
public class ListCreator {

    private final char[] characters;
    private final Font font;

    /**
     * Vytvoří instanci na základě řetězce znaků, ze kterých se bude vytvářet
     * seznam a fontu.
     * 
     * @param characters řetězec znaků
     * @param font 
     */
    public ListCreator(String characters, Font font) {
        this(characters.toCharArray(), font);
    }
    
    /**
     * Vytvoří instanci na základě pole znaků, ze kterých se bude vytvářet
     * seznam a fontu.
     * 
     * @param characters pole znaků
     * @param font 
     */
    public ListCreator(char[] characters, Font font) {
        this.characters = characters;
        this.font = font;
        if (characters.length == 0)
            throw new IllegalArgumentException("Znaková sada nebyla zadána!");
    }
    
    /**
     * Vytvoří seznam znaků s jejich hodnotami ve vyoičteném poměru.
     * 
     * @return seznam
     */
    public CharValueList create() {
        CharValueList temp = new CharValueList();
        
        for (char c : characters) {
            CharValueCounter counter = new CharValueCounter(c, font);
            int result = counter.count();
            temp.add(c, result);
        }

        CharValueList result = new CharValueList();
        double rat = 250d / temp.getMax().getValue();  // trochu méně než 256
        
        Map<Integer, Character> charMap = temp.getCharMap();
        for (Map.Entry<Integer, Character> pairs : charMap.entrySet()) {
            int value = (int)Math.round(pairs.getKey() * rat);
            result.add(pairs.getValue(), value);
        }
        return result;
    }
    
}