package net.mohamadi.Service.payment.provider.zarinpal.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ZarinPalRequest {

    private String merchant_id;
    private String currency;//	واحد پول (IRR برای ریال، IRT برای تومان)
    private String description;
    private String callback_url;
    private String referrer_id;
    private Integer amount;
    private MetaData metaData;


    @Getter
    @Setter
    @Builder
    public static class MetaData {
        //زرین‌پال از این اطلاعات برای ارسال
        // ایمیل/پیامک تأیید یا نمایش در پنل استفاده می‌کند
        private String mobile;
        private String email;
        private String order_id;
    }


}
