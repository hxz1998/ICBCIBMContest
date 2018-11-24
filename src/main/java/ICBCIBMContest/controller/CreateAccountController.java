package ICBCIBMContest.controller;

import ICBCIBMContest.model.APITransmitObject;
import ICBCIBMContest.model.CreateAccountParam;
import ICBCIBMContest.services.CreateAccountService;
import com.alibaba.fastjson.JSON;
import com.icbc.api.IcbcApiException;
import com.icbc.api.response.SettlementAccountOpenResponseV1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/create")
public class CreateAccountController {

    private CreateAccountService createAccountService;

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value = "/createAccount", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String createAccount(@ModelAttribute CreateAccountParam param) {
        APITransmitObject<String> apiTransmitObject = new APITransmitObject<>();
        try {
            SettlementAccountOpenResponseV1 responseV1 = createAccountService.create(param);
            if (responseV1.isSuccess()) {
                apiTransmitObject.setStatus("0");
                apiTransmitObject.setMsg(responseV1.getCorpSerno());
                apiTransmitObject.setData(responseV1.getSmsSendNo());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(apiTransmitObject);
    }

    public CreateAccountService getCreateAccountService() {
        return createAccountService;
    }

    @Resource
    public void setCreateAccountService(CreateAccountService createAccountService) {
        this.createAccountService = createAccountService;
    }
}
