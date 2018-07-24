package ICBCIBMContest.controller;

import ICBCIBMContest.model.SimpleQrRequestParam;
import ICBCIBMContest.services.QrGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/qr")
public class QrRCodeGenerateController {

    private QrGeneratorService qrGeneratorService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "HELLO KETTY";
    }

    @RequestMapping("/getQrCode")
    @ResponseBody
    public String getQrCode(@ModelAttribute SimpleQrRequestParam param) {
        System.out.println(param);
        return "http://qr.topscan.com/api.php?text=" + qrGeneratorService.getQrCode(param);
    }

    public QrGeneratorService getQrGeneratorService() {
        return qrGeneratorService;
    }

    @Resource
    public void setQrGeneratorService(QrGeneratorService qrGeneratorService) {
        this.qrGeneratorService = qrGeneratorService;
    }
}
