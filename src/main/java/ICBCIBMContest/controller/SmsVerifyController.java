package ICBCIBMContest.controller;

import ICBCIBMContest.model.APITransmitObject;
import ICBCIBMContest.util.PropertiesFactory;
import com.alibaba.fastjson.JSON;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.SettlementAccountSCodeVerifyRequestV1;
import com.icbc.api.response.SettlementAccountSCodeVerifyResponseV1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
@RequestMapping("/verify")
public class SmsVerifyController {

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

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value = "/smsVerify", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String verify(String smsSendNo, String corpSerNoOriginal) throws IcbcApiException {
        if (APP_ID == null) {
            init();
        }
        APITransmitObject<String> apiTransmitObject = new APITransmitObject<>();
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        SettlementAccountSCodeVerifyRequestV1 request = new SettlementAccountSCodeVerifyRequestV1();
        SettlementAccountSCodeVerifyRequestV1.SettlementAccountSCodeVerifyRequestV1Biz bizContent = new SettlementAccountSCodeVerifyRequestV1.SettlementAccountSCodeVerifyRequestV1Biz();
        request.setServiceUrl(SERVER_URL + "settlement/account/scode/V1/verify");
        bizContent.setCorpDate("2017-03-15");
        bizContent.setCorpNo("IICAMP0000000809");
        bizContent.setCorpSerno(UUID.randomUUID().toString());//eyruqiwye3412
        bizContent.setCorpSernoOriginal(corpSerNoOriginal);
        bizContent.setOutServiceCode("1");
        bizContent.setTrxAccDate("2017-03-15");
        bizContent.setTrxAccTime("13:30:01");
        bizContent.setSmsSendNo(smsSendNo);
        bizContent.setSmsSCode("955888");
        request.setBizContent(bizContent);
        SettlementAccountSCodeVerifyResponseV1 response = client.execute(request, "msgId");
        if (response.isSuccess()) {
            // 业务成功处理
            System.out.println("success" + response.getReturnMsg());
            System.out.println(response.getSuccessFlag());
            System.out.println(response.getMediumId());
            apiTransmitObject.setStatus("" + response.getReturnCode());
            apiTransmitObject.setData(response.getMediumId());
            apiTransmitObject.setMsg(response.getReturnMsg());
        } else {
            System.out.println("error" + response.getReturnMsg());
            System.out.println(response.getReturnCode());
            apiTransmitObject.setMsg(response.getReturnMsg());
            apiTransmitObject.setData(response.getCorpSerno());
            apiTransmitObject.setStatus("" + response.getReturnCode());
        }
        return JSON.toJSONString(apiTransmitObject);
    }

}
