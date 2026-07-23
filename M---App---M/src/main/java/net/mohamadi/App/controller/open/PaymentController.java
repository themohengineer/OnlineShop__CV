package net.mohamadi.App.controller.open;


import jakarta.transaction.Transactional;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.payment.PaymentService;
import net.mohamadi.dto.payment.GoToPaymentDto;
import net.mohamadi.dto.payment.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    private final PaymentService service;


    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }


    @Transactional
    @PostMapping("goToPayment")
    public APIResponse<String> goToPayment(@RequestBody GoToPaymentDto dto) throws Exception {
        try {
            return APIResponse
                    .<String>builder()
                    .status(HttpStatus.OK)
                    .data(service.goToPayment(dto))
                    .build();
        } catch (ValidationException e) {
            return APIResponse
                    .<String>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
        }

    }


    @GetMapping("gateways")
    public APIResponse<List<PaymentDto>> getAllPaymentGateways() {

        return APIResponse.<List<PaymentDto>>builder()
                .status(HttpStatus.OK)
                .data(service.readAllGatewas())
                .build();


    }


}
