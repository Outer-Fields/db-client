package io.mindspice.api;

import io.mindspice.endpoints.Endpoint;

import java.util.Optional;


public record ApiResponse<T>(
        Optional<T> data,
        boolean success,
        boolean error,
        String error_msg,
        String requestURI,
        Endpoint endpoint
) {
}