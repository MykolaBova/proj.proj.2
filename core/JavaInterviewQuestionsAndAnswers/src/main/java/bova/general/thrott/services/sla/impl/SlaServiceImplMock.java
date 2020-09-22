package bova.general.thrott.services.sla.impl;

import bova.general.thrott.services.sla.SlaService;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class SlaServiceImplMock implements SlaService {

    public static final String TOKEN1 = "token1";
    public static final String TOKEN2 = "token2";
    public static final String TOKEN3 = "token3";

    public static final String USER1 = "user1";
    public static final String USER2 = "user2";

    public static final int RPS1 = 3;
    public static final int RPS2 = 4;

    public static final SLA SLA1 = new SLA(USER1, RPS1);
    public static final SLA SLA2 = new SLA(USER2, RPS2);

    public static int internalSLAServiceDelay = 3000; // 3 s

    @Override
    public CompletableFuture<SLA> getSlaByToken(String token) {

        SlaService.SLA sla = null;
        boolean found = false;

        switch (token) {
            case TOKEN1:
                sla = SLA1;
                found = true;
                break;
            case TOKEN2:
            case TOKEN3:
                sla = SLA2;
                found = true;
                break;
            default:
                found = false;
                break;
        }

        try {
            Thread.sleep(internalSLAServiceDelay);
        } catch (InterruptedException e) {
            // TODO: add corresponding exception here later
            e.printStackTrace();
        }

        if(found == true) {
            return completedFuture(sla);
        } else {
            return null;
        }
    }
}
