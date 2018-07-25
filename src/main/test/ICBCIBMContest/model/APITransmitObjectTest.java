package ICBCIBMContest.model;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

public class APITransmitObjectTest {

    @Test
    public void getJSON() {
        APITransmitObject<String> apiTransmitObject = new APITransmitObject<String>("0", "Hello你好", "wu");
        Assert.assertEquals("{\"data\":\"Hello你好\",\"msg\":\"wu\",\"status\":\"0\"}", JSON.toJSONString(apiTransmitObject));
    }
}