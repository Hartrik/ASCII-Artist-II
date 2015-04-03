
package cz.hartrik.asciiart.gen.html;

/**
 * Nahrazuje vybrané znaky entitamy.
 *
 * @version 2013-10-29
 * @author Patrik Harag
 */
public class EntityFilter {
    
    private boolean space = true;
    private boolean basic = true;
    
    public String apply(char character) {
        switch (character) {
            case ' ': return space ? "&nbsp;" : " ";
            case '<': return basic ? "&lt;"   : "<";
            case '>': return basic ? "&gt;"   : ">";
            case '&': return basic ? "&amp;"  : "&";
            default : return character + "";
        }
    }
    
    public void replaceSpace(boolean bool) { space = bool; }
    public void replaceBasic(boolean bool) { basic = bool; }
    
}