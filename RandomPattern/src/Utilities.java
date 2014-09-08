import java.util.Collection;

/**
 * Created by gmtuk on 31/08/2014.
 */
abstract class Utilities {
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
}