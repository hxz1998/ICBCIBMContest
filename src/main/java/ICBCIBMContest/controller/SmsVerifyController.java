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

/**
 * 短信验证码验证控制器，不需要底层服务组件支持
 * 完整访问路径为：<pre>http://server_ip:port/ifinance/verify</pre>
 * <p>
 * 需要的请求参数：
 * <pre>{
 * @code
 * smsSendNo, 短信验证编号
 * corpSerNoOriginal, 原始交易单号
 * }
 * </pre>
 * <p>
 * 返回数据格式：
 * <pre>
 *     json对象:{
 *     data: "", 正常返回的结果: 工行联名卡号; 非正确结果: 带回交易码; 异常结果: 带回空值
 *     msg: "", 正常返回结果: ICBC原始业务成功处理的返回信息; 非正确结果: 带回ICBC原始异常信息提示; 异常结果: 一些异常提示?
 *     status: ""  正常返回的结果: 0;  非正确结果: 带回ICBC开放接口原始错误码; 异常结果: 带回 -1;
 * }
 * </pre>
 */
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

    /**
     * 负责接收短信验证信息，并且提供验证码校验结果
     * 特殊的跨域列表提供调试方便
     * 默认的字符集为utf-8， ContentType为application/json
     *
     * @param smsSendNo         要校验的短信编号
     * @param corpSerNoOriginal 原始的交易编号
     * @return 校验的结果，以json对象格式返回
     */
    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value = "/smsVerify", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String verify(String smsSendNo, String corpSerNoOriginal) {
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
        SettlementAccountSCodeVerifyResponseV1 response = null;
        try {
            response = client.execute(request, "msgId");
            if (response.isSuccess()) {
                // 业务成功处理
                apiTransmitObject.setStatus("" + response.getReturnCode());
                apiTransmitObject.setData(response.getMediumId());
                apiTransmitObject.setMsg(response.getReturnMsg());
            } else if (response == null) {
                apiTransmitObject.setMsg(response.getReturnMsg());
                apiTransmitObject.setData(response.getCorpSerno());
                apiTransmitObject.setStatus("" + response.getReturnCode());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
            apiTransmitObject.setStatus(-1 + "");
            apiTransmitObject.setMsg("系统异常");
            apiTransmitObject.setData(null);
        }
        return JSON.toJSONString(apiTransmitObject);
    }

    public PropertiesFactory getPropertiesFactory() {
        return propertiesFactory;
    }

    @Resource
    public void setPropertiesFactory(PropertiesFactory propertiesFactory) {
        this.propertiesFactory = propertiesFactory;
    }

}
