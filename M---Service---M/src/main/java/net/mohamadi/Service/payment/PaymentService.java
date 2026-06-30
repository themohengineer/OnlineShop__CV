package net.mohamadi.Service.payment;


import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.payment.Transaction;
import net.mohamadi.Data_Access.repository.payment.PaymentRepository;
import net.mohamadi.Data_Access.repository.payment.TransactionRepository;
import net.mohamadi.Service.payment.provider.zarinpal.provider.ZarinPalProvider;
import net.mohamadi.Service.user.UserService;
import net.mohamadi.dto.payment.GoToPaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    //این کلاس منطق اصلی پرداخت را مدیریت می‌کند.

    private final PaymentRepository repository;
    private final ZarinPalProvider zarinPalProvider;  //برای ارتباط با درگاه زرین‌پال
    private final TransactionRepository trxRepository;//برای ذخیره و مدیریت تراکنش‌ها در دیتابیس.
    private final UserService userService;

    @Autowired
    public PaymentService(PaymentRepository repository, ZarinPalProvider zarinPalProvider, TransactionRepository trxRepository, UserService userService) {
        this.repository = repository;
        this.zarinPalProvider = zarinPalProvider;
        this.trxRepository = trxRepository;
        this.userService = userService;
    }

    public String goToPayment(GoToPaymentDto dto) throws ValidationException {

        //نقطه ورود اصلی برای شروع پرداخت. یک GoToPaymentDto دریافت می‌کند
        // و یک String (آدرس IPG) برمی‌گرداند.

        /*
        1_ Validation
        2_ Creat New User
        3_ Creat New User + Invoice Item
        4_ Calculate Total Amount
        5_ Create New TransAction
        6_ Send Request To Bank
        7_ Receive IPG Response
        8_ Update TransAction => Save IPG Token
        9_ Redirect User to IPG URL
         */

        checkValidation(dto);


        //ساخت Transaction : یک تراکنش جدید با @Builder می‌سازد.
        Transaction trx = Transaction.builder()
                //todo
                .build();

        String result = "";// برای ذخیره آدرس IPG که از درگاه پرداخت برمی‌گردد.

        switch (dto.getGateway()) {
            case Zarinpal -> {
                result = zarinPalProvider.goToPayment(trx);//تراکنش را به زرین‌پال
                // ارسال کرده و آدرس IPG را برمی‌گرداند
            }
        }
        trxRepository.save(trx);

        return result;//آدرسی که کاربر
        // باید به آن هدایت شود تا پرداخت را تکمیل کند


    }

    private static void checkValidation(GoToPaymentDto dto) throws ValidationException {
        if (dto.getGateway() == null)
            throw new ValidationException("Gateway is null");
        if (dto.getFirstname() == null || dto.getFirstname().isEmpty())
            throw new ValidationException("Firstname is null or empty");
        if (dto.getLastname() == null || dto.getLastname().isEmpty())
            throw new ValidationException("Lastname is null or empty");
        if (dto.getUsername() == null || dto.getUsername().isEmpty())
            throw new ValidationException("Username is null or empty");
        if (dto.getPassword() == null || dto.getPassword().isEmpty())
            throw new ValidationException("Password is null or empty");
        if (dto.getEmail() == null || dto.getEmail().isEmpty())
            throw new ValidationException("Email is null or empty");
        if (dto.getMobile() == null || dto.getMobile().isEmpty())
            throw new ValidationException("Mobile is null or empty");
        if (dto.getTel() == null || dto.getTel().isEmpty())
            throw new ValidationException("Tel is null or empty");
        if (dto.getAddress() == null || dto.getPostalCode().isEmpty())
            throw new ValidationException("PostalCode is null or empty");
        if (dto.getItems() == null || dto.getItems().isEmpty())
            throw new ValidationException("Please add at least one item");
    }
}
