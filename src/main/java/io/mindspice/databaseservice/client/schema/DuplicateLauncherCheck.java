package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record DuplicateLauncherCheck(
        @JsonProperty("is_valid") boolean isValid,
        @JsonProperty("launcher_id") String launcherId
) { }
