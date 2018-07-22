package org.xiaozhong.ICBCIBMContest.util.impl;

import org.xiaozhong.ICBCIBMContest.util.PropertiesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SimplePropertiesFactory implements PropertiesFactory {

    private static final SimplePropertiesFactory factory = new SimplePropertiesFactory();
    private Properties properties;

    public static SimplePropertiesFactory getFactory() {
        return factory;
    }

    private SimplePropertiesFactory() {
        properties = new Properties();
        try {
            FileReader reader = new FileReader("src/main/resources/application.properties");
            properties.load(reader);
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
