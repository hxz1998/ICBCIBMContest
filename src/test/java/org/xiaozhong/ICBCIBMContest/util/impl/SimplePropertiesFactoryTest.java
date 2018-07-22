package org.xiaozhong.ICBCIBMContest.util.impl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class SimplePropertiesFactoryTest {

    @Test
    public void getFactory() {
        SimplePropertiesFactory factory = SimplePropertiesFactory.getFactory();
    }

    @Test
    public void getProperty() {
        SimplePropertiesFactory factory = SimplePropertiesFactory.getFactory();
        Properties prop = factory.getProperty();
        Assert.assertEquals("666", prop.getProperty("APP_ID_TEST"));
    }
}