package net.mohamadi.Data_Access.repository.invoice;


import net.mohamadi.Data_Access.entity.order.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
