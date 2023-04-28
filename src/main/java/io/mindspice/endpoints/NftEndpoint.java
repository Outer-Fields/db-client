package io.mindspice.endpoints;

public enum NftEndpoint implements Endpoint{
    CHECK_IF_CARD_EXISTS,
    CHECK_IF_PACK_EXISTS,
    CHECK_IF_ACCOUNT_EXISTS,
    UPDATE_NFT,
    ADD_NEW_NFT,
    GET_PLAYER_DID,
    UPDATE_NFT_DID,;

    @Override
    public String getPath() {
        return "/nft/" + name().toLowerCase();
    }
}
