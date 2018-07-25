package ICBCIBMContest.services;

import ICBCIBMContest.model.QrRequestParam;
import ICBCIBMContest.model.impl.SimpleQrRequestParam;
import ICBCIBMContest.util.PropertiesFactory;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.QrcodeGenerateRequestV2;
import com.icbc.api.response.QrcodeGenerateResponseV2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QrGeneratorService {

    private PropertiesFactory propertiesFactory;

    private QrRequestParam qrRequestParam;
    private String APP_ID;
    private String MY_PRIVATE_KEY;
    private String APIGW_PUBLIC_KEY;
    private String SERVER_URL;
    private String QR_GENERATOR_URL;

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

    private void init() {
        APP_ID = propertiesFactory.getPropertyValue("APP_ID");
        MY_PRIVATE_KEY = propertiesFactory.getPropertyValue("MY_PRIVATE_KEY");
        APIGW_PUBLIC_KEY = propertiesFactory.getPropertyValue("APIGW_PUBLIC_KEY");
        SERVER_URL = propertiesFactory.getPropertyValue("SERVER_URL");
        QR_GENERATOR_URL = propertiesFactory.getPropertyValue("QR_GENERATOR_URL");
    }

    public String getQrCode(QrRequestParam qrRequestParam) {

        //填充配置文件内容
        if (APP_ID == null || MY_PRIVATE_KEY == null || APIGW_PUBLIC_KEY == null || SERVER_URL == null) {
            init();
        }
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        QrcodeGenerateRequestV2 request = new QrcodeGenerateRequestV2();
        request.setServiceUrl(SERVER_URL);
        QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz bizContent = new QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz();
        SimpleQrRequestParam param = (SimpleQrRequestParam) qrRequestParam;
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
        QrcodeGenerateResponseV2 response = new QrcodeGenerateResponseV2();
        try {
            response = client.execute(request, "12");
            if (response.isSuccess()) {
                // 业务成功处理
                return response.getQrcode();
            } else {
                // 失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
                return null;
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
