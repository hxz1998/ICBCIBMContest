package ICBCIBMContest.controller;

import ICBCIBMContest.constant.ResponseStatusCode;
import ICBCIBMContest.model.APITransmitObject;
import ICBCIBMContest.model.impl.SimpleQrRequestParam;
import ICBCIBMContest.services.QrGeneratorService;
import ICBCIBMContest.services.exception.ParamEmptyException;
import ICBCIBMContest.services.exception.ParamIllegalException;
import ICBCIBMContest.services.exception.ServerException;
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
        APITransmitObject<String> apiTransmitObject = null;
        try {
            APITransmitObject result = qrGeneratorService.getQrCode(param);
            String url = propertiesFactory.getPropertyValue("QR_GENERATOR_URL") + result.getData();
            apiTransmitObject = new APITransmitObject<>(ResponseStatusCode.OK, url, "转换成功");
        } catch (ParamIllegalException e) {
             apiTransmitObject = new APITransmitObject<>(ResponseStatusCode.ILLEGAL_PARAM,
                    "null", "参数非法");
        } catch (ParamEmptyException e) {
            apiTransmitObject = new APITransmitObject<>(ResponseStatusCode.ILLEGAL_PARAM,
                    "null", "参数不完整");
        } catch (ServerException e) {
            apiTransmitObject = new APITransmitObject<>(ResponseStatusCode.SERVER_ERROR,
                    "null", "服务器错误");
        } finally {
            System.out.println(APITransmitObjectFactory.getJSON(apiTransmitObject));
            return APITransmitObjectFactory.getJSON(apiTransmitObject);
        }
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
