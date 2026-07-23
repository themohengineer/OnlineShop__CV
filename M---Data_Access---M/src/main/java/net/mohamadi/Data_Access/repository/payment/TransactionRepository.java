package net.mohamadi.Data_Access.repository.payment;


import net.mohamadi.Data_Access.entity.payment.Payment;
import net.mohamadi.Data_Access.entity.payment.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findFirstByAuthorityEqualsIgnoreCase(String authority);


}
