package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;


public record CardAndAccountCheck(
        @JsonProperty("existing_cards") List<String> existingCards,
        @JsonProperty("existing_accounts") List<AccountDid> existingAccounts
) {
    public CardAndAccountCheck {
        existingCards = existingCards == null ? List.of() : Collections.unmodifiableList(existingCards);
        existingAccounts = existingAccounts == null ? List.of() : Collections.unmodifiableList(existingAccounts);
    }
}
