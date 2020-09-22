package bova.general.thrott.cache.impl.ehcache;

import bova.general.thrott.cache.RpsForUserCache;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

public class RpsForUserCacheImpl implements RpsForUserCache {

    private Cache<String, Integer> cache;

    public void buildCache(int heapSize, int expirationTimeSeconds) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        cache = cacheManager
                .createCache("slaForUserCache",
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(
                                        String.class,
                                        Integer.class,
                                        ResourcePoolsBuilder.heap(heapSize))
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                                        Duration.ofSeconds(expirationTimeSeconds)))
                );
    }

    public RpsForUserCacheImpl(int heapSize, int expirationTimeSeconds) {
        buildCache(heapSize, expirationTimeSeconds);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public int get(String key) {
        return cache.get(key);
    }

    public void put(String key, int value) {
        cache.put(key, value);
    }
}
