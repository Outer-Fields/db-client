package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Results(
        @JsonProperty("wins") int wins,
        @JsonProperty("losses") int losses
) {
}
