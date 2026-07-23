package net.mohamadi.App.controller;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class HomeController {


    //This Value, Just For Mock Data !!!
    @Value("${app.payment-gateway.zarinpal.callback-url}")
    private String callbackUrl;//آدرسی که زرین‌پال بعد
    // از پرداخت، کاربر را به آن هدایت می‌کند

    private final PaymentService paymentService;

    @Autowired
    public HomeController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("verify")
    public APIResponse<String> verify(
            @RequestParam String authority,
            @RequestParam String status
    ) {

        try {
            return APIResponse.<String>builder()
                    .status(HttpStatus.OK)
                    .data(paymentService.verify(authority, status))
                    .build();
        } catch (NotFoundExceptionss e) {
            return APIResponse.<String>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
        }


    }

//https://127.0.0.1/pg/StartPay/

    // Just For Mock Data !!!
    @GetMapping("pg/StartPay/{Authority}")
    public APIResponse<String> startPay(
            @PathVariable String Authority) {

        return APIResponse.<String>builder()
                .status(HttpStatus.OK)
                .data(callbackUrl + "?authority=" + Authority + "&status=OK")
                .build();


    }


}
