package io.mindspice.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.DBServiceClient;
import io.mindspice.JsonUtils;
import io.mindspice.Request;
import io.mindspice.endpoints.ChiaEndpoint;
import io.mindspice.schema.TypeRefs;
import io.mindspice.util.ResponseUtil;

import java.io.IOException;
import java.util.List;


public class ChiaApi {

    public static ApiResponse<List<String>> getAdditions(int height) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("height", height);
            Request req = new Request(ChiaEndpoint.COIN_ADDITIONS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponseList(
                    respNode, "coin_records", TypeRefs.STRING_LIST, ChiaEndpoint.COIN_ADDITIONS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }

    public static ApiResponse<List<String>> getRemovals(int height) {
        try {
            byte[] data = JsonUtils.newSingleNodeAsBytes("height", height);
            Request req = new Request(ChiaEndpoint.COIN_ADDITIONS, data);
            byte[] resp = DBServiceClient.makeRequest(req);
            JsonNode respNode = JsonUtils.readTree(resp);
            return ResponseUtil.newResponseList(
                    respNode, "coin_records", TypeRefs.STRING_LIST, ChiaEndpoint.COIN_REMOVALS
            );
        } catch (IOException e) {
            throw new IllegalStateException("Bad return");
        }
    }
}
