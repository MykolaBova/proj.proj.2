package bova.general.thrott.cache.impl.ehcache;

import bova.general.thrott.cache.TokenToUserCache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

public class TokenToUserCacheImpl implements TokenToUserCache {

    private Cache<String, String> cache;

    public void buildCache(int heapSize, int expirationTimeSeconds) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        cache = cacheManager
                .createCache("tokenToUserCache",
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(
                                        String.class,
                                        String.class,
                                        ResourcePoolsBuilder.heap(heapSize))
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                                        Duration.ofSeconds(expirationTimeSeconds)))
                );
    }

    public TokenToUserCacheImpl(int heapSize, int expirationTimeSeconds) {
        buildCache(heapSize, expirationTimeSeconds);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }
}
