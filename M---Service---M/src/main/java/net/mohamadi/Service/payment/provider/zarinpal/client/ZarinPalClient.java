package net.mohamadi.Service.payment.provider.zarinpal.client;


import net.mohamadi.Service.payment.provider.zarinpal.request.ZarinPalRequest;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalResponse;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalResponseWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component//به Springمی‌گویداین کلاس یک Beanاست
// و باید در IoC Container ثبت شود
//کلاس را به عنوان یک Bean معرفی می‌کند تا Spring آن را مدیریت کند.
public class ZarinPalClient {


    private String baseUrl;

    public ZarinPalResponse goToPayment(ZarinPalRequest request) {

        String url = baseUrl + "v4/payment/request.json";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ZarinPalRequest> requestEntity = new
                HttpEntity<>(request, headers);//ساخت HttpEntity:
        // درخواست (بدنه + هدرها) را بسته‌بندی می‌کند

        RestTemplate restTemplate = new RestTemplate();//یک کلاینت HTTP قدرتمند در
        // Spring که برای ارسال درخواست‌های RESTful استفاده می‌شود

        ResponseEntity<ZarinPalResponseWrapper> response = restTemplate.postForEntity(
                url,//آدرس نهایی
                requestEntity,//بدنه درخواست (شامل ZarinPalRequest و هدرها)
                ZarinPalResponseWrapper.class);

        //نتیجه: یک ResponseEntity شامل پاسخ HTTP و بدنه
        // (که به ZarinPalResponseWrapper تبدیل شده است) برمی‌گرداند.

        return Objects
                .requireNonNull(response
                        .getBody()
                )
                .getData();
        //استخراج data از پاسخ:
        //response.getBody(): بدنه پاسخ را
        // به صورت ZarinPalResponseWrapper برمی‌گرداند.
        //Objects.requireNonNull(...): اگر بدنه null باشد،
        // NullPointerException پرتاب می‌کند.
        //.getData(): ZarinPalResponse را
        // از wrapper استخراج می‌کند.
    }


}
