import io.mindspice.databaseservice.client.DBServiceClient;
import io.mindspice.databaseservice.client.api.OkraGameAPI;
import io.mindspice.databaseservice.client.schema.MintLog;
import io.mindspice.jxch.rpc.util.JsonUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class test {

    @Test
    public static void test() throws Exception {
        MintLog mintLog = new MintLog("e423043423-42342-fsd", "txhcblexc");

        for (var item :  List.of("fgsdfdd", "fsdfsdf", "fsdfsdfsd")){
            mintLog.addNftId(item);
        }

        System.out.println(JsonUtils.writePretty(mintLog));
    }


}
