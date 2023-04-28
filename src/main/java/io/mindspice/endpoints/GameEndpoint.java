package io.mindspice.endpoints;

public enum GameEndpoint implements Endpoint{
    GET_PAWN_SET,
    UPDATE_PAWN_SET,
    DELETE_PAWN_SET,
    GET_POTION_TOKEN_AMOUNT,
    GET_PLAYER_FUNDS,
    COMMIT_POTION_PURCHASE,
    COMMIT_POTION_USE,
    COMMIT_MATCH_RESULT,
    COMMIT_PLAYER_REWARDS,
    GET_PLAYER_DAILY_RESULTS,
    GET_PLAYER_HISTORICAL_RESULTS,
    GET_PLAYER_CARDS;

    @Override
    public String getPath() {
        return "/game/" + name().toLowerCase();
    }
}
