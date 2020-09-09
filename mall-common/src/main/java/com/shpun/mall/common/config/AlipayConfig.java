package com.shpun.mall.common.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import com.shpun.mall.common.common.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/8 14:05
 */
@Component
public class AlipayConfig {

    private static final Logger logger = LoggerFactory.getLogger(AlipayConfig.class);

    @Value("${server.port:}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    /**
     * 全局配置，只需一次
     */
    static {
        Factory.setOptions(getDevOptions());
    }

    /**
     * 手机网站支付
     * @param outTradeNo 交易编号，唯一
     * @param totalAmount 金额
     * @return
     */
    public AlipayTradeWapPayResponse pay(String outTradeNo, String totalAmount) {
        AlipayTradeWapPayResponse response;
        try {
            response = Factory.Payment.Wap().pay(Const.PAY_SUBJECT, outTradeNo, totalAmount, buildUrl("/api/open/quit"), buildUrl("/api/open/callback"));
        } catch (Exception e) {
            logger.error("订单编号： {} 交易异常！", outTradeNo);
            throw new RuntimeException(e.getMessage(), e);
        }
        return response;
    }

    /**
     * 退款
     * @param outTradeNo
     * @param refundAmount
     * @return
     */
    public AlipayTradeRefundResponse refund(String outTradeNo, String refundAmount) {
        AlipayTradeRefundResponse response;
        try {
            response = Factory.Payment.Common().refund(outTradeNo, refundAmount);
        } catch (Exception e) {
            logger.error("订单编号： {} 退款异常！", outTradeNo);
            throw new RuntimeException(e.getMessage(), e);
        }
        return response;
    }

    /**
     * 沙箱环境
     * @return
     */
    private static Config getDevOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";
        config.appId = "2021000116690100";
        // 私钥
        config.merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJIRY7/eyfEzgWmYrJeZpVYRm7Qw8Y39TFqN0ODLDUbezxZR0q6WAhfuyw16hXBZzTwPAL3MDduNWMVXJ2a8UbvYBpZ1p31OBF0tXAYOasQZTPUEsHongq2c4LkKX+ah82/X5/KlsKBK2bUiF8prl2BmJMbDeguQlSM1b+ERrjUl0++VE9ceYP8gJ1x4HgIHF1LpmplskIqs9X4A2ZdOGvpGY9L2mWHwJUn5Djc+wrMm8Vcp2EInKVwggPdMGLWSvv+EBvQGI4wtQKa/TdR1/q57DMMjbaa0evXBgHiDaP6mjDK9ON7onnVndWP0QhZmnaHdC7MPBKNYnewd4dUHkFAgMBAAECggEAFUrN6QLEeu7BBsFQi3gWPquVEz6WWYZnw0k1Cbluu41V/SV2IfOauZi7p7nUqa51a2vJ2g2s5MCOZ/vcigax1FzYU4V4xqKHMFBMYFKrfpOwR4BwJf9UKVMoayNKovZXYnJ58cTh3excCIH4MSKyKWFUYglrmckhaWIPX+PICQPGjVEqvjYIqwgz7OiGSWvx6CGZVBeRzYJ3BqTejguR4PclZmGryxDEuldw3nICnKCz+jcxaLsvzo6Q3U70mXj/QCM+aXtuC60rBrEszLMjZjS3YJynZ00ZRmdA80EN7hTPd+z4xa33n0/zzKfBq53RpL3CfwWdC3CxDyUF6tooAQKBgQDMpi7DOlW49aZWqUaklnTo+x7+uiYRN+rYNK5OUblGIwqP4wrburMcPisOF3uQDCoVdHUGhvSGBnF+Fs+ghucozhgFqzsYkLWqOLEe+/qGbMGNkfcpOm+f/ETN1LBC2LKLqFFI29qybnu+h2Ybxjnu8tuNRnMqrSQisDpxZbk5KQKBgQCribQ2ro/tenmViCo+Pr74rReeai9cuBbLb8aMFl8H6cHtwp52MNdgLEmP7oOai6DjasXADCh6AA9YkMzuFp541vH6CwSyIbtIt0JlOqkF79tUmlSc0i8pn8HFga+rr+dsFIDDYjW4i/wNa/DyvJhsWS+Q0evB9uLMnq2JTIUQfQKBgBs98/OhDFA2wxG+Lt0x+hYZd6LI/NBF9Ukamilqz5L6jylgO83MKJOV4v1ZOuWN+PwqKHnZ9SryKKF0P+dKjfLsnF4E0TUP7l7edRV+zXafe5Rw3cnrMXjGiBIuQdhkKEBXA5sDSTV/Z5GRTJJd7NBWG8vOmP4RVkIX22/E7schAoGAXtmfynSvpPKJ9EL8iLqNQlgDrVatpaktAYRm1uMv3OJGlz97+4J6NPWIH4wVPQlkBxvtCIowf3U1BxXaGvRq4AqKdmZNTGoWctjSX8MV3vCH6LoFe0lFRtDNhnZ1OovjfQHBSUguyEKvNoSvEKlz5sVR5dQNdo0bT2WWM0sacMkCgYA/tWWgabXTC1CIoywMG2mz9LXe5TGtycDy4v438tN0RQ80qvVq6iCyVMUQTsA3ug04X2n6v473Mp+VnD8hyqiQr+poxKCJV9pDxRP5TnDUh8om2pzDACgIDfL8c16M9iQwFTM/f4MARS98m/N60N8J8wSvIulGVB8Vb/s+fYuw6Q==";
        // 公钥
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiSEWO/3snxM4FpmKyXmaVWEZu0MPGN/UxajdDgyw1G3s8WUdKulgIX7ssNeoVwWc08DwC9zA3bjVjFVydmvFG72AaWdad9TgRdLVwGDmrEGUz1BLB6J4KtnOC5Cl/mofNv1+fypbCgStm1IhfKa5dgZiTGw3oLkJUjNW/hEa41JdPvlRPXHmD/ICdceB4CBxdS6ZqZbJCKrPV+ANmXThr6RmPS9plh8CVJ+Q43PsKzJvFXKdhCJylcIID3TBi1kr7/hAb0BiOMLUCmv03Udf6uewzDI22mtHr1wYB4g2j+powyvTje6J51Z3Vj9EIWZp2h3QuzDwSjWJ3sHeHVB5BQIDAQAB";

        // todo 待配置
        config.notifyUrl = "http://localhost:8123/alipay/notify";
        return config;
    }

    private String buildUrl(String path) {
        StringBuilder urlSb = new StringBuilder("http://localhost:").append(port).append(contextPath).append(path);
        return urlSb.toString();
    }

}
