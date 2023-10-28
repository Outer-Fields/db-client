package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record AccountDid(
        @JsonProperty("launcher_id") String launcherId,
        @JsonProperty("did") String did
) { }
