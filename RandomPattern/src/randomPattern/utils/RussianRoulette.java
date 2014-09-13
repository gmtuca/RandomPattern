package randomPattern.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by gmtuk on 30/08/2014.
 */
public class RussianRoulette {
    private static final Random random = new Random();

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static int randomInRange(int min, int max) {
        if(min > max)
            throw new IllegalArgumentException("Cannot generate random number from range where min > max.");

        return random.nextInt(max-min+1)+min;
    }

    /**
     * Generates a random number between {@param min} and {@param max} which is not equal to {@param exception}
     * @param min
     * @param max
     * @param exception
     * @return
     */
    protected static int randomInRangeExcept(int min, int max, int exception){
        return randomInRangeExceptBetween(min, max, exception, exception);
    }

    /**
     * Generates a random number between {@param min} and {@param max} which is not contained within the set
     * of numbers between {@param a} and {@param b}
     * @param min
     * @param max
     * @param a
     * @param b
     * @return
     */
    protected static int randomInRangeExceptBetween(int min, int max, int a, int b){
        if(a > b)
            throw new IllegalArgumentException("Cannot generate random number from range where exception set min (a) > max (b).");

        int lengthBetween = b-a+1;

        int r = randomInRange(min, max-lengthBetween);

        if(r >= a)
            r += lengthBetween;

        return r;
    }

    /**
     * Returns true or false according to fate. The odds are set by you.
     * {@param successPercentage} The % chance of returning true.
     * @return
     */
    public static boolean luckyDay(int successPercentage){
        return successPercentage >= randomInRange(1, 100);
    }

    /**
     * Selects a random item within a List.
     * @param list List of items
     * @param <T> Type of item
     * @return
     */
    public static <T> T randomItem(List<T> list){
        if(list == null || list.size() == 0)
            return null;

        return list.get(randomInRange(0, list.size()-1));
    }

    /**
     *
     * @param array Array of items
     * @return
     */
    public static <T> T randomItem(T[] array){
        return randomItem(Arrays.asList(array));
    }
}
