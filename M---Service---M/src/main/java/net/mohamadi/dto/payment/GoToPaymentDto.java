package net.mohamadi.dto.payment;


import lombok.*;
import net.mohamadi.enums.PaymentGateway;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class GoToPaymentDto {


    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String tel;
    private String address;
    private String postalCode;
    private PaymentGateway gateway;//که کدام درگاه پرداخت انتخاب شود. الان فقط زرین پال هست.

    private List<BasketItem> items;


    @Getter
    @Setter
    @Builder
    public static class BasketItem {

        private Long productId;
        private Long colorId;
        private Long sizeId;
        private Long quantity;

    }


}
