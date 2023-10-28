package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public record TransactionLog(
        String uuid,
        String address,
        long amount,
        @JsonProperty("coin_id") String coinId
){}


