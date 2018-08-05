package ICBCIBMContest.util.impl;

import ICBCIBMContest.util.PropertiesFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 使用 SpringBoot 方式进行属性读取
 *
 * @author Mr.Hu
 */
public class DirectPropertiesFactory implements PropertiesFactory {

    @Value("${APP_ID}")
    private String APP_ID;
    @Value("${MY_PRIVATE_KEY}")
    private String MY_PRIVATE_KEY;
    @Value("${APIGW_PUBLIC_KEY}")
    private String APIGW_PUBLIC_KEY;
    @Value("${SERVER_URL}")
    private String SERVER_URL;

    private Properties properties = new Properties();;

    public DirectPropertiesFactory() {
        properties.put("APP_ID", APP_ID);
        properties.put("MY_PRIVATE_KEY", MY_PRIVATE_KEY);
        properties.put("APIGW_PUBLIC_KEY", APIGW_PUBLIC_KEY);
        properties.put("SERVER_URL", SERVER_URL);

    }

    @Override
    public Properties getProperty() {
        return properties;
    }

    @Override
    public String getPropertyValue(String name) {
        return properties.getProperty(name);
    }

}
