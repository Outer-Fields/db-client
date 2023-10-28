package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class MintLog{
    private final String uuid;
    private final String address;
    @JsonProperty("nft_ids") private final List<String> nftIds = new ArrayList<>();

    public MintLog(String uuid, String targetAddress) {
        this.uuid = uuid;
        this.address = targetAddress;
    }

    public void addNftId(String launcher) {
        nftIds.add(launcher);
    }

    public String getUUID() {
        return uuid;
    }

    public String getAddress() {
        return address;
    }
    @JsonProperty("nft_ids")
    public List<String> getNftIds() {
        return nftIds;
    }
}
