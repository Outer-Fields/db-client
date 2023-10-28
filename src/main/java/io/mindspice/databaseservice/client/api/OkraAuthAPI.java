package io.mindspice.databaseservice.client.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.databaseservice.client.DBServiceClient;
import io.mindspice.databaseservice.client.Request;
import io.mindspice.databaseservice.client.endpoints.AuthEndpoint;
import io.mindspice.databaseservice.client.endpoints.GameEndpoint;
import io.mindspice.databaseservice.client.schema.DuplicateLauncherCheck;
import io.mindspice.databaseservice.client.util.Util;
import io.mindspice.mindlib.util.JsonUtils;
import io.mindspice.databaseservice.client.schema.Credential;

import java.io.IOException;


public class OkraAuthAPI extends BaseAPI {

    public OkraAuthAPI(DBServiceClient client) {
        super(client);
    }

    public APIResponse<Boolean> userExists(String username) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("username", username);
            Request req = new Request(AuthEndpoint.USER_EXISTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "exists", Boolean.class, AuthEndpoint.USER_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Integer> registerUser(String username, String displayName, String password,
            boolean termsAccept, String termsHash) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("username", username)
                    .put("password", password)
                    .put("display_name", displayName)
                    .put("terms_accept", termsAccept)
                    .put("terms_hash", termsHash)
                    .buildBytes();
            Request req = new Request(AuthEndpoint.REGISTER_USER, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "player_id", Integer.class, AuthEndpoint.REGISTER_USER);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Credential> getUserCredentials(String username) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("username", username);
            Request req = new Request(AuthEndpoint.GET_CREDENTIALS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, Credential.class, AuthEndpoint.GET_CREDENTIALS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> setFundAddresses(int playerId, String playerXchAddr, String internalXchAddr,
            String internalPotionAddr) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("player_xch_addr", playerXchAddr)
                    .put("internal_xch_addr", internalXchAddr)
                    .put("internal_potion_addr", internalPotionAddr)
                    .buildBytes();
            Request req = new Request(AuthEndpoint.SET_FUND_ADDRESS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, AuthEndpoint.SET_FUND_ADDRESS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updateLastLogin(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(AuthEndpoint.UPDATE_LAST_LOGIN, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, AuthEndpoint.UPDATE_LAST_LOGIN);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updatePlayerDid(int playerId, String launcherId) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("launcher_id", Util.normalizeHex(launcherId))
                    .buildBytes();
            Request req = new Request(AuthEndpoint.UPDATE_PLAYER_DID, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, AuthEndpoint.UPDATE_PLAYER_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updatePlayerPassword(int playerId, String password) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("password", password)
                    .buildBytes();
            Request req = new Request(AuthEndpoint.UPDATE_PLAYER_PASSWORD, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, AuthEndpoint.UPDATE_PLAYER_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<String> getPlayerAccountLauncher(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(AuthEndpoint.GET_PLAYER_ACCOUNT_LAUNCHER, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "did_launcher", String.class, AuthEndpoint.GET_PLAYER_ACCOUNT_LAUNCHER);
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

    public APIResponse<Long> getLastPasswordReset(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(AuthEndpoint.GET_LAST_PASSWORD_RESET, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "last_reset", Long.class, AuthEndpoint.GET_LAST_PASSWORD_RESET);
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

    public APIResponse<Boolean> doesDidExists(String did) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("did", Util.normalizeHex(did));
            Request req = new Request(AuthEndpoint.CHECK_IF_DID_EXISTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, AuthEndpoint.CHECK_IF_DID_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> checkForExistingLauncher(String launcherId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("launcher_id", Util.normalizeHex(launcherId));
            Request req = new Request(AuthEndpoint.CHECK_FOR_EXISTING_LAUNCHER, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, AuthEndpoint.CHECK_FOR_EXISTING_LAUNCHER);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<DuplicateLauncherCheck> checkForDuplicateLauncher(String launcherId, String did) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("launcher_id", Util.normalizeHex(launcherId))
                    .put("did", Util.normalizeHex(did))
                    .buildBytes();
            Request req = new Request(AuthEndpoint.CHECK_FOR_DUPLICATE_LAUNCHER, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, DuplicateLauncherCheck.class, AuthEndpoint.CHECK_FOR_DUPLICATE_LAUNCHER);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }
}