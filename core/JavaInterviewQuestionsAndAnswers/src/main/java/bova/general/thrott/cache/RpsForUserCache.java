package bova.general.thrott.cache;


/**
 * key - user
 * value - rps
 */
public interface RpsForUserCache {

    void buildCache(int heapSize, int expirationTimeSeconds);

    boolean containsKey(String key);

    int get(String key);

    void put(String key, int value);
}
