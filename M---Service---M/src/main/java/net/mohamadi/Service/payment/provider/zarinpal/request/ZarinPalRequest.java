package net.mohamadi.Service.payment.provider.zarinpal.request;


import lombok.*;

import java.lang.reflect.Array;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ZarinPalRequest {

    private String merchant_id;
    private String currency;
    private String description;
    private String callback_url;
    private String referrer_id;
    private Integer amount;
    private MetaData metaData;



    @Getter
    @Setter
    @Builder
    public static class MetaData{

        private String mobile;
        private String email;
        private String order_id;
    }


}
