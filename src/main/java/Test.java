import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.QrcodeGenerateRequestV2;
import com.icbc.api.response.QrcodeGenerateResponseV2;

public class Test {
    //网关公钥
    static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16\n" +
            "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL\n" +
            "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP\n" +
            "3CiNgg0W6O3AGqwIDAQAB";
    //appid
    static final String APP_ID = "IICAMP0000000043";
    //私钥
    protected static final String MY_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCk8QYzRC3UuEYcpHxyW3/DQ6m0318x92xtIpDsHIiEdaJHzh7hTgsE/1/F1xSqdY9xrBue/2NNH6Y5qqS0IqPwhnnya6cU6h4w6v20F+QJqMjBAWEhAoA8fw07/XeV1IZEa3vVgEttS7wfY4TMuKW7JnXFuDcGASOgcObivFY/1YDrZeQ+CAbmA1Uf7buUUkFnlsr3xlM6dJYsf08rnG3JXJyJPkpcFQCuOkwJaP3VV0DlTrMz6HJty8QUqIhJm8FRD94RLtcUz3FBeC4AzQ45hEVLvNCyx6M/qnS7/R2VTN2EQqmsFzd8n2gn+0QoiT28j2ylmPwShLheiOqT2EJPAgMBAAECggEATXLTW303G/xPB6IAwV3PdyutYz3DoF2yrugdLpETVUc7JQZXH6oaqIUVPnuIu6Wp7lFHQvhec1daIRfMcL0XR5iNaFQ+RybLeGLPMCJLtHFpmp8AMZTQDBgIadPRtCvvw+7IXe74++Ak/IQpVkuZnnAnuuiOBabBSNPaxjpzMVNW8nzW9s5Iko6No7+CUDd0JC0WTA2+XhO4eW2te8M5SUtHHr0QXWpcVXrgKKDnbG2COrAcfTbSwlN5BDNU8gQykopXCAx6r+nllyz6nK0Oge+I1C5EnkGLcLMx3vhOc/71UvxzwjY3nqUswCUw9UnWH14CG1Tre4czqV1MU2SEUQKBgQDwa9eH4k6RsEsKApNHZhm1ijeiVV68df0psjTgh2EeFSyoPeJqG2je/l4Ziqy/iuKrbUZu4RrX0614GfX6QWCKhOzU+8eLB8KOc59TmbdKKj57fJaiEaAmJCz5dWhaWAMNfNgNhiZK7+A46zTjLn3BVm01/KYDyrKrraZVaP+T5wKBgQCvoRu60sI5EJ/gJY37KIY52/sE63ptjGM0O1kbjEJB3XYfFkpjfSdWeOL2rWmgiQXk9IGhixHz8DWlM/zGikOcf8cUvluWDak8rGZpImGd5orjQmIDkOwtSOPO/x6c6284bKCE4gAMoQFXks9v9IoWzk2GyRoLr1x1jil4pwiRWQKBgBiJ2UC8P2qtRKtr4d5atGsMBWujLP0tSb6UusLj7mykmp89WbucupJwAei3d7RBF6uJhrPQc+3tY/orJCj7vT/GG2oNnKs7CoeBk5RzzvpZ33C5IaK/C8p4zMJVUmZOL1mUEXUn6Qai/w6wKwC5AQrXqGPnOoaOjLfrDFwRUGynAoGAKZPvd6QLfOz1nF/no+ztEeeFHp4MpsCUf7oJF192FBe0rhld1P29AVivCoz1M58cuR41TiQZRPG6EXkkP3BeHz+UxtFl5zkmtolt2Tfdej8o5DJD9+ooL3RREiENg0KtWuNEgRDryBGZuryzxSJUSp/edKlJNDj4n4/fO4P3oEkCgYEApCyAgfatj0/4hSLWKRi9pIVYFMB/9gL7/PhJFdAkmjhEF6Voy7Fqzik5e0Y4KFzOfPIlgyWfX1KPTna5BHdfgcImnaYSL6SEcfqkcocr2s+30oYDOONF1nlMnu1XplgjYmZ/pQ4j9+lS0r+mCcjB3+IeCPsGNC75T9H5b/j3FB8=";

    public static void main(String[] args) {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        QrcodeGenerateRequestV2 request = new QrcodeGenerateRequestV2();
        request.setServiceUrl("https://apisandbox.dccnet.com.cn/api/qrcode/V2/generate");
        QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz bizContent = new QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz();
        bizContent.setMerId("123456");
        bizContent.setStoreCode("123456");
        bizContent.setOutTradeNo("123456");
        bizContent.setOrderAmt("73341");
        bizContent.setTradeDate("2013241");
        bizContent.setTradeTime("16031");
        bizContent.setPayExpire("12432");
        bizContent.setTporderCreateIp("127.0.0.1");
        bizContent.setNotifyFlag("1");
        request.setBizContent(bizContent);
        QrcodeGenerateResponseV2 response = new QrcodeGenerateResponseV2();
        try {
            response = client.execute(request, "mm");
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
                System.out.println("return_id:" + response.getMsgId());
                System.out.println("return_msg:" + response.getReturnMsg());
                System.out.println("return qrcode:" + response.getQrcode());
            } else {
                // 失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
