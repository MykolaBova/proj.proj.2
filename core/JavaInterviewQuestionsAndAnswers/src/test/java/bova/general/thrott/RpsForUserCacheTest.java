package bova.general.thrott;

import bova.general.thrott.cache.impl.ehcache.RpsForUserCacheImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RpsForUserCacheTest {

    public static final int HEAP_SIZE = 10;

    public static final int EXPIRATION_TIME_SECONDS = 20;

    private final RpsForUserCacheImpl cache = new RpsForUserCacheImpl(
            HEAP_SIZE,
            EXPIRATION_TIME_SECONDS);

    @Test
    public void whenCalculatingSquareValueAgain_thenCacheHasAllValues() {
        for (int i = 10; i < 15; i++) {
            assertFalse(cache.containsKey(String.valueOf(i)));
            System.out.println("Square value of " + i + " is: "
                    + getSquareValueOfNumber(String.valueOf(i)) + "\n");
        }

        for (int i = 10; i < 15; i++) {
            assertTrue(cache.containsKey(String.valueOf(i)));
            System.out.println("Square value of " + i + " is: "
                    + getSquareValueOfNumber(String.valueOf(i)) + "\n");
        }
    }

    public int getSquareValueOfNumber(String input) {
        if (cache.containsKey(input)) {
            return cache.get(input);
        }

        System.out.println("Calculating square value of " + input + " and caching result.");

        int squaredValue = (int) Math.pow(Integer.valueOf(input), 2);
        cache.put(input, squaredValue);

        return squaredValue;
    }
}
