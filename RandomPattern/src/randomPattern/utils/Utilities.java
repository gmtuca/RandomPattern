package randomPattern.utils;

import java.util.Collection;

/**
 * Created by gmtuk on 31/08/2014.
 */
public abstract class Utilities {
    /**
     *
     * @param collection
     * @param items
     * @param <T>
     */
    public static <T> void addItemsToCollection(Collection<T> collection, T... items){
        if(collection == null)
            return;

        for(T t : items)
            if(t != null)
                collection.add(t);
    }

    /**
     *
     * @param i
     * @param min
     * @param max
     * @return
     */
    public static int saturate(int i, int min, int max){
        if(i < min)
            return min;
        if(i > max)
            return max;

        return i;
    }

    /**
     *
     * @return
     */
    public static int saturate(int i){
        return (i < 0) ? 0 : i;
    }

}