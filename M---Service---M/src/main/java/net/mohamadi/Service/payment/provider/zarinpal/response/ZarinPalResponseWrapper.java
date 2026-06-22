package net.mohamadi.Service.payment.provider.zarinpal.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ZarinPalResponseWrapper {


    private ZarinPalResponse data;
    private Object[] Errors;

    private String code;
    private String message;
    private String authority;
    private String fee_type;
    private Integer fee;


}
