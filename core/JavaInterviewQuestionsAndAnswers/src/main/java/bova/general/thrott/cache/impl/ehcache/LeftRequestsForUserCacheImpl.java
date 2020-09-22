package bova.general.thrott.cache.impl.ehcache;

import bova.general.thrott.cache.LeftRequestsForUserCache;
import bova.general.thrott.domain.LeftRequests;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

public class LeftRequestsForUserCacheImpl implements LeftRequestsForUserCache {

    private Cache<String, LeftRequests> cache;

    public void buildCache(int heapSize) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        cache = cacheManager
                .createCache("tokenToUserCache",
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(
                                        String.class,
                                        LeftRequests.class,
                                        ResourcePoolsBuilder.heap(heapSize))
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                                        Duration.ofSeconds(1000000)))
                );
    }

    public LeftRequestsForUserCacheImpl(int heapSize) {
        buildCache(heapSize);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public LeftRequests get(String key) {
        return cache.get(key);
    }

    public void put(String key, LeftRequests value) {
        cache.put(key, value);
    }
}
