package io.mindspice.databaseservice.client.schema;

import io.mindspice.jxch.rpc.schemas.wallet.nft.MetaData;


public record Card(
        String uid,
        int level,
        CardDomain domain,
        CardType type,
        boolean isGold,
        boolean isHolo,
        MetaData metaData
) {
    public Card cloneUidToMeta() {
        var appendedUid = metaData.metaUris();
        appendedUid.add(uid);
        MetaData newMeta = new MetaData(
                metaData.uris(),
                metaData.metaUris(),
                appendedUid,
                metaData.hash(),
                metaData.metaHash(),
                metaData.licenseHash(),
                metaData.editionNumber(),
                metaData.editionTotal()
        );
        return new Card(
                uid,
                level,
                domain,
                type,
                isGold,
                isHolo,
                newMeta
        );
    }

}

