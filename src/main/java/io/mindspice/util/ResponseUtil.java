package io.mindspice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.DBServiceClient;
import io.mindspice.JsonUtils;
import io.mindspice.api.ApiResponse;
import io.mindspice.endpoints.Endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ResponseUtil {

    public static <T> ApiResponse<T> newResponse(JsonNode jsonNode, String dataField, Class<T> type,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<T> data = success
                ? Optional.ofNullable(JsonUtils.readJson(jsonNode.get(dataField), type))
                : Optional.empty();

        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static ApiResponse<JsonNode> newResponseNode(JsonNode jsonNode, String dataField,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<JsonNode> data = success
                ? Optional.ofNullable(jsonNode.get(dataField))
                : Optional.empty();

        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static ApiResponse<JsonNode> newResponseNode(JsonNode jsonNode, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<JsonNode> data = success
                ? Optional.of(jsonNode)
                : Optional.empty();

        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T> ApiResponse<T> newResponse(JsonNode jsonNode, Class<T> type,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<T> data = success
                ? Optional.ofNullable(JsonUtils.readJson(jsonNode, type))
                : Optional.empty();

        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T> ApiResponse<List<T>> newResponseList(JsonNode jsonNode, String dataField,
            TypeReference<List<T>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<List<T>> data;
        if (success) {
            var list = JsonUtils.readJson(jsonNode.get(dataField).traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableList(list));
        } else {
            data = Optional.empty();
        }
        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T> ApiResponse<List<T>> newResponseList(JsonNode jsonNode,
            TypeReference<List<T>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<List<T>> data;
        if (success) {
            var list = JsonUtils.readJson(jsonNode.traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableList(list));
        } else {
            data = Optional.empty();
        }
        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T> ApiResponse<List<T>> newResponseList(JsonNode jsonNode, List<T> list,
            Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<List<T>> data = Optional.of(Collections.unmodifiableList(list));
        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T, U> ApiResponse<Map<T, U>> newResponseMap(JsonNode jsonNode, String dataField,
            TypeReference<Map<T, U>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<Map<T, U>> data;
        if (success) {
            var map = JsonUtils.readJson(jsonNode.get(dataField).traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableMap(map));
        } else {
            data = Optional.empty();
        }
        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T, U> ApiResponse<Map<T, U>> newResponseMap(JsonNode jsonNode,
            TypeReference<Map<T, U>> typeRef, Endpoint endpoint) throws IOException {

        var success = jsonNode.get("success").asBoolean();
        Optional<Map<T, U>> data;
        if (success) {
            var map = JsonUtils.readJson(jsonNode.traverse(), typeRef);
            data = Optional.of(Collections.unmodifiableMap(map));
        } else {
            data = Optional.empty();
        }
        return new ApiResponse<>(
                data,
                success,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T> ApiResponse<T> newResponse(T object, Endpoint endpoint) throws IOException {
        return new ApiResponse<>(
                Optional.of(object),
                true,
                false,
                "",
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }

    public static <T> ApiResponse<T> newFailedResponse(JsonNode jsonNode, Endpoint endpoint) {
        return new ApiResponse<>(
                Optional.empty(),
                false,
                jsonNode.get("error").asBoolean(),
                jsonNode.get("error_msg").asText(),
                DBServiceClient.getAddress() + endpoint.getPath(),
                endpoint
        );
    }
}
