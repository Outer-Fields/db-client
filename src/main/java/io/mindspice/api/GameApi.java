package io.mindspice.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.DBServiceClient;
import io.mindspice.JsonUtils;
import io.mindspice.Request;
import io.mindspice.endpoints.GameEndpoint;
import io.mindspice.schema.PlayerFunds;
import io.mindspice.schema.Results;
import io.mindspice.schema.Reward;
import io.mindspice.schema.TypeRefs;
import io.mindspice.util.ResponseUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class GameApi {

    public static ApiResponse<Map<Integer, JsonNode>> getPawnSets(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PAWN_SETS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponseMap(
                    respNode, "pawn_sets", TypeRefs.PAWN_SET_MAP, GameEndpoint.GET_PAWN_SETS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> updatePawnSet(int playerId, int setNum, JsonNode setData) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("set_num", setNum)
                    .put("set_data", setData)
                    .buildBytes();
            Request req = new Request(GameEndpoint.UPDATE_PAWN_SET, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, GameEndpoint.UPDATE_PAWN_SET);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> updatePawnSet(int playerId, int setNum, String setData) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("set_num", setNum)
                    .put("set_data", setData)
                    .buildBytes();
            Request req = new Request(GameEndpoint.UPDATE_PAWN_SET, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, GameEndpoint.UPDATE_PAWN_SET);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> deletePawnSet(int playerId, int setNum) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("set_num", setNum)
                    .buildBytes();
            Request req = new Request(GameEndpoint.DELETE_PAWN_SET, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, GameEndpoint.DELETE_PAWN_SET);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Integer> getPotionTokenAmount(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_POTION_TOKEN_AMOUNT, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(
                    respNode, "potion_token_amount", Integer.class, GameEndpoint.GET_POTION_TOKEN_AMOUNT
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<PlayerFunds> getPlayerFunds(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_FUNDS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, PlayerFunds.class, GameEndpoint.GET_PLAYER_FUNDS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> commitPotionPurchase(int playerId, int amount) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("amount", amount)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_POTION_PURCHASE, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, GameEndpoint.COMMIT_POTION_PURCHASE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> commitPotionUse(int playerId, int amount) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("amount", amount)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_POTION_USE, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(
                    respNode, "success", Boolean.class, GameEndpoint.COMMIT_POTION_USE
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> commitMatchResult(int playerId, boolean isWin) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("is_win", isWin)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_MATCH_RESULT, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(
                    respNode, "success", Boolean.class, GameEndpoint.COMMIT_MATCH_RESULT
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> commitPlayerRewards(int playerId, Reward type, int amount) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("type", type)
                    .put("amount", amount)
                    .buildBytes();
            Request req = new Request(GameEndpoint.COMMIT_PLAYER_REWARDS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(
                    respNode, "success", Boolean.class, GameEndpoint.COMMIT_PLAYER_REWARDS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Results> getPlayerDailyResults(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_DAILY_RESULTS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, Results.class, GameEndpoint.GET_PLAYER_DAILY_RESULTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Results> getPlayerHistoricalResults(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_HISTORICAL_RESULTS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, Results.class, GameEndpoint.GET_PLAYER_HISTORICAL_RESULTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<List<String>> getPlayerCards(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(GameEndpoint.GET_PLAYER_CARDS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponseList(
                    respNode, "card_uids", TypeRefs.STRING_LIST, GameEndpoint.GET_PLAYER_CARDS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }
}
