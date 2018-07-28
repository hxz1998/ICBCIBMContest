package ICBCIBMContest.model.impl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleQrRequestParamTest {

    @Test
    public void verify() throws IllegalAccessException {
        SimpleQrRequestParam simpleQrRequestParam = new SimpleQrRequestParam();
        simpleQrRequestParam.setMerId("1");
        simpleQrRequestParam.setNotifyFlag("2");
        simpleQrRequestParam.setOrderAmt("2");
        simpleQrRequestParam.setOutTradeNo("2");
        simpleQrRequestParam.setPayExpire("2");
        simpleQrRequestParam.setStoreCode("2");
        simpleQrRequestParam.setTporderCreateIp("2");
        simpleQrRequestParam.setTradeDate("2");
        simpleQrRequestParam.setTradeTime("2");
        Assert.assertTrue(simpleQrRequestParam.verify());
    }
}