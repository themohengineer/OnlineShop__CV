package net.mohamadi.Service.order;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Data_Access.entity.order.Invoice;
import net.mohamadi.Data_Access.entity.order.InvoiceItem;
import net.mohamadi.Data_Access.enums.OrderStatus;
import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Data_Access.repository.invoice.InvoiceItemRepository;
import net.mohamadi.Data_Access.repository.invoice.InvoiceRepository;
import net.mohamadi.Service.product.ProductService;
import net.mohamadi.dto.invoice.InvoiceDto;
import net.mohamadi.dto.product.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvoiceService {


    private final InvoiceRepository repository;
    private final InvoiceItemRepository itemRepository;
    private final ModelMapper mapper;
    private final ProductService productService;

    @Autowired
    public InvoiceService(InvoiceRepository repository,
                          InvoiceItemRepository itemRepository,
                          ModelMapper mapper,
                          ProductService productService) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.mapper = mapper;
        this.productService = productService;
    }

    public InvoiceDto create(InvoiceDto dto) {
        //todo:check validation
        Invoice invoice = mapper.map(dto, Invoice.class);
        invoice.setCreateDate(LocalDateTime.now());
        invoice.setPayedDate(null);
        invoice.setStatus(OrderStatus.InProgress);

        long totalAmount = 0L;

        if (invoice.getItems() != null && invoice.getItems().size() > 0) {
            for (InvoiceItem ii : invoice.getItems()) {
                try {
                    ProductDto product = productService.read(ii.getProduct().getId());
                    ii.setPrice(product.getPrice());
                    totalAmount += product.getPrice() * ii.getQuantity();
                } catch (NotFoundExceptionss e) {
                    throw new RuntimeException();
                }
            }
        }

        invoice.setTotalAmount(totalAmount);
        Invoice savedInvoice = repository.save(invoice);
        return mapper.map(savedInvoice, InvoiceDto.class);

    }


}
