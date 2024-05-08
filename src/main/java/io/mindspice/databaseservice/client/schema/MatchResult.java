package io.mindspice.databaseservice.client.schema;

public record MatchResult(
        boolean player1Won,
        boolean player2Won,
        String player1Name,
        String player2Name
) { }
