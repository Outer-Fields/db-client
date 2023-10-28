package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record RewardDispersal(
        @JsonProperty("player_id") int playerId,
        @JsonProperty("okra_tokens") int okraTokens,
        @JsonProperty("outr_tokens") int outrTokens,
        @JsonProperty("nft_drops") int nftDrops
) { }
