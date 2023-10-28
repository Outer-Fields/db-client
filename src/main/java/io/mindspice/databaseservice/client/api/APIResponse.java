package io.mindspice.databaseservice.client.api;

import io.mindspice.databaseservice.client.endpoints.Endpoint;

import java.util.Optional;


public record APIResponse<T>(
        Optional<T> data,
        boolean success,
        boolean error,
        String error_msg,
        String requestURI,
        Endpoint endpoint
) {
}