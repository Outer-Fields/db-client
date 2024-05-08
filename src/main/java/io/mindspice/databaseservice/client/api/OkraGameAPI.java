package io.mindspice.databaseservice.client.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.databaseservice.client.DBServiceClient;
import io.mindspice.databaseservice.client.Request;
import io.mindspice.databaseservice.client.endpoints.GameEndpoint;
import io.mindspice.databaseservice.client.schema.*;
import io.mindspice.databaseservice.client.util.Util;
import io.mindspice.jxch.rpc.util.ChiaUtils;
import io.mindspice.jxch.rpc.util.bech32.AddressUtil;
import io.mindspice.mindlib.util.JsonUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class OkraGameAPI extends BaseAPI {

    public OkraGameAPI(DBServiceClient client) {
        super(client);
    }

    public APIResponse<Map<Integer, String>> getPawnSets(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PAWN_SETS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponseMap(
                    respNode, "pawn_sets", TypeRefs.PAWN_SET_MAP, GameEndpoint.GET_PAWN_SETS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

//    public APIResponse<Boolean> updatePawnSet(int playerId, int setNum, String setData) {
//        try {
//            byte[] data = new JsonUtils.ObjectBuilder()
//                    .put("player_id", playerId)
//                    .put("set_num", setNum)
//                    .put("set_data", setData)
//                    .buildBytes();
//            Request req = new Request(GameEndpoint.UPDATE_PAWN_SET, data);
//            byte[] resp = client.makeRequest(req);
//            JsonNode respNode = JsonUtils.readTree(resp);
//            return newResponse(respNode, "success", Boolean.class, GameEndpoint.UPDATE_PAWN_SET);
//        } catch (IOException e) {
//            throw new IllegalStateException("Bad return", e);
//        }
//    }

    public APIResponse<Boolean> updatePawnSet(int playerId, int setNum, String setData) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("set_num", setNum)
                    .put("set_data", setData)
                    .buildBytes();
            Request req = new Request(GameEndpoint.UPDATE_PAWN_SET, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.UPDATE_PAWN_SET);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> deletePawnSet(int playerId, int setNum) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("set_num", setNum)
                    .buildBytes();
            Request req = new Request(GameEndpoint.DELETE_PAWN_SET, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.DELETE_PAWN_SET);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Integer> getPotionTokenAmount(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_POTION_TOKEN_AMOUNT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(
                    respNode, "potion_token_amount", Integer.class, GameEndpoint.GET_POTION_TOKEN_AMOUNT
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<PlayerFunds> getPlayerFunds(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_FUNDS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, PlayerFunds.class, GameEndpoint.GET_PLAYER_FUNDS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> commitPotionPurchase(int playerId, int amount) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("amount", amount)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_POTION_PURCHASE, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.COMMIT_POTION_PURCHASE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> commitPotionUse(int playerId, int amount) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("amount", amount)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_POTION_USE, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.COMMIT_POTION_USE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> commitMatchResult(int playerId, boolean isWin) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("is_win", isWin)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_MATCH_RESULT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.COMMIT_MATCH_RESULT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> commitPlayerRewards(int playerId, Reward type, int amount) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("type", type)
                    .put("amount", amount)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_PLAYER_REWARDS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.COMMIT_PLAYER_REWARDS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Results> getPlayerDailyResults(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_DAILY_RESULTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, Results.class, GameEndpoint.GET_PLAYER_DAILY_RESULTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Results> getPlayerHistoricalResults(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_HISTORICAL_RESULTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, Results.class, GameEndpoint.GET_PLAYER_HISTORICAL_RESULTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<List<String>> getPlayerCards(String did) {
        try {
            byte[] data;
            if (did.toLowerCase().startsWith("did")) {
                data = JsonUtils.newSingleNodeAsBytes("did", AddressUtil.decode(did).toHexString(true));
            } else {
                data = JsonUtils.newSingleNodeAsBytes("did", Util.normalizeHex(did));
            }
            Request req = new Request(GameEndpoint.GET_PLAYER_CARDS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponseList(respNode, "card_uids", TypeRefs.STRING_LIST, GameEndpoint.GET_PLAYER_CARDS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<String> getDisplayName(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_DISPLAY_NAME, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "display_name", String.class, GameEndpoint.GET_PLAYER_DISPLAY_NAME);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updateDisplayName(int playerId, String displayName) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("display_name", displayName)
                    .buildBytes();
            Request req = new Request(GameEndpoint.UPDATE_PLAYER_DISPLAY_NAME, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.UPDATE_PLAYER_DISPLAY_NAME);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updatePlayerAvatar(int playerId, String imgLink) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("img_link", imgLink)
                    .buildBytes();
            Request req = new Request(GameEndpoint.UPDATE_PLAYER_AVATAR, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.UPDATE_PLAYER_AVATAR);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Long> getLastDidUpdate(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_LAST_DID_UPDATE, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "last_time", Long.class, GameEndpoint.GET_LAST_DID_UPDATE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Long> getLastAvatarUpdate(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_LAST_AVATAR_UPDATE, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "last_time", Long.class, GameEndpoint.GET_LAST_AVATAR_UPDATE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Long> getLastDisplayNameUpdate(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_LAST_DISPLAY_NAME_UPDATE, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "last_time", Long.class, GameEndpoint.GET_LAST_DISPLAY_NAME_UPDATE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> isValidDidLauncher(String launcherId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("launcher_id", Util.normalizeHex(launcherId));
            Request req = new Request(GameEndpoint.IS_VALID_DID_LAUNCHER, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.IS_VALID_DID_LAUNCHER);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<PlayerInfo> getPlayerInfo(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_INFO, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, PlayerInfo.class, GameEndpoint.GET_PLAYER_INFO);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<String> getPlayerDid(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_DID, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "did", String.class, GameEndpoint.GET_PLAYER_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> doesNameExist(String name) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("name", name);
            Request req = new Request(GameEndpoint.DOES_NAME_EXIST, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.DOES_NAME_EXIST);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<List<RewardDispersal>> getRewardsForDispersal() {
        try {
            byte[] data = JsonUtils.newEmptyNodeAsBytes();
            Request req = new Request(GameEndpoint.GET_REWARDS_FOR_DISPERSAL, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponseList(respNode, "rewards", TypeRefs.REWARD_LIST, GameEndpoint.GET_REWARDS_FOR_DISPERSAL);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<String> getAccountCoin(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_ACCOUNT_COIN, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "curr_coin", String.class, GameEndpoint.GET_ACCOUNT_COIN);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> resetDailyResults() {
        try {
            Request req = new Request(GameEndpoint.RESET_DAILY_RESULTS, JsonUtils.newEmptyNodeAsBytes());
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.RESET_DAILY_RESULTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> commitFullMatchResult(String matchUid, int player1, int player2, boolean player1Won,
            boolean player2Won, int roundCount, String finishState, List<String> player1Ips, List<String> player2Ips) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("match_uid", matchUid)
                    .put("player_1", player1)
                    .put("player_2", player2)
                    .put("player_1_won", player1Won)
                    .put("player_2_won", player2Won)
                    .put("round_count", roundCount)
                    .put("finish_state", finishState)
                    .put("player1_ips", player1Ips)
                    .put("player2_ips", player2Ips)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_FULL_MATCH_RESULT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.COMMIT_FULL_MATCH_RESULT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addFlaggedDispersal(int playerId, int okraAmount, int outrAmount, int nftAmount) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("okra_amount", okraAmount)
                    .put("outr_amount", outrAmount)
                    .put("nft_amount", nftAmount)
                    .buildBytes();
            Request req = new Request(GameEndpoint.ADD_FLAGGED_DISPERSAL, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.ADD_FLAGGED_DISPERSAL);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Integer> getFreeGamesPlayed(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_FREE_GAMES_PLAYED, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "games_played", Integer.class, GameEndpoint.GET_FREE_GAMES_PLAYED);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> incFreeGamesPlayed(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.ADD_FREE_GAME_PLAYED, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.ADD_FREE_GAME_PLAYED);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> resetFreeGames() {
        try {
            byte[] data = JsonUtils.newEmptyNodeAsBytes();
            Request req = new Request(GameEndpoint.RESET_FREE_GAMES_PLAYED, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.RESET_FREE_GAMES_PLAYED);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addPackRedemption(String packType, int amount, String senderAddress, String coinId) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("pack_type", packType)
                    .put("amount", amount)
                    .put("sender_address", senderAddress)
                    .put("coin_id", coinId)
                    .buildBytes();
            Request req = new Request(GameEndpoint.ADD_PACK_REDEMPTION, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, GameEndpoint.ADD_PACK_REDEMPTION);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<List<MatchResult>> getMatchResults(long startEpochMilli, long endEpochMilli) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("start_milli", startEpochMilli)
                    .put("end_milli", endEpochMilli)
                    .buildBytes();
            Request req = new Request(GameEndpoint.GET_MATCH_RESULTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponseList(respNode, "results", TypeRefs.MATCH_RESULT_LIST, GameEndpoint.GET_MATCH_RESULTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }

    }

}
