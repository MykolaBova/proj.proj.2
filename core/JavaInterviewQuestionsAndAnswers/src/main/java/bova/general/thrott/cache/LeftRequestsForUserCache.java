package bova.general.thrott.cache;


import bova.general.thrott.domain.LeftRequests;

/**
 * key - user
 * value - LeftRequests
 */
public interface LeftRequestsForUserCache {

    void buildCache(int heapSize);

    boolean containsKey(String key);

    LeftRequests get(String key);

    void put(String key, LeftRequests value);
}
