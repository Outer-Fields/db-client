package io.mindspice.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.DBServiceClient;
import io.mindspice.JsonUtils;
import io.mindspice.Request;
import io.mindspice.endpoints.NftEndpoint;
import io.mindspice.schema.CardPack;
import io.mindspice.util.ResponseUtil;

import java.io.IOException;


public class NftApi {

    public static ApiResponse<String> checkIfCardExists(String coinId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("coin_id", coinId);
            Request req = new Request(NftEndpoint.CHECK_IF_CARD_EXISTS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(
                    respNode, "launcher_id", String.class, NftEndpoint.CHECK_IF_CARD_EXISTS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<CardPack> checkIfPackExists(String coinId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("coin_id", coinId);
            Request req = new Request(NftEndpoint.CHECK_IF_PACK_EXISTS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, CardPack.class, NftEndpoint.CHECK_IF_PACK_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<String> checkIfAccountExists(String address) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("address", address);
            Request req = new Request(NftEndpoint.CHECK_IF_ACCOUNT_EXISTS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(
                    respNode, "player_id", String.class, NftEndpoint.CHECK_IF_ACCOUNT_EXISTS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> updateNft(String ownerDid, String coinId, String launcherId, int height) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("owner_did", ownerDid)
                    .put("coin_id", coinId)
                    .put("launcher_id", launcherId)
                    .put("height", height)
                    .buildBytes();
            Request req = new Request(NftEndpoint.UPDATE_NFT, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, NftEndpoint.UPDATE_NFT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> addNewNft(String ownerDid, String coinId, String launcherId, String uid) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("owner_did", ownerDid)
                    .put("coin_id", coinId)
                    .put("launcher_id", launcherId)
                    .put("uid", uid)
                    .buildBytes();
            Request req = new Request(NftEndpoint.ADD_NEW_NFT, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_NEW_NFT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<String> getPlayersDid(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(NftEndpoint.GET_PLAYER_DID, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "owner_did", String.class, NftEndpoint.GET_PLAYER_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> updateNftDid(String launcherId, String ownerDid) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("owner_did", ownerDid)
                    .put("launcher_id", launcherId)
                    .buildBytes();
            Request req = new Request(NftEndpoint.UPDATE_NFT_DID, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, NftEndpoint.UPDATE_NFT_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }


}
