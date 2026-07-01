package net.mohamadi.Data_Access.repository.payment;


import net.mohamadi.Data_Access.entity.order.Invoice;
import net.mohamadi.Data_Access.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


    Optional<Payment> findFirstByNameEqualsIgnoreCase(String name);

}
