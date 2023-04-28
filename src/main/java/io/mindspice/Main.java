package io.mindspice;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws Exception {
        DBServiceClient.init(Settings.getUser(), Settings.getPassword());

        DBServiceClient.setAddress("https://localhost:9191");
        var req = new Request("/test", JsonUtils.newSingleNodeAsBytes("ping", "ping"));
        var response = DBServiceClient.makeRequest(req);
        System.out.println(response.length);
        System.out.println(JsonUtils.readTree(response));
    }


}
