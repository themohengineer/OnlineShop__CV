package net.mohamadi.Data_Access.repository.product;


import net.mohamadi.Data_Access.entity.order.Invoice;
import net.mohamadi.Data_Access.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("""
            from Product 
            where enable=true 
            and exist=true 
            order by visitCount desc
            limit 6
                        """)
    List<Product> find6PopularProduct();


}
