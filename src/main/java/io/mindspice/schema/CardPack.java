package io.mindspice.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record CardPack(
        @JsonProperty("launcher_id") String launcherId,
        @JsonProperty("pack_type") String packType // FIXME enum?
) {
}
