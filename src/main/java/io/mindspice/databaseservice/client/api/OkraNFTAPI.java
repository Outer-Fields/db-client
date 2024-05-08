package io.mindspice.databaseservice.client.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.databaseservice.client.DBServiceClient;
import io.mindspice.databaseservice.client.Request;
import io.mindspice.databaseservice.client.endpoints.GameEndpoint;
import io.mindspice.databaseservice.client.endpoints.NftEndpoint;
import io.mindspice.databaseservice.client.schema.*;
import io.mindspice.databaseservice.client.util.Util;
import io.mindspice.mindlib.util.JsonUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class OkraNFTAPI extends BaseAPI {

    public OkraNFTAPI(DBServiceClient client) {
        super(client);
    }

    public APIResponse<String> checkIfCardExists(String coinId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("coin_id", Util.normalizeHex(coinId));
            Request req = new Request(NftEndpoint.CHECK_IF_CARD_EXISTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "launcher_id", String.class, NftEndpoint.CHECK_IF_CARD_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<CardAndAccountCheck> checkIfCardOrAccountExists(List<String> coinIds) {
        try {
            var ids = coinIds.stream().map(Util::normalizeHex).toList();
            byte[] data = JsonUtils.newSingleNodeAsBytes("coin_ids", ids);
            Request req = new Request(NftEndpoint.CHECK_IF_EXISTS_BULK, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, CardAndAccountCheck.class, NftEndpoint.CHECK_IF_CARD_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<CardPack> checkIfPackExists(String coinId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("coin_id", Util.normalizeHex(coinId));
            Request req = new Request(NftEndpoint.CHECK_IF_PACK_EXISTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, CardPack.class, NftEndpoint.CHECK_IF_PACK_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

//    public APIResponse<String> checkIfAccountExists(String address) {
//        try {
//            byte[] data = JsonUtils.newSingleNodeAsBytes("address", address);
//            Request req = new Request(NftEndpoint.CHECK_IF_ACCOUNT_EXISTS, data);
//            byte[] resp = client.makeRequest(req);
//            JsonNode respNode = JsonUtils.readTree(resp);
//            return newResponse(respNode, "player_id", String.class, NftEndpoint.CHECK_IF_ACCOUNT_EXISTS);
//        } catch (IOException e) {
//            throw new IllegalStateException("Bad return", e);
//        }
//    }

//    public APIResponse<Boolean> updateNft(String ownerDid, String coinId, String launcherId, int height) {
//        try {
//            byte[] data = new JsonUtils.ObjectBuilder()
//                    .put("owner_did", ownerDid)
//                    .put("coin_id", coinId)
//                    .put("launcher_id", launcherId)
//                    .put("height", height)
//                    .buildBytes();
//            Request req = new Request(NftEndpoint.UPDATE_NFT, data);
//            byte[] resp = client.makeRequest(req);
//            JsonNode respNode = JsonUtils.readTree(resp);
//            return newResponse(respNode, "success", Boolean.class, NftEndpoint.UPDATE_NFT);
//        } catch (IOException e) {
//            throw new IllegalStateException("Bad return", e);
//        }
//    }

    public APIResponse<Boolean> addNewCardNFT(String ownerDid, String coinId, String launcherId,
            String uid, long height) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("owner_did", Util.normalizeHex(ownerDid))
                    .put("coin_id", Util.normalizeHex(coinId))
                    .put("launcher_id", Util.normalizeHex(launcherId))
                    .put("uid", uid)
                    .put("height", height)
                    .buildBytes();
            Request req = new Request(NftEndpoint.ADD_NEW_CARD_NFT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_NEW_CARD_NFT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addNewAccountNFT(int playerId, String ownerDid, String coinId, String launcherId,
            long height) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("owner_did", Util.normalizeHex(ownerDid))
                    .put("coin_id", Util.normalizeHex(coinId))
                    .put("launcher_id", Util.normalizeHex(launcherId))
                    .put("height", height)
                    .buildBytes();
            Request req = new Request(NftEndpoint.ADD_NEW_ACCOUNT_NFT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_NEW_ACCOUNT_NFT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<String> getPlayersDid(int playerId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("player_id", playerId);
            Request req = new Request(NftEndpoint.GET_PLAYER_DID, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "owner_did", String.class, NftEndpoint.GET_PLAYER_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updateCardDid(NftUpdate updateInfo) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("owner_did", Util.normalizeHex(updateInfo.did()))
                    .put("launcher_id", Util.normalizeHex(updateInfo.launcherId()))
                    .put("coin_id", Util.normalizeHex(updateInfo.coinId()))
                    .put("height", updateInfo.height())
                    .buildBytes();
            Request req = new Request(NftEndpoint.UPDATE_CARD_DID, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.UPDATE_CARD_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updateAccountDid(NftUpdate updateInfo) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("owner_did", Util.normalizeHex(updateInfo.did()))
                    .put("launcher_id", Util.normalizeHex(updateInfo.launcherId()))
                    .put("coin_id", Util.normalizeHex(updateInfo.coinId()))
                    .put("height", updateInfo.height())
                    .put("collision", updateInfo.collision())
                    .buildBytes();
            Request req = new Request(NftEndpoint.UPDATE_ACCOUNT_DID, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.UPDATE_ACCOUNT_DID);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<AccountDid> checkIfAccountCoinExists(String coinId) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("coin_id", Util.normalizeHex(coinId));
            Request req = new Request(NftEndpoint.CHECK_IF_ACCOUNT_NFT_EXISTS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, AccountDid.class, NftEndpoint.CHECK_IF_ACCOUNT_NFT_EXISTS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Integer> getAndIncEdt(String collectionTable, String uid) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("collection_table", collectionTable)
                    .put("uid", uid)
                    .buildBytes();
            Request req = new Request(NftEndpoint.GET_AND_INC_EDITION, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "edt", Integer.class, NftEndpoint.GET_AND_INC_EDITION);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<List<Card>> getCardCollection(String collectionTable) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("collection_table", collectionTable);
            Request req = new Request(NftEndpoint.GET_CARD_COLLECTION, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponseList(respNode, "card_list", TypeRefs.CARD_LIST, NftEndpoint.GET_CARD_COLLECTION);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> updatePlayerXchAddress(int playerId, String xchAddress) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("player_id", playerId)
                    .put("xch_address", xchAddress)
                    .buildBytes();
            Request req = new Request(NftEndpoint.UPDATE_PLAYER_XCH_ADDRESS, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.UPDATE_PLAYER_XCH_ADDRESS);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addMintLog(MintLog mintLog) {
        try {
            byte[] data = JsonUtils.writeBytes(mintLog);
            Request req = new Request(NftEndpoint.ADD_MINT_LOG, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_MINT_LOG);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addTransactionLog(TransactionLog transactionLog) {
        try {
            byte[] data = JsonUtils.writeBytes(transactionLog);
            Request req = new Request(NftEndpoint.ADD_TRANSACTION_LOG, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_TRANSACTION_LOG);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addCardToCollection(String collectionSet, Card card) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("collection_set", collectionSet)
                    .put("card", card)
                    .buildBytes();

            Request req = new Request(NftEndpoint.ADD_CARD_TO_COLLECTION, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_CARD_TO_COLLECTION);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addPackPurchases(String uuid, String address, String packType,
            int height, String coinId) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("uuid", uuid)
                    .put("address", Util.normalizeHex(address))
                    .put("pack_type", packType)
                    .put("height", height)
                    .put("coin_id", Util.normalizeHex(coinId))
                    .buildBytes();
            Request req = new Request(NftEndpoint.ADD_PACK_PURCHASE, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_PACK_PURCHASE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addFailedUpdate(String launcherId, int height, boolean isAccount, String reason) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("launcher_id", Util.normalizeHex(launcherId))
                    .put("height", height)
                    .put("is_account", isAccount)
                    .put("reason", reason)
                    .buildBytes();
            Request req = new Request(NftEndpoint.ADD_FAILED_UPDATE, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_FAILED_UPDATE);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addFailedMint(String uuid, String reason) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("uuid", uuid)
                    .put("reason", reason)
                    .buildBytes();
            Request req = new Request(NftEndpoint.ADD_FAILED_MINT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_FAILED_MINT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<Boolean> addFailedTransaction(String uuid, int playerId, int okraAmount, int outrAmount, String reason) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("uuid", uuid)
                    .put("player_id", playerId)
                    .put("okra_amount", okraAmount)
                    .put("outr_amount", outrAmount)
                    .put("reason", reason)
                    .buildBytes();
            Request req = new Request(NftEndpoint.ADD_FAILED_TRANSACTION, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "success", Boolean.class, NftEndpoint.ADD_FAILED_TRANSACTION);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

}
