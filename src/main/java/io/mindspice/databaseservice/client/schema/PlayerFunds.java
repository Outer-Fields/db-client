package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record PlayerFunds(
        @JsonProperty("okra_token_amount") int okraTokens,
        @JsonProperty("potion_token_amount") int potionTokens,
        @JsonProperty("nft_drop_amount") int nftDrops,
        @JsonProperty("outr_token_amounts") int outrTokens
) { }
