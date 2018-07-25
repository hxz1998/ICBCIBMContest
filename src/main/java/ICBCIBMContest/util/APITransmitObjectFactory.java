package ICBCIBMContest.util;

import ICBCIBMContest.model.APITransmitObject;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class APITransmitObjectFactory {

    public static String getJSON(@NotNull APITransmitObject apiTransmitObject) {
        return JSON.toJSONString(apiTransmitObject);
    }
}
