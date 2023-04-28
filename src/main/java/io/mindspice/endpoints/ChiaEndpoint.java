package io.mindspice.endpoints;

public enum ChiaEndpoint implements Endpoint {
    COIN_REMOVALS,
    COIN_ADDITIONS;

    @Override
    public String getPath() {
        return "/chia/" + name().toLowerCase();
    }
}
