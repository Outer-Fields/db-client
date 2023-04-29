package io.mindspice;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws Exception {
        DBServiceClient.init("https://localhost:9191", "user", "password");

        var req = new Request("/test", JsonUtils.newSingleNodeAsBytes("ping", "ping"));
        var response = DBServiceClient.makeRequest(req);
        System.out.println(response.length);
        System.out.println(JsonUtils.readTree(response));
    }


}
