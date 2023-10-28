package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Credential(
        @JsonProperty("player_id") int playerId,
        @JsonProperty("display_name") String displayName,
        @JsonProperty("passhash") String passHash
) {
}
