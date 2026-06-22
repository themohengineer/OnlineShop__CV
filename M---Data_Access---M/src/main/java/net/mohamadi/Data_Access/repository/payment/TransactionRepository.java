package net.mohamadi.Data_Access.repository.payment;


import net.mohamadi.Data_Access.entity.payment.Payment;
import net.mohamadi.Data_Access.entity.payment.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
