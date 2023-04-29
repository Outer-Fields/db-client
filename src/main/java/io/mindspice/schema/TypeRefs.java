package io.mindspice.schema;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;


public class TypeRefs {
    public static final TypeReference<List<String>> STRING_LIST = new TypeReference<>() { };
    public static final TypeReference<Map<Integer, JsonNode>> PAWN_SET_MAP = new TypeReference<>() { };

}
