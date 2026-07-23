package net.mohamadi.Service.payment.provider.zarinpal.provider;


import net.mohamadi.Data_Access.entity.payment.Transaction;
import net.mohamadi.Service.payment.provider.zarinpal.client.ZarinPalClient;
import net.mohamadi.Service.payment.provider.zarinpal.client.ZarinPalClientMock;
import net.mohamadi.Service.payment.provider.zarinpal.request.ZarinPalRequest;
import net.mohamadi.Service.payment.provider.zarinpal.request.ZarinPalVerifyRequest;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalResponse;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalVerifyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ZarinPalProvider {


    @Value("${app.payment-gateway.zarinpal.merchant-id}")
    private String merchantId;//کلید اختصاصی شما در
    // زرین‌پال (که از پنل زرین‌پال دریافت می‌کنید)

    @Value("${app.payment-gateway.zarinpal.callback-url}")
    private String callbackUrl;//آدرسی که زرین‌پال بعد
    // از پرداخت، کاربر را به آن هدایت می‌کند

    @Value("${app.payment-gateway.zarinpal.ipg-url}")
    private String ipgUrl;// آدرس درگاه پرداخت
    // زرین‌پال (که کاربر به آن هدایت می‌شود)

    private final ZarinPalClientMock client;//یک نمونه از ZarinPalClient که برای
    // ارسال درخواست به زرین‌پال استفاده می‌شود

    @Autowired
    public ZarinPalProvider(ZarinPalClientMock client) {
        this.client = client;
    }

    public String goToPayment(Transaction trx) {
        //یک Transaction دریافت می‌کند و آدرس IPG را برمی‌گرداند
        ZarinPalRequest request = ZarinPalRequest.builder()
                .merchant_id(merchantId)
                .callback_url(callbackUrl)
                .amount(trx.getAmount().intValue())
                .currency("IRT") // Yani Toman
                .description(trx.getDescription())
                .metaData(ZarinPalRequest.MetaData.builder()
                        .email(trx.getCustomer() != null ? trx.getCustomer().getEmail() : "")
                        .mobile(trx.getCustomer() != null ? trx.getCustomer().getMobile() : "")
                        .order_id(trx.getInvoice() != null ? trx.getInvoice().getId().toString() : "")
                        .build())
                .build();
        ZarinPalResponse response = client
                .goToPayment(request);

        if (response != null) {
            trx.setAuthority(response.getAuthority());
            trx.setCode(response.getCode());
            trx.setResultMessage(response.getMessage());
        }
        assert response != null;
        return ipgUrl + response.getAuthority();
    }


    public Transaction verify(Transaction trx) {
        //یک Transaction دریافت می‌کند و آدرس IPG را برمی‌گرداند
        ZarinPalVerifyRequest request = ZarinPalVerifyRequest.builder()
                .merchant_id(merchantId)
                .amount(trx.getAmount().intValue())
                .authority(trx.getAuthority())
                .build();
        ZarinPalVerifyResponse response = client
                .verifyPayment(request);

        if (response != null) {
            trx.setVerifyCode(response.getCode());
            trx.setVerifyMessage(response.getMessage());
            trx.setCardHash(response.getCard_hash());
            trx.setCardPan(response.getCard_pan());
            trx.setRefId(response.getRef_id());
        }

        return trx;

    }


}
