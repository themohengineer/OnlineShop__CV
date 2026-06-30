package net.mohamadi.App.controller.open;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.payment.PaymentService;
import net.mohamadi.dto.payment.GoToPaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    private final PaymentService service;


    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("goToPayment")
    public APIResponse<String> goToPayment(@RequestBody GoToPaymentDto dto) {
        try {
            return APIResponse
                    .<String>builder()
                    .status(HttpStatus.OK)
                    .data(service.goToPayment(dto))
                    .build();
        } catch (ValidationException e) {
            APIResponse
                    .<String>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data(e.getMessage())
                    .build();
        }
    }


}
