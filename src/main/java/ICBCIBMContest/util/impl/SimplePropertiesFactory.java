package ICBCIBMContest.util.impl;

import ICBCIBMContest.util.PropertiesFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 直接从 IO 流读取配置文件属性
 *
 * @author Mr.Hu
 */
@Component("propertiesFactory")
@Scope
public class SimplePropertiesFactory implements PropertiesFactory {

    private Properties properties;

    public SimplePropertiesFactory() {
        properties = new Properties();
        try {
            properties.load(new ClassPathResource("/ICBCIBMContest/application.properties").getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
