package bova.general.thrott.services.throtting.impl;

import bova.general.thrott.cache.LeftRequestsForUserCache;
import bova.general.thrott.cache.RpsForUserCache;
import bova.general.thrott.cache.TokenToUserCache;
import bova.general.thrott.domain.LeftRequests;
import bova.general.thrott.services.sla.SlaService;
import bova.general.thrott.services.throtting.ThrottlingService;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ThrottlingServiceImpl implements ThrottlingService {

    private final String NOT_FOUND_USER = "_NOT_FOUND_USER";

    private final String GUEST_USER = "_GUEST_USER";

    public static long timeSlide = 1000;// 1000 ms = 1 s

    private int guestRPS = 0;

    // Caches
    private RpsForUserCache rpsForUserCache;

    private TokenToUserCache tokenToUserCache;

    private LeftRequestsForUserCache leftRequestsForUserCache;

    @Override
    public boolean isRequestAllowed(Optional<String> tokenOptional) {

        int rps = guestRPS;
        String user = GUEST_USER;
        boolean needSLAServiceCall = false;

        // Determine rps and needSLAServiceCall
        if(tokenOptional.isEmpty()) {
            rps = guestRPS;
        } else {
            String token = tokenOptional.get();
            if(!tokenToUserCache.containsKey(token)) {
                // No user for given token
                needSLAServiceCall = true;

            } else {
                user = tokenToUserCache.get(token);

                if(!rpsForUserCache.containsKey(user)) {
                    // no rps for given user
                    needSLAServiceCall = true;
                } else {
                    rps = rpsForUserCache.get(user);
                }
            }
        }

        long addingRequestTime =  timeSlide / rps;

        if(needSLAServiceCall){
            CompletableFuture<Void> future = new CompletableFuture();

            future.thenAccept(s -> {
                    System.out.println("Computation returned: " + s);
                    System.out.println("Computation returned 2: " + s);
                    // TODO:Add here call to SLA service and update of corresponding caches with returned Future
                });
            };


        // Get and update leftRequests
        LeftRequests leftRequests;
        long now = System.currentTimeMillis();

        if(!leftRequestsForUserCache.containsKey(user)) {
            // no leftRequests for given user yet
            leftRequests = new LeftRequests(rps, now);
        } else {
            leftRequests = leftRequestsForUserCache.get(user);
        }

        boolean result = false;

        // take in account passed time
        if(leftRequests.getLastRequestTime() != now) {

            long spentTime = now - leftRequests.getLastRequestTime();

            // TODO: To change system out tologging later
            System.out.println("spentTime = " + spentTime);

            int addedRequests = (int) (
                    spentTime / addingRequestTime
            );

            if(addedRequests > rps) {
                leftRequests.setLeftRequests(rps);
            } else {
                leftRequests.setLeftRequests(leftRequests.getLeftRequests() + addedRequests);
            }
        }

        // Check service availability
        if(leftRequests.getLeftRequests() > 0) {
            result = true;
        } else {
            result = false;
        }

        // Set updated leftRequests value
        leftRequests.setLeftRequests(leftRequests.getLeftRequests()-1);

        leftRequests.setLastRequestTime(now);
        leftRequestsForUserCache.put(user, leftRequests);

        System.out.println(leftRequests);

        return result;
    }

    public ThrottlingServiceImpl(int guestRPS,
                                 RpsForUserCache rpsForUserCache,
                                 TokenToUserCache tokenToUserCache,
                                 LeftRequestsForUserCache leftRequestsForUserCache,
                                 SlaService slaService) {

        this.guestRPS = guestRPS;

        this.rpsForUserCache = rpsForUserCache;

        this.tokenToUserCache = tokenToUserCache;

        this.leftRequestsForUserCache = leftRequestsForUserCache;
    }
}
