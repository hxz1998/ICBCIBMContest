package org.xiaozhong.ICBCIBMContest.services;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.QrcodeGenerateRequestV2;
import com.icbc.api.response.QrcodeGenerateResponseV2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xiaozhong.ICBCIBMContest.model.QrRequestParam;
import org.xiaozhong.ICBCIBMContest.model.SimpleQrRequestParam;
import org.xiaozhong.ICBCIBMContest.util.PropertiesFactory;
import org.xiaozhong.ICBCIBMContest.util.impl.SimplePropertiesFactory;

@Service
@Scope("prototype")
public class QrGeneratorService {

    private static PropertiesFactory propertiesFactory;

    private QrRequestParam qrRequestParam;
    private String APP_ID;
    private String MY_PRIVATE_KEY;
    private String APIGW_PUBLIC_KEY;
    private String SERVER_URL;

    public QrGeneratorService() {
        propertiesFactory = SimplePropertiesFactory.getFactory();
        APP_ID = propertiesFactory.getPropertyValue("APP_ID");
        MY_PRIVATE_KEY = propertiesFactory.getPropertyValue("MY_PRIVATE_KEY");
        APIGW_PUBLIC_KEY = propertiesFactory.getPropertyValue("APIGW_PUBLIC_KEY");
        SERVER_URL = propertiesFactory.getPropertyValue("SERVER_URL");
    }

    public String getQrCode(QrRequestParam qrRequestParam) {
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
