package io.mindspice.databaseservice.client.schema;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import io.mindspice.jxch.rpc.schemas.object.CoinRecord;

import java.util.List;
import java.util.Map;


public class TypeRefs {
    public static final TypeReference<List<String>> STRING_LIST = new TypeReference<>() { };
    public static final TypeReference<Map<Integer, String>> PAWN_SET_MAP = new TypeReference<>() { };
    public static final TypeReference<List<CoinRecord>> COIN_RECORD_LIST = new TypeReference<>() { };
    public static final TypeReference<List<Card>> CARD_LIST = new TypeReference<>() { };
    public static final TypeReference<List<RewardDispersal>> REWARD_LIST = new TypeReference<>() { };

}
