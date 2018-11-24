package ICBCIBMContest.model;

public class CreateAccountParam {
    private String corpNo;
    private String corpSerno;
    private String outServiceCode;
    private String corpCisNo;
    private String corpMediumId;
    private String bindMedium;
    private String certType;
    private String certNo;
    private String custName;
    private String mobileNo;
    private String notifyAddr;

    public String getCorpNo() {
        return corpNo;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }

    public String getCorpSerno() {
        return corpSerno;
    }

    public void setCorpSerno(String corpSerno) {
        this.corpSerno = corpSerno;
    }

    public String getOutServiceCode() {
        return outServiceCode;
    }

    public void setOutServiceCode(String outServiceCode) {
        this.outServiceCode = outServiceCode;
    }

    public String getCorpCisNo() {
        return corpCisNo;
    }

    public void setCorpCisNo(String corpCisNo) {
        this.corpCisNo = corpCisNo;
    }

    public String getCorpMediumId() {
        return corpMediumId;
    }

    public void setCorpMediumId(String corpMediumId) {
        this.corpMediumId = corpMediumId;
    }

    public String getBindMedium() {
        return bindMedium;
    }

    public void setBindMedium(String bindMedium) {
        this.bindMedium = bindMedium;
    }

    public String getNotifyAddr() {
        return notifyAddr;
    }

    public void setNotifyAddr(String notifyAddr) {
        this.notifyAddr = notifyAddr;
    }
}
