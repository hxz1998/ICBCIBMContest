package ICBCIBMContest.controller;

import ICBCIBMContest.constant.ResponseStatusCode;
import ICBCIBMContest.model.APITransmitObject;
import ICBCIBMContest.model.impl.SimpleQrRequestParam;
import ICBCIBMContest.services.QrGeneratorService;
import ICBCIBMContest.util.APITransmitObjectFactory;
import ICBCIBMContest.util.PropertiesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/qr")
public class QrRCodeGenerateController {

    private QrGeneratorService qrGeneratorService;
    private PropertiesFactory propertiesFactory;
    private APITransmitObjectFactory apiTransmitObjectFactory;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "HELLO KETTY";
    }

    @CrossOrigin("http://localhost:63342")
    @RequestMapping(value = "/getQrCode", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getQrCode(@ModelAttribute SimpleQrRequestParam param) {
        String result = qrGeneratorService.getQrCode(param);
        if (result == null) {
            APITransmitObject<String> apiTransmitObject = new APITransmitObject<>(ResponseStatusCode.ILLEGAL_PARAM, "转换失败", "转换失败");
            return APITransmitObjectFactory.getJSON(apiTransmitObject);
        }
        String url = propertiesFactory.getPropertyValue("QR_GENERATOR_URL") + qrGeneratorService.getQrCode(param);
        APITransmitObject<String> apiTransmitObject = new APITransmitObject<>(ResponseStatusCode.OK, url, "转换成功");
        return APITransmitObjectFactory.getJSON(apiTransmitObject);
    }

    public QrGeneratorService getQrGeneratorService() {
        return qrGeneratorService;
    }

    @Resource
    public void setQrGeneratorService(QrGeneratorService qrGeneratorService) {
        this.qrGeneratorService = qrGeneratorService;
    }

    public PropertiesFactory getPropertiesFactory() {
        return propertiesFactory;
    }

    @Resource
    public void setPropertiesFactory(PropertiesFactory propertiesFactory) {
        this.propertiesFactory = propertiesFactory;
    }

    public APITransmitObjectFactory getApiTransmitObjectFactory() {
        return apiTransmitObjectFactory;
    }

    @Resource
    public void setApiTransmitObjectFactory(APITransmitObjectFactory apiTransmitObjectFactory) {
        this.apiTransmitObjectFactory = apiTransmitObjectFactory;
    }
}
