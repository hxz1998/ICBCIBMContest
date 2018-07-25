package ICBCIBMContest.controller;

import ICBCIBMContest.model.SimpleQrRequestParam;
import ICBCIBMContest.services.QrGeneratorService;
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


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "HELLO KETTY";
    }

    @CrossOrigin("http://localhost:63343")
    @RequestMapping(value = "/getQrCode", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getQrCode(@ModelAttribute SimpleQrRequestParam param) {
        System.out.println(param);
        return propertiesFactory.getPropertyValue("QR_GENERATOR_URL") + qrGeneratorService.getQrCode(param);
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
}
