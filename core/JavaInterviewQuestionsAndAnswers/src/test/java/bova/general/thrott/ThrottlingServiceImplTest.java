package bova.general.thrott;

import bova.general.thrott.cache.LeftRequestsForUserCache;
import bova.general.thrott.cache.RpsForUserCache;
import bova.general.thrott.cache.TokenToUserCache;
import bova.general.thrott.cache.impl.ehcache.LeftRequestsForUserCacheImpl;
import bova.general.thrott.cache.impl.ehcache.RpsForUserCacheImpl;
import bova.general.thrott.cache.impl.ehcache.TokenToUserCacheImpl;
import bova.general.thrott.services.sla.SlaService;
import bova.general.thrott.services.sla.impl.SlaServiceImplMock;
import bova.general.thrott.services.throtting.ThrottlingService;
import bova.general.thrott.services.throtting.impl.ThrottlingServiceImpl;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThrottlingServiceImplTest {

    public static final int GUEST_RPS = 2;

    public static final int HEAP_SIZE = 10;

    public static final int EXPIRATION_TIME_SECONDS = 20;


    @Test
    public void simpleTest() {
        // Caches
        RpsForUserCache rpsForUserCache = new RpsForUserCacheImpl(
                HEAP_SIZE,
                EXPIRATION_TIME_SECONDS);

        TokenToUserCache tokenToUserCache = new TokenToUserCacheImpl(
                HEAP_SIZE,
                EXPIRATION_TIME_SECONDS);

        LeftRequestsForUserCache leftRequestsForUserCache = new LeftRequestsForUserCacheImpl(
                HEAP_SIZE);

        // Services
        SlaService slaService = new SlaServiceImplMock();

        ThrottlingService throttlingService = new ThrottlingServiceImpl(GUEST_RPS,
                rpsForUserCache,
                tokenToUserCache,
                leftRequestsForUserCache,
                slaService);

        Optional<String> token = Optional.of("token4");

        assertTrue(throttlingService.isRequestAllowed(token));

        assertTrue(throttlingService.isRequestAllowed(token));

        assertFalse(throttlingService.isRequestAllowed(token));
    }

    @Test
    public void simpleTest_restoredRPS() throws InterruptedException {
        // Caches
        RpsForUserCache rpsForUserCache = new RpsForUserCacheImpl(
                HEAP_SIZE,
                EXPIRATION_TIME_SECONDS);

        TokenToUserCache tokenToUserCache = new TokenToUserCacheImpl(
                HEAP_SIZE,
                EXPIRATION_TIME_SECONDS);

        LeftRequestsForUserCache leftRequestsForUserCache = new LeftRequestsForUserCacheImpl(
                HEAP_SIZE);

        // Services
        SlaService slaService = new SlaServiceImplMock();

        ThrottlingService throttlingService = new ThrottlingServiceImpl(GUEST_RPS,
                rpsForUserCache,
                tokenToUserCache,
                leftRequestsForUserCache,
                slaService);

        Optional<String> token = Optional.of("token4");

        assertTrue(throttlingService.isRequestAllowed(token));

        assertTrue(throttlingService.isRequestAllowed(token));

        assertFalse(throttlingService.isRequestAllowed(token));

        Thread.sleep(ThrottlingServiceImpl.timeSlide* 2);

        assertTrue(throttlingService.isRequestAllowed(token));

        assertTrue(throttlingService.isRequestAllowed(token));

        assertFalse(throttlingService.isRequestAllowed(token));

    }
}
