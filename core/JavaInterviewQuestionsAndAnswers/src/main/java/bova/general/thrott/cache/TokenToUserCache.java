package bova.general.thrott.cache;


/**
 * key - token
 * value - user
 */
public interface TokenToUserCache {

    void buildCache(int heapSize, int expirationTimeSeconds);

    boolean containsKey(String key);

    String get(String key);

    void put(String key, String value);
}
