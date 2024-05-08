package io.mindspice.databaseservice.client.endpoints;

public enum NftEndpoint implements Endpoint{
    CHECK_IF_CARD_EXISTS,
    CHECK_IF_PACK_EXISTS,
    CHECK_IF_ACCOUNT_EXISTS,
    CHECK_IF_EXISTS_BULK,
    UPDATE_NFT,
    ADD_NEW_CARD_NFT,
    ADD_NEW_ACCOUNT_NFT,
    GET_PLAYER_DID,
    UPDATE_CARD_DID,
    UPDATE_ACCOUNT_DID,
    CHECK_IF_ACCOUNT_NFT_EXISTS,
    GET_AND_INC_EDITION,
    GET_CARD_COLLECTION,
    UPDATE_PLAYER_XCH_ADDRESS,
    ADD_MINT_LOG,
    ADD_TRANSACTION_LOG,
    ADD_CARD_TO_COLLECTION,
    ADD_PACK_PURCHASE,
    ADD_FAILED_UPDATE,
    ADD_FAILED_MINT,
    ADD_FAILED_TRANSACTION;

    @Override
    public String getPath() {
        return "/nft/" + name().toLowerCase();
    }
}
