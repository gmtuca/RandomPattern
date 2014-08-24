import java.util.*;

/**
 * Created by gmtuk on 23/08/2014.
 * @Author Arthur Ceccotti
 */
public class Utils {
    public static final Random random = new Random();

    public static final int MIN_CHAR = 33;
    public static final int MAX_CHAR = 126;

    /**
     *
     * @param min Minimum number.
     * @param max Maximum number.
     * @return
     */
    public static int randomInRange(int min, int max) {
        return random.nextInt((max-min)+1)+min;
    }

    /**
     * Returns true or false according to fate. The odds are set by you.
     * @param successPercentage The % chance of returning true.
     * @return
     */
    public static boolean luckyDay(int successPercentage){
        return successPercentage >= randomInRange(1, 100);
    }

    /**
     * Returns a random char with ASCII value between {@link #MIN_CHAR} and {@link #MAX_CHAR}
     * @return
     */
    public static char randomChar(){
        return (char)randomInRange(MIN_CHAR, MAX_CHAR);
    }

    /**
     * Returns a random char with ASCII value above {@link @param c} and below {@link #MAX_CHAR}
     * @param c Minimum char.
     * @return
     */
    public static char randomCharAbove(char c){
        return (char)randomInRange((int)c, MAX_CHAR);
    }

    private static final Set<Character> escapeSet = new HashSet<Character>();
    static {
        addCharsToSet(escapeSet, '\\', '[', ']', '(', ')', '{', '}', '?', '-', '.', '$', '|', '+', '^', '*', '&');
    }

    private static final Set<Character> quantifierSet = new HashSet<Character>();
    static {
        addCharsToSet(escapeSet, '*', '+', '?');
    }

    public static boolean isQuantifier(char c){
        return quantifierSet.contains(c);
    }

    public static boolean isQuantifier(String p){
        if(p == null || p.equals("") || p.length() > 1)
            return false;

        return quantifierSet.contains(p.charAt(0));
    }

    /**
     * Selects a random item within a List.
     * @param list List of items
     * @param <T> Type of item
     * @return
     */
    public static <T> T randomItem(List<T> list){
        return list.get(randomInRange(0, list.size()-1));
    }


    private static void addCharsToSet(Set<Character> set, char... chars){
        for(char c : chars)
            set.add(new Character(c));
    }

    public static <T> void addItemsToList(List<T> list, T... items){
        if(list == null)
            return;

        for(T t : items)
            list.add(t);
    }

    /**
     * Inverts the case of a char. Upper - Lower
     * Returns '\0' in case of non-alphabetic parameter.
     * @param c
     * @return
     */
    public static char toOtherCase(char c){
        if(Character.isAlphabetic(c)) {
            if (Character.isLowerCase(c))
                return Character.toUpperCase(c);
            else
                return Character.toLowerCase(c);
        }
        else
            return '\0';
    }

    /**
     * Selects a random char as specified by {@link #randomChar()}, which cannot be equal @link @param c}
     * @param c
     * @return
     */
    public static char randomCharNot(char c){
        char randomChar;

        do{
            randomChar = Utils.randomChar();
        }while (randomChar == c);

        return randomChar;
    }

    /**
     * Returns a char whose ASCII value is {@link @param offset} away from {@link @param c}
     * @param c
     * @param offset
     * @return
     */
    public static char neighbour(char c, int offset){
        return (char)(c + offset);
    }

    /**
     * Escapes a char by inserts a backslash ( \ ) if {@link @param c} is one of
     * \ [ ] ( ) { } ? - . $ | + ^ * &
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