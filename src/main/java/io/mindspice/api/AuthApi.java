package io.mindspice.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.DBServiceClient;
import io.mindspice.JsonUtils;
import io.mindspice.Request;
import io.mindspice.endpoints.AuthEndpoint;
import io.mindspice.schema.Credential;
import io.mindspice.util.ResponseUtil;

import java.io.IOException;


public class AuthApi {

    public static ApiResponse<Boolean> userExists(String username) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("username", username);
            Request req = new Request(AuthEndpoint.USER_EXISTS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "exists", Boolean.class, AuthEndpoint.USER_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<Boolean> registerUser(String username, String password) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("username", username)
                    .put("password", password)
                    .buildBytes();
            Request req = new Request(AuthEndpoint.REGISTER_USER, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, AuthEndpoint.REGISTER_USER);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");        }
    }

    public static ApiResponse<Credential> getUserCredentials(String username) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("username", username);
            Request req = new Request(AuthEndpoint.GET_CREDENTIALS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, Credential.class, AuthEndpoint.GET_CREDENTIALS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");        }
    }

    public static ApiResponse<Boolean> setFundAddresses(int playerId, String playerXchAddr, String internalXchAddr,
            String internalPotionAddr) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("player_xch_addr", playerXchAddr)
                    .put("internal_xch_addr", internalXchAddr)
                    .put("internal_potion_addr", internalPotionAddr)
                    .buildBytes();
            Request req = new Request(AuthEndpoint.SET_FUND_ADDRESS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponse(respNode, "success", Boolean.class, AuthEndpoint.SET_FUND_ADDRESS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");        }
    }
}