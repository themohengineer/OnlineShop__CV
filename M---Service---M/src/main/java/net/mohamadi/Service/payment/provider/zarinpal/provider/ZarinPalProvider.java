package net.mohamadi.Service.payment.provider.zarinpal.provider;


import net.mohamadi.Data_Access.entity.payment.Transaction;
import net.mohamadi.Service.payment.provider.zarinpal.client.ZarinPalClient;
import net.mohamadi.Service.payment.provider.zarinpal.request.ZarinPalRequest;
import net.mohamadi.Service.payment.provider.zarinpal.response.ZarinPalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ZarinPalProvider {


    @Value("${app.payment-gateway.zarinpal.merchant-id}")
    private String merchantId;

    @Value("${app.payment-gateway.zarinpal.callback-url}")
    private String callbackUrl;

    @Value("${app.payment-gateway.zarinpal.ipg-url}")
    private String ipgUrl;

    private final ZarinPalClient client;


    public ZarinPalProvider(ZarinPalClient client) {
        this.client = client;
    }

    public String goToPayment(Transaction trx) {
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


}
