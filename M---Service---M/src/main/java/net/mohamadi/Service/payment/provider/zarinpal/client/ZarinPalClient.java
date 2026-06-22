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

@Component
public class ZarinPalClient {


    private String baseUrl;

    public ZarinPalResponse goToPayment(ZarinPalRequest request) {

        String url = baseUrl + "v4/payment/request.json";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ZarinPalRequest> requestEntity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ZarinPalResponseWrapper> response = restTemplate.postForEntity(
                url,
                requestEntity,
                ZarinPalResponseWrapper.class);
        return Objects.requireNonNull(response.getBody()).getData();
    }


}
