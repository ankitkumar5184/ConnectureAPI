package modules;


import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CollectionHandler {
    private CollectionHandler() {
    }

    public static <T> void verifyCollectionNotEmpty(List<T> retrievedValues) {
        Assert.assertFalse(retrievedValues.isEmpty(), "No values retrieved!");
    }

    public static <T> Tuple<T, Integer> getRandomElementAndIndex(List<T> collection) {
        int randomIndex = getRandomIndex(collection);
        return new Tuple(collection.get(randomIndex), randomIndex);
    }

    public static <T> T getRandomElement(List<T> collection){
        return collection.get(getRandomIndex(collection));
    }

    private static <T> int getRandomIndex(List<T> collection) {
        return (collection.size());
    }
}