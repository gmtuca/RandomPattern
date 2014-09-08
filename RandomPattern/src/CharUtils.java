/**
 * Created by gmtuk on 23/08/2014.
 * @Author Arthur Ceccotti
 */
enum CharRange {
    ALL_PRINTABLE       (32,0xFFFF),
    HUMAN_FRIENDLY      (48,122),
    LATIN_PRINTABLE     (32,126),
    UPPER_CASE          (65,90),
    LOWER_CASE          (97,122),
    DIGITS              (48,57);

    final int MIN_CHAR, MAX_CHAR;

    CharRange(int MIN_CHAR, int MAX_CHAR) {
        this.MIN_CHAR = MIN_CHAR;
        this.MAX_CHAR = MAX_CHAR;
    }
}

abstract class CharUtils {

    /**
     * Represents the current mode of this utils class. Defaulted to Mode.LATIN_PRINTABLE
     */
    private static CharRange charRange = CharRange.LATIN_PRINTABLE;

    protected static CharRange getCharRange() {
        return charRange;
    }
    protected static void setCharRange(CharRange charRange) {
        CharUtils.charRange = charRange;
    }

    /**
     * Returns true if the char is valid within the defined CharRange, false otherwise
     * @param c
     * @return
     */
    protected static boolean charIsInRange(char c){
        return c >= charRange.MIN_CHAR && c <= charRange.MAX_CHAR;
    }

    /**
     * Returns a random char with ASCII value between mode.MIN_CHAR and mode.MAX_CHAR
     * @return
     */
    protected static char randomChar(){
        return (char)RussianRoulette.randomInRange(charRange.MIN_CHAR, charRange.MAX_CHAR);
    }

    /**
     * Selects a random char as specified by {@link #randomChar()}, which cannot be equal @link @param c}
     * @param c
     * @return
     */
    protected static char randomCharExcept(char c){
        return (char)RussianRoulette.randomInRangeExcept(charRange.MIN_CHAR, charRange.MAX_CHAR, c);
    }

    /**
     * Returns a random char with ASCII value above {@link @param c} and below mode.MAX_CHAR
     * @param c Minimum char.
     * @return
     */
    protected static char randomCharAbove(char c){
        return (char)RussianRoulette.randomInRange((int)c, charRange.MAX_CHAR);
    }

    /**
     * Returns a random char with ASCII value below {@link @param c} and above mode.MIN_CHAR
     * @param c Minimum char.
     * @return
     */
    protected static char randomCharBelow(char c){
        return (char)RussianRoulette.randomInRange(charRange.MIN_CHAR, (int)c);
    }

    /**
     * @param length The length of the string to be produced
     * @return
     */
    public static String randomString(int length){
        if(length < 0)
            return null;

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < length; i++)
            stringBuilder.append(randomChar());

        return stringBuilder.toString();
    }

    /**
     * Inverts the case of a char. Upper - Lower
     * Returns the char itself in case of non-alphabetic parameter.
     * @param c
     * @return
     */
    protected static char toOtherCase(char c){
        if(Character.isAlphabetic(c)) {
            if (Character.isLowerCase(c))
                return Character.toUpperCase(c);
            else
                return Character.toLowerCase(c);
        }
        else
            return c;
    }

    /**
     * Returns a char whose ASCII value is {@link @param offset} away from {@link @param c}
     * @param c
     * @param offset
     * @return
     */
    protected static char neighbour(char c, int offset){
        c += offset;

        if(c < charRange.MIN_CHAR)
            return (char) charRange.MIN_CHAR;
        if(c > charRange.MAX_CHAR)
            return (char) charRange.MAX_CHAR;

        return c;
    }

    /**
     * Generates a random char neighbour of {@param c} away at least {@param minOffset} and at most {@param maxOffset}.
     * Saturation will happen according to mode.MIN_CHAR and mode.MAX_CHAR.
     * @param c
     * @param minOffset
     * @param maxOffset
     * @return
     */
    protected static char randomNeighbour(char c, int minOffset, int maxOffset){
        return CharUtils.neighbour(c, RussianRoulette.randomInRange(minOffset, maxOffset));
    }
}