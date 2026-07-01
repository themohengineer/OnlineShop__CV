package net.mohamadi.Service.payment;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.order.Invoice;
import net.mohamadi.Data_Access.entity.payment.Payment;
import net.mohamadi.Data_Access.entity.payment.Transaction;
import net.mohamadi.Data_Access.entity.user.User;
import net.mohamadi.Data_Access.repository.payment.PaymentRepository;
import net.mohamadi.Data_Access.repository.payment.TransactionRepository;
import net.mohamadi.Service.order.InvoiceService;
import net.mohamadi.Service.payment.provider.zarinpal.provider.ZarinPalProvider;
import net.mohamadi.Service.user.UserService;
import net.mohamadi.dto.invoice.InvoiceDto;
import net.mohamadi.dto.invoice.InvoiceItemDto;
import net.mohamadi.dto.payment.GoToPaymentDto;
import net.mohamadi.dto.product.ColorDto;
import net.mohamadi.dto.product.ProductDto;
import net.mohamadi.dto.product.SizeDto;
import net.mohamadi.dto.user.CustomerDto;
import net.mohamadi.dto.user.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PaymentService {

    //این کلاس منطق اصلی پرداخت را مدیریت می‌کند.

    private final PaymentRepository repository;
    private final ZarinPalProvider zarinPalProvider;  //برای ارتباط با درگاه زرین‌پال
    private final TransactionRepository trxRepository;//برای ذخیره و مدیریت تراکنش‌ها در دیتابیس.
    private final UserService userService;
    private final InvoiceService invoiceService;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentService(PaymentRepository repository,
                          ZarinPalProvider zarinPalProvider,
                          TransactionRepository trxRepository,
                          UserService userService,
                          InvoiceService invoiceService, ModelMapper modelMapper) {
        this.repository = repository;
        this.zarinPalProvider = zarinPalProvider;
        this.trxRepository = trxRepository;
        this.userService = userService;
        this.invoiceService = invoiceService;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public String goToPayment(GoToPaymentDto dto) throws Exception {

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

        UserDto user = userService.create(UserDto.builder()
                .username(dto.getUsername())
                .mobile(dto.getMobile())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .customer(CustomerDto.builder()
                        .firstName(dto.getFirstname())
                        .lastName(dto.getLastname())
                        .tel(dto.getTel())
                        .address(dto.getTel())
                        .postalCode(dto.getPostalCode())
                        .build())
                .build());


        InvoiceDto invoice = invoiceService.create(InvoiceDto.builder()
                .items(dto.getItems().stream().map(x -> InvoiceItemDto.builder()
                        .product(ProductDto.builder()
                                .id(x.getProductId())
                                .build())
                        .color(ColorDto.builder()
                                .id(x.getProductId())
                                .build())
                        .size(SizeDto.builder()
                                .id(x.getSizeId())
                                .build())
                        .quantity(Math.toIntExact(x.getQuantity()))
                        .build()).toList())
                .build());


        Payment gateway = repository.findFirstByNameEqualsIgnoreCase(dto.getGateway()
                        .toString())
                .orElseThrow(NotFoundExceptionss::new);


        //ساخت Transaction : یک تراکنش جدید با @Builder می‌سازد.
        Transaction trx = Transaction.builder()
                //todo
                .amount(invoice.getTotalAmount())
                .payment(gateway)
                .description(invoice.getId() + "_" + " " + invoice.getTotalAmount())
                .customer(modelMapper.map(user, User.class))
                .invoice(modelMapper.map(invoice, Invoice.class))
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
        if (dto.getAddress() == null || dto.getAddress().isEmpty())
            throw new ValidationException("Address is null or empty");
        if (dto.getPostalCode() == null || dto.getPostalCode().isEmpty())
            throw new ValidationException("PostalCode is null or empty");
        if (dto.getItems() == null || dto.getItems().isEmpty())
            throw new ValidationException("Please add at least one item");
    }
}
