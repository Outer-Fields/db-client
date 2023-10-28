package io.mindspice.databaseservice.client.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.databaseservice.client.DBServiceClient;
import io.mindspice.databaseservice.client.Request;
import io.mindspice.databaseservice.client.endpoints.ChiaEndpoint;
import io.mindspice.jxch.rpc.schemas.fullnode.AdditionsAndRemovals;
import io.mindspice.jxch.rpc.schemas.object.CoinRecord;
import io.mindspice.mindlib.util.JsonUtils;

import java.io.IOException;


public class OkraChiaAPI extends BaseAPI {

    public OkraChiaAPI(DBServiceClient client) {
        super(client);
    }

    public APIResponse<AdditionsAndRemovals> getCoinRecordsByHeight(int height) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("height", height);
            Request req = new Request(ChiaEndpoint.COIN_RECORDS_BY_HEIGHT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode,  AdditionsAndRemovals.class , ChiaEndpoint.COIN_RECORDS_BY_HEIGHT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<AdditionsAndRemovals> getCoinRecordsByPuzzleHash(String puzzleHash) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("puzzle_hash", puzzleHash);
            Request req = new Request(ChiaEndpoint.COIN_RECORDS_BY_PUZZLEHASH, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode,  AdditionsAndRemovals.class , ChiaEndpoint.COIN_RECORDS_BY_PUZZLEHASH);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<CoinRecord> getCoinRecordByName(String coin_name) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("name", coin_name);
            Request req = new Request(ChiaEndpoint.COIN_RECORD_BY_NAME, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "coin_record", CoinRecord.class , ChiaEndpoint.COIN_RECORD_BY_NAME);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }

    public APIResponse<CoinRecord> getCoinRecordByParentAndHeight(String coinName, int height) {
        try {
            byte[] data = new JsonUtils.ObjectBuilder()
                    .put("name", coinName)
                    .put("height", height)
                    .buildBytes();
            Request req = new Request(ChiaEndpoint.COIN_RECORD_BY_PARENT_AND_HEIGHT, data);
            byte[] resp = client.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return newResponse(respNode, "coin_record", CoinRecord.class , ChiaEndpoint.COIN_RECORD_BY_PARENT_AND_HEIGHT);
        } catch (IOException e) {
            throw new IllegalStateException("Bad return", e);
        }
    }
}
