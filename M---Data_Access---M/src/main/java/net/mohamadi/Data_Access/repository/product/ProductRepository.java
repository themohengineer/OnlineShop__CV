package net.mohamadi.Data_Access.repository.product;


import net.mohamadi.Data_Access.entity.order.Invoice;
import net.mohamadi.Data_Access.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
