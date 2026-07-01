package net.mohamadi.Data_Access.repository.user;

import net.mohamadi.Data_Access.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
