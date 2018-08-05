package ICBCIBMContest.util;

import ICBCIBMContest.model.APITransmitObject;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 对 FastJSON 的封装类，提供静态方法进行获取 String 字符串
 *
 * @author Mr.Hu
 */
@Component
public class APITransmitObjectFactory {

    public static String getJSON(@NotNull APITransmitObject apiTransmitObject) {
        return JSON.toJSONString(apiTransmitObject);
    }
}
