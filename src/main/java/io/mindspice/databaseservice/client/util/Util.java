package io.mindspice.databaseservice.client.util;

public class Util {

    public static String normalizeHex(String hexString) {
        if (hexString == null || hexString.equals("null") || hexString.isEmpty()) {
            return hexString; // need to handle empty/null values for dids
        }
        if (hexString.length() < 64 || hexString.length() > 66) {
            throw new IllegalArgumentException("Hex mus be 64-66 length string");
        }
        return hexString.length() == 64 ? "0x" + hexString : hexString;
    }
}
