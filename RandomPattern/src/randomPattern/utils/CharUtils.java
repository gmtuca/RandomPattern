package randomPattern.utils;

/**
 * Created by gmtuk on 23/08/2014.
 * @Author Arthur Ceccotti
 */
public abstract class CharUtils {

    /**
     * Returns a random char with ASCII value between mode.MIN_CHAR and mode.MAX_CHAR
     * @return
     */
    public static char randomChar(CharRange r){
        return (char) RussianRoulette.randomInRange(r.MIN_CHAR, r.MAX_CHAR);
    }

    /**
     * Selects a random char as specified by {@link #randomChar()}, which cannot be equal @link @param c}
     * @param c
     * @return
     */
    public static char randomCharExcept(CharRange r, char c){
        return (char)RussianRoulette.randomInRangeExcept(r.MIN_CHAR, r.MAX_CHAR, c);
    }

    /**
     * Returns a random char with ASCII value above {@link @param c} and below mode.MAX_CHAR
     * @param c Minimum char.
     * @return
     */
    public static char randomCharAbove(char c, int maxOffset){
        return (char)RussianRoulette.randomInRange((int)c, (int)c + maxOffset);
    }

    /**
     * Returns a random char with ASCII value below {@link @param c} and above mode.MIN_CHAR
     * @param c Minimum char.
     * @return
     */
    public static char randomCharBelow(char c, int maxOffset){
        return randomCharAbove(c, -maxOffset);
    }

    /**
     * @param length The length of the string to be produced
     * @return
     */
    public static String randomString(CharRange r, int length){
        if(length < 0)
            return null;

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < length; i++)
            stringBuilder.append(randomChar(r));

        return stringBuilder.toString();
    }

    /**
     * Inverts the case of a char. Upper - Lower
     * Returns the char itself in case of non-alphabetic parameter.
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
            return c;
    }

    /**
     * Returns a char whose ASCII value is {@link @param offset} away from {@link @param c}
     * @param c
     * @param offset
     * @return
     */
    public static char neighbour(CharRange r, char c, int offset){
        c += offset;

        return (char) Utilities.saturate(c, r.MIN_CHAR, r.MAX_CHAR);
    }

    /**
     * Generates a random char neighbour of {@param c} away at least {@param minOffset} and at most {@param maxOffset}.
     * Saturation will happen according to mode.MIN_CHAR and mode.MAX_CHAR.
     * @param c
     * @param minOffset
     * @param maxOffset
     * @return
     */
    public static char randomNeighbour(CharRange r, char c, int minOffset, int maxOffset){
        return (char)RussianRoulette.randomNear(c, minOffset, maxOffset, r.MIN_CHAR, r.MAX_CHAR);
    }
}