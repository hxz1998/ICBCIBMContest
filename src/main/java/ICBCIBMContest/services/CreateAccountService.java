package ICBCIBMContest.services;

import ICBCIBMContest.model.CreateAccountParam;
import ICBCIBMContest.util.PropertiesFactory;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.SettlementAccountOpenRequestV1;
import com.icbc.api.response.SettlementAccountOpenResponseV1;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CreateAccountService {

    /**
     * ICBC开放接口应用所需认证参数
     */
    private String APP_ID;
    private String MY_PRIVATE_KEY;
    private String APIGW_PUBLIC_KEY;
    private String SERVER_URL;
    /**
     * 属性读取工厂，从 application.properties
     * 从配置文件 application.properties 读取APP_ID、PRIVATE_KEY、PUBLIC_KEY、SERVER_URL
     *
     * @see ICBCIBMContest.util.impl.SimplePropertiesFactory 提供了直接从IO流读取的实现
     */
    private PropertiesFactory propertiesFactory;

    /**
     * 服务组件初始化方法
     * <p>
     * 解决 Spring 容器初始化 bean 带来读取IO流的问题
     */
    private void init() {
        APP_ID = propertiesFactory.getPropertyValue("APP_ID");
        MY_PRIVATE_KEY = propertiesFactory.getPropertyValue("MY_PRIVATE_KEY");
        APIGW_PUBLIC_KEY = propertiesFactory.getPropertyValue("APIGW_PUBLIC_KEY");
        SERVER_URL = propertiesFactory.getPropertyValue("SERVER_URL");
    }

    public PropertiesFactory getPropertiesFactory() {
        return propertiesFactory;
    }

    @Resource
    public void setPropertiesFactory(PropertiesFactory propertiesFactory) {
        this.propertiesFactory = propertiesFactory;
    }

    /**
     * 结算账户开户服务
     * @param param 封装从前端传来的数据
     * @throws IcbcApiException API异常抛出
     */
    public SettlementAccountOpenResponseV1 create(CreateAccountParam param) throws IcbcApiException {//填充配置文件内容
        if (APP_ID == null) {
            init();
        }
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        SettlementAccountOpenRequestV1 request = new SettlementAccountOpenRequestV1();
        SettlementAccountOpenRequestV1.SettlementAccountOpenRequestV1Biz bizContent = new SettlementAccountOpenRequestV1.SettlementAccountOpenRequestV1Biz();
        request.setServiceUrl(SERVER_URL + "settlement/account/V1/open");
        bizContent.setAddress("");
        bizContent.setBindMedium(param.getBindMedium());
        bizContent.setBirthday("");
        bizContent.setCertNo(param.getCertNo());
        bizContent.setCertType(0);
        bizContent.setCorpCisNo("1");
        bizContent.setCorpDate("2017-03-15");
        bizContent.setCorpMediumId(param.getCorpMediumId());
        bizContent.setCorpNo(param.getCorpNo());
        bizContent.setCorpSerno(param.getCorpSerno());
        bizContent.setCustName(param.getCustName());
        bizContent.setCustomizationInfo("");
        bizContent.setGender(1);
        bizContent.setIssuingAuthority("");
        bizContent.setMobileNo(param.getMobileNo());
        bizContent.setNationality(0);
        bizContent.setNotifyAddr("aaaaabbbbb");
        bizContent.setOccupation(0);
        bizContent.setOutServiceCode(param.getOutServiceCode());
        bizContent.setTaxDeclarationFlag(0);
        bizContent.setTrxAccDate("2017-03-15");
        bizContent.setTrxAccTime("13:30:01");
        bizContent.setValidityPeriod("");
        bizContent.setSecretKey("ASDQWEQDZCSDFAWWQDA");
        bizContent.setBindMediumHash("SDFDFHTEWTGDFWADADAFSDGSESEFD");
        request.setBizContent(bizContent);
        SettlementAccountOpenResponseV1 response = client.execute(request, "msgId");
        if (response.isSuccess()) {
            System.out.println(response.getSmsSendNo());
        } else {
            System.out.println(response.getReturnMsg());
        }
        return response;

    }
}
//955888
