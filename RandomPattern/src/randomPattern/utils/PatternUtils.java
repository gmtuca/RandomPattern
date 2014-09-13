package randomPattern.utils;

import randomPattern.utils.Utilities;

import java.util.*;

/**
 * Created by gmtuk on 30/08/2014.
 */
public abstract class PatternUtils {

    /**
     * Set of Characters which must be escaped in a regular expression:
     * \ [ ] ( ) { } ? - . $ | + ^ * &
     */
    private static final Set<Character> escapeSet = new HashSet<>();
    static {
        Utilities.addItemsToCollection(escapeSet, '\\', '[', ']', '(', ')', '{', '}', '?', '-', '.', '$', '|', '+', '^', '*', '&');
    }

    /**
     * Escapes a char by inserts a backslash ( \ ) if {@link @param c} is contained within the escapeSet
     * @param c
     * @return
     */
    public static String escape(char c){
        if(escapeSet.contains(c))
            return "\\"+c;
        else
            return ""+c;
    }

}
