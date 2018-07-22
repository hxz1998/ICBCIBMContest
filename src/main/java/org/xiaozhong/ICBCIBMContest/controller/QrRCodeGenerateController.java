package org.xiaozhong.ICBCIBMContest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiaozhong.ICBCIBMContest.model.SimpleQrRequestParam;
import org.xiaozhong.ICBCIBMContest.services.QrGeneratorService;

@Controller
@RequestMapping("/qr")
public class QrRCodeGenerateController {

    @Autowired
    QrGeneratorService qrGeneratorService;

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
}
