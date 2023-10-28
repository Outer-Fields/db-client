package io.mindspice.databaseservice.client.endpoints;

public enum AuthEndpoint implements Endpoint{
    USER_EXISTS,
    REGISTER_USER,
    GET_CREDENTIALS,
    SET_FUND_ADDRESS,
    UPDATE_LAST_LOGIN,
    UPDATE_PLAYER_DID,
    UPDATE_PLAYER_PASSWORD,
    GET_PLAYER_ACCOUNT_LAUNCHER,
    GET_LAST_PASSWORD_RESET,
    DOES_NAME_EXIST,
    CHECK_IF_DID_EXISTS,
    CHECK_FOR_EXISTING_LAUNCHER,
    CHECK_FOR_DUPLICATE_LAUNCHER;

    @Override
    public String getPath() {
        return "/auth/" + name().toLowerCase();
    }
}
