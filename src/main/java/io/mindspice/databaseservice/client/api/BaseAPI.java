package io.mindspice.databaseservice.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.databaseservice.client.DBServiceClient;
import io.mindspice.databaseservice.client.endpoints.Endpoint;
import io.mindspice.mindlib.util.JsonUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class BaseAPI {
    protected final DBServiceClient client;

    public BaseAPI(DBServiceClient client) { this.client = client; }

    public <T> APIResponse<T> newResponse(JsonNode jsonNode, String dataField, Class<T> type,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<T> data = success
                ? Optional.ofNullable(JsonUtils.readJson(jsonNode.get(dataField), type))
                : Optional.empty();

        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public APIResponse<JsonNode> newResponseNode(JsonNode jsonNode, String dataField,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<JsonNode> data = success
                ? Optional.ofNullable(jsonNode.get(dataField))
                : Optional.empty();

        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public APIResponse<JsonNode> newResponseNode(JsonNode jsonNode, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<JsonNode> data = success
                ? Optional.of(jsonNode)
                : Optional.empty();

        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T> APIResponse<T> newResponse(JsonNode jsonNode, Class<T> type,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<T> data = success
                ? Optional.ofNullable(JsonUtils.readJson(jsonNode, type))
                : Optional.empty();

        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T> APIResponse<List<T>> newResponseList(JsonNode jsonNode, String dataField,
            TypeReference<List<T>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<List<T>> data;
        if (success) {
            var list = JsonUtils.readJson(jsonNode.get(dataField).traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableList(list));
        } else {
            data = Optional.empty();
        }
        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T> APIResponse<List<T>> newResponseList(JsonNode jsonNode,
            TypeReference<List<T>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<List<T>> data;
        if (success) {
            var list = JsonUtils.readJson(jsonNode.traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableList(list));
        } else {
            data = Optional.empty();
        }
        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T> APIResponse<List<T>> newResponseList(JsonNode jsonNode, List<T> list,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<List<T>> data = Optional.of(Collections.unmodifiableList(list));
        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T, U> APIResponse<Map<T, U>> newResponseMap(JsonNode jsonNode, String dataField,
            TypeReference<Map<T, U>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<Map<T, U>> data;
        if (success) {
            var map = JsonUtils.readJson(jsonNode.get(dataField).traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableMap(map));
        } else {
            data = Optional.empty();
        }
        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T, U> APIResponse<Map<T, U>> newResponseMap(JsonNode jsonNode,
            TypeReference<Map<T, U>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<Map<T, U>> data;
        if (success) {
            var map = JsonUtils.readJson(jsonNode.traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableMap(map));
        } else {
            data = Optional.empty();
        }
        return new APIResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T> APIResponse<T> newResponse(T object, Endpoint endpoint) throws IOException {
        return new APIResponse<>(
                Optional.of(object),
                true,
                false,
                "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public <T> APIResponse<T> newFailedResponse(JsonNode jsonNode, Endpoint endpoint) {
        return new APIResponse<>(
                Optional.empty(),
                false,
                jsonNode.get("error").asBoolean(),
                jsonNode.has("error_msg") ?  jsonNode.get("error_msg").asText() : "",
                client.getAddress() + endpoint.getPath(),
                endpoint
        );
    }
}
