package net.mohamadi.Service.payment.provider.zarinpal.client;


import net.mohamadi.Service.payment.provider.zarinpal.request.ZarinPalRequest;
import net.mohamadi.Service.payment.provider.zarinpal.request.ZarinPalVerifyRequest;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalResponse;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalResponseWrapper;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalVerifyResponse;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalVerifyResponseWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Random;

@Component//به Springمی‌گویداین کلاس یک Beanاست
// و باید در IoC Container ثبت شود
//کلاس را به عنوان یک Bean معرفی می‌کند تا Spring آن را مدیریت کند.
public class ZarinPalClientMock {


    private String baseUrl;

    public ZarinPalResponse goToPayment(ZarinPalRequest request) {


        return ZarinPalResponse.builder()

                .authority("MOCK_DATA_AUTHORITY_" + new Random().nextInt(100000, 999999))
                .code("100")
                .message("MOCK_DATA")
                .build();


    }


    public ZarinPalVerifyResponse verifyPayment(ZarinPalVerifyRequest request) {

        return ZarinPalVerifyResponse.builder()
                .code("100")
                .message("Verified")
                .card_hash("1EBE3EBEBE35C7EC0F8D6EE4F2F859107A87822CA179BC9528767EA7B5489B69")
                .card_pan("502229******5995")
                .ref_id("201")
                .fee_type("Merchant")
                .fee(0)
                .build();
    }






}
