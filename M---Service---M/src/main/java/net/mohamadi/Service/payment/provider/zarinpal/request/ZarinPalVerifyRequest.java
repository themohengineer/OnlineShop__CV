package net.mohamadi.Service.payment.provider.zarinpal.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ZarinPalVerifyRequest {

    private String merchant_id;
    private Integer amount;
    private String authority;

    }

