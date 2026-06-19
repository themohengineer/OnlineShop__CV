package net.mohamadi.Service.payment;


import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Data_Access.repository.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {


    private final PaymentRepository repository;


    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }
}
