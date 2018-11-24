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

/**
 * 该类为二维码生成控制器层核心类，接收前端传来的数据并返回封装了响应的 API 传输对象
 * 依赖于 QrGeneratorService PropertiesFactory APITransitObjectFactory
 *
 * <pre> {@code
 *      success:
 *      {
 *          data: "http://qr.topscan.com/api.php?text=示例值详见响应示例",
 *          msg: "转换成功",
 *          status: "0"
 *      },
 *      fail:
 *      {
 *          data: "null",
 *          msg: "参数不完整",
 *          status: "400011"
 *      }
 * }</pre>
 *
 * @author XiaozhongHu
 * @see QrGeneratorService
 * @see PropertiesFactory
 * @see APITransmitObjectFactory
 */
@Controller
@RequestMapping("/qr")
public class QrRCodeGenerateController {

    /**
     * 依赖项 调用ICBC提供的API服务
     */
    private QrGeneratorService qrGeneratorService;

    /**
     * 依赖项 读取配置文件中的属性
     */
    private PropertiesFactory propertiesFactory;

    /**
     * API 传输对象生成工厂
     */
    private APITransmitObjectFactory apiTransmitObjectFactory;

    /**
     * 控制器生成二维码的简单实现方法
     * 根据传进来的必填参数进行调用 QrGeneratorService 进行服务
     * @param param 必要的参数
     * @return 序列化过后的API传输对象
     */
    @CrossOrigin("http://localhost:8081")
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
        }
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
