package io.mindspice.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Credential(
        @JsonProperty("player_id") int playerId,
        @JsonProperty("passhash") String passHash
) {
}
