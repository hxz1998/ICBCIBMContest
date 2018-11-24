package ICBCIBMContest;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.SettlementAccountOpenRequestV1;
import com.icbc.api.response.SettlementAccountOpenResponseV1;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IcbcApiException {
//
//        String APP_ID = "IICAMP0000000809";
//        String MY_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzVPI24Bk+3UpiManMH1OQIfsG1MME6e5sW4B2F1o5MXqrFmZ3YT6VUMkEHz6mHyAGUfncFDdV3xQXBvp6RRyUQVgWc/GjFOM+qeE1j0Z4wab+xGBz1z64BQJXSyoY9eFuTpZuu8NBlWWOun2G8yqx37IYuy4F/Laz0Gxng+yVEIpFSs/MHF5C7EWLlvBUfdqAndptd3qAI3MZ8fnKDfYkjgXV2vm2UUMECYmJNGM7uwBGBpX6RttO2t3hVQdRBlDd9ZREIRvtvGt5o7U6L/C6kSl8hUsF2ydW8oSYUnOiPwk7x+w9tgVNxAkdAHtH0U1Z90nJ7oDfm1fmPMjwaP4RAgMBAAECggEABmDgGJmR7E18ps9zZaI7m6XJwu1wXk1fwJTklc6w09XxsgIi9N4Nlsv/yli+JrR2tCLYSz6g1ToRAdyqrgylPpSwaGQJVS6aApGSRBBrhkIFCYy8jYI7ZdovwiEXRzn/oafD6/5brIp+yTvS2Sju/NKjCAQoV+gsK2sfX/MuSAL2T/CGFl4AHihgbRDBHwLW89yko1cS6A3Qh/hJx0b26jNJB+BtDBcW+POgTbElKSojkee5dLYPZqF54EqDL5Xrm8/gN0yenimTxkiDx9K9uKs4ojtskN3OGLUVoIKaMvdVR3/SDp+iJx8qZInFTv41q/EqQbVaw49q1Ohw6o5swQKBgQDmTjGRBXON/9VuABUgIatdulJSs+3BiIwReYDobSxfkQ8sW0y5VaWDrVADkWSgpumLjzlQq8K0+E1emUfRga35I+ggU3FAv+2drpOT1Sh5iiSmQ8dgn5WtKf1HVhaQwKvzRUBw8OsSUorzi7jK8mRIcB9iKfhhzP5IUc8pvO8lqQKBgQDHVuIcTZ+lFXU6AjUGllPBHjLf6lTeu4xVrfhJ+E+AQ4WF/p+4zEiRwdjnrTEUlf5LFfys0rARLQ1/Fl5RXXIhSI0Qv/FuJcTtN93vDt6pOVNwgHNeAytX2q4JWz+m9u6K1ZIu7Ga0THU2G6P1OCfd6MNYibW2a3D6dTVNKaoGKQKBgQDA8JEl5WNBeyra1Mbd/FcMVqPRFVwB3ts5aIlOLugVx/jmODa2rcHT5sY5tvyOekb93CcwJjUgV0S6E7/PDM0rA6HcMwuWKzRt/Ws1VkZqTAkYIYBOumMyhxLhSIXvDvr++u1ZsPu+hdhS4KMXoqnQ5d3k5zRm+s2s2JytYtMWwQKBgCpjX1GFcB06ouLi9UOhU84NDeRvf/ydBQpzJAkWtFEiCpKjwnMZfZUBE46wVW97NcfhtQlLBAWi/uu3ZfcHV+2b7+NP7ZbukSGgb6EWGGMDvap+pgmnwt+7sKZssOxgNflv0NYJjw1n9MS8u/C5MEEP7fo1zl4sGqonol32LiGBAoGAIDIBqplXtASQdD8ngN+Pqa8FKuuQswcsMvvicLSYJ7rHp0GVIHWnXpyX+Ztl0fqBbPCA0dPUG4PJXvwjEnA2Xxpj6xv3fd0jx/uSvjn4ErUbZWIWWpL1TWl9h4HvJf/ifLXbPrK7WX68YlA9Q41T4tvGxCPs4hENAuyzNS/fX+M=";
//        String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
//        String SERVER_URL = "https://gw-api-iicamp.dccnet.com.cn/api/settlement/account/V1/open";
////        String SERVER_URL = "https://apisandbox.dccnet.com.cn/api/settlement/account/V1/open";
//        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
//
//        SettlementAccountOpenRequestV1 request = new SettlementAccountOpenRequestV1();
//        SettlementAccountOpenRequestV1.SettlementAccountOpenRequestV1Biz bizContent = new SettlementAccountOpenRequestV1.SettlementAccountOpenRequestV1Biz();
//        request.setServiceUrl(SERVER_URL);
//        bizContent.setAddress("");
//        bizContent.setBindMedium("6212880200000000139");
//        bizContent.setBirthday("");
//        bizContent.setCertNo("110110198801051116");
//        bizContent.setCertType(0);
//        bizContent.setCorpCisNo("1");
//        bizContent.setCorpDate("2017-03-15");
////        bizContent.setCorpDate("20170315");
//        bizContent.setCorpMediumId("987654321");
//        bizContent.setCorpNo("IICAMP0000000809");
////        bizContent.setCorpSerno(UUID.randomUUID().toString());
//        bizContent.setCorpSerno("hfdakjshfdhasjkfjdksla");
//
//        bizContent.setCustName("测试五");
//        bizContent.setCustomizationInfo("");
//        bizContent.setGender(1);
//        bizContent.setIssuingAuthority("");
//        bizContent.setMobileNo("13611223388");
//        bizContent.setNationality(0);
//        bizContent.setNotifyAddr("aaaaabbbbb");
//        bizContent.setOccupation(0);
//        bizContent.setOutServiceCode("openaccount");
//        bizContent.setTaxDeclarationFlag(0);
//        bizContent.setTrxAccDate("2017-03-15");
////        bizContent.setTrxAccDate("20170315");
//        bizContent.setTrxAccTime("13:30:01");
////        bizContent.setTrxAccTime("133001");
//        bizContent.setValidityPeriod("");
//        bizContent.setSecretKey("ASDQWEQDZCSDFAWWQDA");
//        bizContent.setBindMediumHash("SDFDFHTEWTGDFWADADAFSDGSESEFD");
//        request.setBizContent(bizContent);
//        SettlementAccountOpenResponseV1 response = client.execute(request, "msgId");
//        if (response.isSuccess()) {
//            System.out.println(response.getCorpSerno());
//            System.out.println(response.getMsgId());
//            System.out.println(response.getReturnMsg());
//        } else {
//            System.out.println("error:"+response.getReturnMsg());
//            System.out.println(response.getReturnCode());
//        }
    }

}
