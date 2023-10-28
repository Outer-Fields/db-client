package io.mindspice.databaseservice.client.schema;

public record NftUpdate(
        String coinId,
        String launcherId,
        String did,
        long height,
        boolean collision
) {
    public NftUpdate(String coinId, String launcherId, String did, long height) {
        this(coinId, launcherId, did, height, false);
    }
}
