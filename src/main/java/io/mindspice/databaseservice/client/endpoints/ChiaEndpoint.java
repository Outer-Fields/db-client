package io.mindspice.databaseservice.client.endpoints;

public enum ChiaEndpoint implements Endpoint {
    COIN_RECORDS_BY_HEIGHT,
    COIN_RECORDS_BY_PUZZLEHASH,
    COIN_RECORD_BY_NAME, COIN_RECORD_BY_PARENT_AND_HEIGHT;


    @Override
    public String getPath() {
        return "/chia/" + name().toLowerCase();
    }
}
