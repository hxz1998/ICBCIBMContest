package ICBCIBMContest.model.impl;

import ICBCIBMContest.model.QrRequestParam;

public class SimpleQrRequestParam implements QrRequestParam {

    //商户线下档案编号
    private String merId;
    //e生活档案编号
    private String storeCode;
    //商户系统订单号
    private String outTradeNo;
    //订单总金额，单位：分
    private String orderAmt;
    //订单生成日期，格式yyyyMMdd
    private String tradeDate;
    //订单生成时间，格式HHmmss
    private String tradeTime;
    //二维码有效期，单位：秒
    private String payExpire;
    //商户订单生成的机器IP
    private String tporderCreateIp;
    //商户是否开启通知接口，1-是
    private String notifyFlag;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getPayExpire() {
        return payExpire;
    }

    public void setPayExpire(String payExpire) {
        this.payExpire = payExpire;
    }

    public String getTporderCreateIp() {
        return tporderCreateIp;
    }

    public void setTporderCreateIp(String tporderCreateIp) {
        this.tporderCreateIp = tporderCreateIp;
    }

    public String getNotifyFlag() {
        return notifyFlag;
    }

    public void setNotifyFlag(String notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    @Override
    public String toString() {
        return "SimpleQrRequestParam{" +
                "merId='" + merId + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", orderAmt='" + orderAmt + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                ", payExpire='" + payExpire + '\'' +
                ", tporderCreateIp='" + tporderCreateIp + '\'' +
                ", notifyFlag='" + notifyFlag + '\'' +
                '}';
    }
}
