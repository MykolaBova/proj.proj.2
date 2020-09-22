//package com.in28minutes.java.bova;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class UtilX {
//
//    /**
//     * Handles add free spins http call from BO, or throws an exception if there
//     * is a problem with the request.
//     */
//    public static String handleAddFreespinsCall(JSONObject arguments) throws Exception {
//        final int game = getGame(arguments);
//        int partnerId = arguments.optInt(JSONKeys.LOGIN_PARTNER_ID);
//
//// ----> getPlayerIds
//        List<String> playerIds = new ArrayList<>();
//        try {
//
//// Players are given either as a JSON list or just as single player
//// id
//            String raw = arguments.getString(JSONKeys.LOGIN_USERID);
//            if (raw.charAt(0) == '[') {
//                JSONArray playerList = new JSONArray(raw);
//                for (int i = 0; i < playerList.length(); i++) {
//                    playerIds.add(playerList.getString(i));
//                }
//            } else {
//                playerIds.add(raw);
//            }
//        } catch (Exception e) {
//            throw new GameException(600, "\"" + JSONKeys.LOGIN_USERID
//                    + "\" missing or invalid. Either supply one userid, or list of them as JSON array");
//        }
//// <---- getPlayerIds
//
//        CasinoWorldServer.instance.dbConnection.checkUsersExist(playerIds, partnerId);
//
//        int paylines = arguments.optInt(JSONKeys.FREESPINS_COMMAND_PAYLINES, 1);
//        GameRound.checkLegalPaylines(GameRound.gameIdToName(game), paylines);
//        int rounds = Math.max(1, arguments.optInt(JSONKeys.COUNT));
//        int betamount = arguments.optInt(JSONKeys.FREESPINS_COMMAND_BETAMOUNT);
//        long totalValue = arguments.optLong(JSONKeys.TOTAL_VALUE);
//        final String currency = arguments.getString(JSONKeys.CURRENCY).toUpperCase();
//
//// ----> 10
//        final int currencyMultiplier;
//        if (currency != null) {
//            currencyMultiplier = Currencies.getMultiplier(currency);
//        } else
//            currencyMultiplier = 1;
//        int tokens = arguments.optInt("tokens");
//        if (tokens != 0) {
//            if (totalValue > 0)
//                throw new GameException(600, "Variable betsizes freespins and feature trigger can't be combined");
//            RealMoneyDemoPlayer modelPlayer = RealMoneyDemoPlayer.loginPlayer(currency == null ? "EUR" : currency,
//                    LoginChannel.LOGIN_WEB, game, partnerId);
//            try {
//                GameRound modelRound = GameRound.getInstance(game, modelPlayer, null,new DemoCashier(modelPlayer, partnerId));
//                int actualBet = (int) modelRound.getClosestLegalBet(betamount / paylines) * paylines;
//                if (actualBet != betamount)
//                    throw new
//                        GameException(600, "Game cannot be played for exactly " + betamount
//                                ", closest possible is " + actualBet
//                                ". Token free spins need to be created with exactly correct betsize so that token cost can be locked in
//                                advance.");
//
//                GameException(600, "Game cannot be played for exactly " + betamount
//                        ", closest possible is " + actualBet
//                        ". Token free spins need to be created with exactly correct betsize so that token cost can be locked in
//                        advance.");
//                int correctTokenCost = (int) GameRound.roundTokenPrice(betamount, modelPlayer.currencyMultiplier, game);
//                if (tokens != correctTokenCost) {
//                    throw new GameException(600,
//                            "Token value of " + tokens + " per round given, actual token cost of rounds with "
//                                    + betamount + " bet would be " + correctTokenCost);
//                }
//            } finally {
//                modelPlayer.removeCachedPlayer();
//            }
//        }
//// <---- 10
//
//// <----- extractSpecial
//        JSONObject special = null;
//        if (arguments.has(JSONKeys.FREESPINS_COMMAND_SPECIAL)) {
//            try {
//                special = new JSONObject(arguments.getString(JSONKeys.FREESPINS_COMMAND_SPECIAL));
//            } catch (JSONException e) {
//                throw new GameException(600, "Argument \"" + JSONKeys.FREESPINS_COMMAND_SPECIAL
//                        + "\" is invalid, value \"" + arguments.getString("special") + "\" is not a legal JSON object");
//            }
//        }
//        if (special == null) {
//            special = new JSONObject();
//        }
//        if (tokens != 0) {
//            special.put(JSONKeys.TOKENS, tokens);
//        }
//        else if (special.has(JSONKeys.TOKENS)) {
//            throw new GameException(600, "Create token free spins by adding tokens=[round token cost] parameter to "
//                    + GameRequestHandler.ADD_FREESPINS_URL + " call");
//        }
//        if (arguments.has(JSONKeys.PROMOTIONAL_CODE)) {
//            special.put(JSONKeys.PROMOTIONAL_CODE, arguments.getString(JSONKeys.PROMOTIONAL_CODE));
//        }
//        if (arguments.has(JSONKeys.SET_ID)) {
//            special.put(JSONKeys.SET_ID, arguments.getString(JSONKeys.SET_ID));
//        }
//        boolean allowAutoplay = arguments.optBoolean(JSONKeys.ALLOW_AUTOPLAY, true);
//        if (allowAutoplay) {
//            special.put(JSONKeys.ALLOW_AUTOPLAY, allowAutoplay);
//        }
//// -----> extractSpecial
//        // ----> 11
//// We are adding "variable betsizes" free spins, check options caller is
//// trying to use...
//        if (totalValue > 0) {
//            rounds = 0;
//            int maxOptions = arguments.optInt(JSONKeys.MAX_OPTIONS);
//            JSONArray possibleCounts = null;
//            if (arguments.has(JSONKeys.POSSIBLE_COUNTS))
//                possibleCounts = new JSONArray(arguments.getString(JSONKeys.POSSIBLE_COUNTS));
//            if ((maxOptions == 0 && possibleCounts == null) || (maxOptions != 0 && possibleCounts != null))
//                throw new GameException(600, "Variable betsizes freespins must include exactly one of \""
//                        + JSONKeys.MAX_OPTIONS + "\" or \"" + JSONKeys.POSSIBLE_COUNTS + "\"");
//            JSONArray euroBetSizes = new JSONArray();
//            if (possibleCounts != null) {
//// User has given a list of numbers of spins
//                Set<Long> betSizes = new HashSet<>();
//                for (int i = 0; i < possibleCounts.length(); i++) {
//                    long count = possibleCounts.getLong(i);
//                    if (totalValue % count != 0)
//                        throw new GameException(600, "Bad value in " + JSONKeys.POSSIBLE_COUNTS + ", " + totalValue
//                                + " isn't divisible by " + count);
//                    betSizes.add(totalValue / count);
//                }
//                List<Long> checked = pickLegalBetsizes(game, partnerId, currency, paylines, betSizes);
//                if (checked.size() != betSizes.size()) {
//                    betSizes.removeAll(checked);
//                    throw new GameException(600, "Following listed " + JSONKeys.POSSIBLE_COUNTS
//                            + " options would lead to illegal betsizes: "
//                            + betSizes.stream().map(bet ­> totalValue / bet).collect(Collectors.toList()).toString());
//                }
//                for (Long betsize : checked) {
//                    euroBetSizes.put(betsize / currencyMultiplier);
//                }
//            } else {
//// User wishes server to autoselect the numbers of spins
//                List<Long> possibleSpinCounts = autoselectFreespinsCounts(arguments);
//                if (possibleSpinCounts.size() < 2)
//                    throw new GameException(600,
//                            "Variable betsize spins are impossible, as there aren't multiple possible spin counts for these settings
//                (legal: "
//                        + possibleSpinCounts.toString() + ")");
//                for (long spins : possibleSpinCounts)
//                    euroBetSizes.put(totalValue / spins / currencyMultiplier);
//            }
//            special.put(JSONKeys.BET_SIZE_OPTIONS, euroBetSizes);
//        }
//        // <---- 11
//
//        // ----> 12
//        Set<String> failures = new HashSet<>();
//        JSONObject freeSpinIds = new JSONObject();
//        for (String playerId : playerIds) {
//            try {
//
//                long freeSpinsId = Utils.getFreeId();
//
//                FreeSpinsType spintype = FreeSpinsType.valueOf(arguments.optString(
//                        JSONKeys.SPINS_TYPE, "money"));
//
//                long expiration;
//                if (arguments.has(JSONKeys.FREESPINS_COMMAND_EXPIRE)) {
//                    expiration = arguments.getLong(JSONKeys.FREESPINS_COMMAND_EXPIRE);
//                } else {
//                    expiration = System.currentTimeMillis() / 1000 + 7 * 24 * 60 * 60;
//                }
//
//                CasinoWorldServer.instance.dbConnection.givePlayerFreespins(playerId, partnerId, game,
//                        spintype, currency, betamount, paylines, rounds, special, freeSpinsId, expiration,
//                        totalValue != 0 ? totalValue : betamount * rounds, tokens != 0);
//
//                freeSpinIds.put(playerId, freeSpinsId);
//                RealMoneyPlayer.getPlayerByUserName(playerId).parallelStream().forEach(pl ­> {
//                    if (pl.gameRoundCache.isGameReserved(game)) {
//                        pl.lock.lock();
//                        try {
//                            pl.checkCampaigns();
//                        } catch (GameException ge) {
//                            Logger.error(ge);
//                        } finally {
//                            pl.lock.unlock();
//                        }
//                    }
//                });
//
//            } catch (Exception e) {
//                failures.add(playerId);
//            }
//        }
//// <---- 12
//
//// ----> fillResponse
//        JSONObject response = new JSONObject();
//        response.put("status", "OK");
//        response.put("given", (playerIds.size() ­ failures.size()));
//        response.put("spinSetIds", freeSpinIds);
//        if (failures.isEmpty())
//            response.put("message", "Free spins successfully added for " + playerIds.size() + " players");
//        else
//            response.put("message", "Free spins successfully added for " + (playerIds.size() ­ failures.size())
//                    + " players, " + failures.size() + " players failed (" + failures.toString() + ")");
//        if (totalValue != 0)
//            response.put(JSONKeys.BET_SIZE_OPTIONS, special.getJSONArray(JSONKeys.BET_SIZE_OPTIONS));
//
//        return (response.toString());
//// <---- fillResponse
//
//    }
//}
