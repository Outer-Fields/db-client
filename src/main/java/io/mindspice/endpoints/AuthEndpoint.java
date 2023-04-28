package io.mindspice.endpoints;

public enum AuthEndpoint implements Endpoint{
    USER_EXISTS,
    REGISTER_USER,
    GET_CREDENTIALS,
    SET_FUND_ADDRESS;

    @Override
    public String getPath() {
        return "/auth/" + name().toLowerCase();
    }
}
