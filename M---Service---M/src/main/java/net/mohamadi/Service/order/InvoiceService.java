package net.mohamadi.Service.order;


import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Data_Access.repository.invoice.InvoiceItemRepository;
import net.mohamadi.Data_Access.repository.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {


    private final InvoiceRepository repository;
    private final InvoiceItemRepository itemRepository;

    @Autowired
    public InvoiceService(InvoiceRepository repository, InvoiceItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }
}
