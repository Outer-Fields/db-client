package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record PlayerInfo (
        @JsonProperty("display_name") String displayName,
        @JsonProperty("avatar") String avatar
) {}
