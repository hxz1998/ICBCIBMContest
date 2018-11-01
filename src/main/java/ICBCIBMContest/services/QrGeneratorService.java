package ICBCIBMContest.services;

import ICBCIBMContest.model.APITransmitObject;
import ICBCIBMContest.model.QrRequestParam;
import ICBCIBMContest.model.impl.SimpleQrRequestParam;
import ICBCIBMContest.services.exception.ApiServersException;
import ICBCIBMContest.services.exception.ParamEmptyException;
import ICBCIBMContest.services.exception.ParamIllegalException;
import ICBCIBMContest.util.PropertiesFactory;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.QrcodeGenerateRequestV2;
import com.icbc.api.response.QrcodeGenerateResponseV2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 二维码生成器服务组件
 * <p>
 * 依赖于 PropertiesFactory
 *
 * @author Mr.hu
 */

@Component
public class QrGeneratorService {

    /**
     * 属性读取工厂，从 application.properties
     * 从配置文件 application.properties 读取APP_ID、PRIVATE_KEY、PUBLIC_KEY、SERVER_URL
     *
     * @see ICBCIBMContest.util.impl.SimplePropertiesFactory 提供了直接从IO流读取的实现
     */
    private PropertiesFactory propertiesFactory;

    /**
     * 参数传递域模型，封装要求为 true 的参数
     *
     * @see QrRequestParam
     */
    private QrRequestParam qrRequestParam;

    /**
     * ICBC开放接口应用所需认证参数
     */
    private String APP_ID;
    private String MY_PRIVATE_KEY;
    private String APIGW_PUBLIC_KEY;
    private String SERVER_URL;

    /**
     * 二维码生成第三方服务API
     */
    private String QR_GENERATOR_URL;

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
        QR_GENERATOR_URL = propertiesFactory.getPropertyValue("QR_GENERATOR_URL");
    }

    /**
     * 二维码生成方法
     * <p>
     * 使用前需要先初始化（不需要调用者实现）
     * 根据传入的参数调用 API 服务生成 API 传输对象并返回
     * 调用者可以解析 API 传输对象属性来获取所需内容
     *
     * @param qrRequestParam 生成二维码所必要的参数
     * @return API传输对象，封装了 ICBC 提供所 API 的返回的 returnMsg、returnCode、returnQrCode 字段
     * @throws ParamEmptyException   参数不完整异常
     * @throws ParamIllegalException 参数不合法异常
     * @throws ApiServersException   API服务器异常
     * @see APITransmitObject
     * @see ICBCIBMContest.util.APITransmitObjectFactory
     */
    public APITransmitObject getQrCode(QrRequestParam qrRequestParam) throws ParamEmptyException,
            ParamIllegalException, ApiServersException {
        //填充配置文件内容
        if (APP_ID == null) {
            init();
        }
        //验证参数是否合法
        ((SimpleQrRequestParam) qrRequestParam).verify();
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);
        QrcodeGenerateRequestV2 request = new QrcodeGenerateRequestV2();
        //填充服务器地址
        request.setServiceUrl(SERVER_URL + "/qrcode/V2/generate");
        QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz bizContent =
                new QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz();

        //强制转换为参数的实现
        SimpleQrRequestParam param = (SimpleQrRequestParam) qrRequestParam;

        //填充所需参数
        bizContent.setMerId(param.getMerId());
        bizContent.setStoreCode(param.getStoreCode());
        bizContent.setOutTradeNo(param.getOutTradeNo());
        bizContent.setOrderAmt(param.getOrderAmt());
        bizContent.setTradeDate(param.getTradeDate());
        bizContent.setTradeTime(param.getTradeTime());
        bizContent.setPayExpire(param.getPayExpire());
        bizContent.setTporderCreateIp(param.getTporderCreateIp());
        bizContent.setNotifyFlag(param.getNotifyFlag());
        request.setBizContent(bizContent);

        //获取响应
        QrcodeGenerateResponseV2 response = new QrcodeGenerateResponseV2();
        APITransmitObject<String> apiTransmitObject = null;
        try {
            //消息ID暂不提供具体生成策略
            response = client.execute(request, "1");
            /*
             * 此处暂不区分成功处理和失败处理的处理方式
             * 在 Controller 层将结合返回码进行处理，该处仅进行简单的数据封装
             */
            apiTransmitObject = new APITransmitObject<>(response.getReturnCode() + "",
                    response.getQrcode(), response.getReturnMsg());
            return apiTransmitObject;
        } catch (IcbcApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    public QrRequestParam getQrRequestParam() {
        return qrRequestParam;
    }

    public String getQR_GENERATOR_URL() {
        return QR_GENERATOR_URL;
    }

    public void setQR_GENERATOR_URL(String QR_GENERATOR_URL) {
        this.QR_GENERATOR_URL = QR_GENERATOR_URL;
    }

    @Resource
    public void setPropertiesFactory(PropertiesFactory propertiesFactory) {
        this.propertiesFactory = propertiesFactory;
    }

    public void setQrRequestParam(QrRequestParam qrRequestParam) {
        this.qrRequestParam = qrRequestParam;
    }

}
