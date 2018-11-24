package ICBCIBMContest.controller;

import ICBCIBMContest.model.APITransmitObject;
import ICBCIBMContest.model.CreateAccountParam;
import ICBCIBMContest.services.CreateAccountService;
import com.alibaba.fastjson.JSON;
import com.icbc.api.response.SettlementAccountOpenResponseV1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 该类接收来自前端传来的参数进行结算账户开户
 * 该类下的完整调用路径为：<pre>http://server_ip:port/ifinance/create/*</pre>
 * 直接服务该控制器的服务组件为Spring注入的CreateAccountService组件
 * 接收的参数经过封装为CreateAccountParam传入，各项所需属性均从该对象中读取
 *
 * @author XiaozhongHu
 * @see CreateAccountService,CreateAccountParam
 */
@Controller
@RequestMapping("/create")
public class CreateAccountController {

    /**
     * 服务于该控制器的服务组件，提供控制器所需的各项方法
     *
     * @see CreateAccountService
     */
    private CreateAccountService createAccountService;

    /**
     * 接收前端请求的映射方法，默认使用utf-8作为字符集进行字符映射
     * 前后端ContentType为application/json
     * 经过参数检查和校验之后使用service组件进行API调用
     * 特殊的跨域列表作为调试使用
     * <p>
     * 需要的请求参数：
     * <pre>{
     * @code
     * param, 创建卡号所需的一系列数据
     * }
     * </pre>
     * <p>
     * 返回数据格式：
     * <pre>{
     * @code
     * json对象:{
     *  data: "", 正常返回的结果: 短信验证码编号; 非正确结果: 带回交易码; 异常结果: 带回空值
     *  msg: "", 正常返回结果: 交易单号; 非正确结果: 带回ICBC原始异常信息提示; 异常结果: 一些异常提示?
     *  status: "", 正常返回的结果: 0;  非正确结果: 带回ICBC开放接口原始错误码; 异常结果: 带回 -1;
     *  }
     * }
     *
     * </pre>
     *
     * @param param 前端传入的各项请求参数，必填项
     * @return 经过再次封装的json格式返回参数
     */
    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value = "/createAccount", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String createAccount(@ModelAttribute CreateAccountParam param) {
        APITransmitObject<String> apiTransmitObject = new APITransmitObject<>();
        SettlementAccountOpenResponseV1 responseV1 = createAccountService.create(param);
        if (responseV1 != null && responseV1.isSuccess()) {
            apiTransmitObject.setStatus("0");
            apiTransmitObject.setMsg(responseV1.getCorpSerno());
            apiTransmitObject.setData(responseV1.getSmsSendNo());
        } else if (responseV1 == null) {
            apiTransmitObject.setStatus(-1 + "");
            apiTransmitObject.setMsg("服务器内部出错");
            apiTransmitObject.setData("");
        } else {
            apiTransmitObject.setData(responseV1.getCorpSerno());
            apiTransmitObject.setMsg(responseV1.getReturnMsg());
            apiTransmitObject.setStatus(responseV1.getReturnCode() + "");
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
