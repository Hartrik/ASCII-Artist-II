package cz.hartrik.asciiart.gen.chars;

import java.awt.Font;

/**
 * Obsahuje užitečná statická data.
 *
 * @version 2013-07-08
 * @author Patrik Harag
 */
public class Data {
    
    public static final String ASCII = 
            "!\"#$%&'()*+,-./0123456789:;<=>?@"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]`"
            + "abcdefghijklmnopqrstuvwxyz{|}~";
    
    public static final String WINDOWS_1250 =
            "!\"#$%&'()*+,-./0123456789:;<=>?@"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`"
            + "abcdefghijklmnopqrstuvwxyz{|}~"
            + "¤¦§¨©«¬S®°±´µ¶·¸»ÁÂÄÇÉËÍÎÓÔÖ×"
            + "ÚÜÝßáâäçéëíîóôö÷úüýĂăĄąĆćČčĎďĐđĘęĚěĹĺĽľŁłŃńŇňŐőŔŕŘř"
            + "ŚśŞşŠšŢţŤťŮůŰűŹźŻżŽžˇ˘˙˛˝–—‘’‚“”„†‡•…‰‹›€™";
    
    public static final String CZECH_CHARS = "ěščřžýáíéňťůúĚŠČŘŽÝÁÍÉŇŤŮÚ";
    
    public static final Font DEFAULT_FONT
            = new Font("Courier New", Font.PLAIN, 12);
    
}