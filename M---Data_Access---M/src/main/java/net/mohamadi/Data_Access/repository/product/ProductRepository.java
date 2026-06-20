//package net.mohamadi.Data_Access.repository.product;
//
//
//import net.mohamadi.Data_Access.entity.order.Invoice;
//import net.mohamadi.Data_Access.entity.product.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProductRepository extends JpaRepository<Product, Long> {
//
//
//    @Query("""
//            from Product
//            where enable=true
//            and exist=true
//            order by visitCount desc
//            limit 6
//                        """)
//    List<Product> find6PopularProducts();
//
//
//    @Query("""
//            from Product
//            where enable=true
//            and exist=true
//            order by addDate desc
//            limit 6
//                        """)
//    List<Product> find6NewestProducts();
//
//
//    @Query("""
//            from Product
//            where enable=true
//            and exist=true
//            order by price asc
//            limit 6
//                        """)
//    List<Product> find6CheapestProducts();
//
//    @Query("""
//            from Product
//            where enable=true
//            and exist=true
//            order by  price desc
//            limit 6
//                        """)
//    List<Product> find6ExpensiveProducts();
//
//
//
//
//
//}

package net.mohamadi.Data_Access.repository.product;

import net.mohamadi.Data_Access.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            from Product p
            left join fetch p.colors
            left join fetch p.sizes
            where p.enable=true
            and p.exist=true
            order by p.visitCount desc
            limit 6
            """)
    List<Product> find6PopularProducts();

    @Query("""
            from Product p
            left join fetch p.colors
            left join fetch p.sizes
            where p.enable=true
            and p.exist=true
            order by p.addDate desc
            limit 6
            """)
    List<Product> find6NewestProducts();

    @Query("""
            from Product p
            left join fetch p.colors
            left join fetch p.sizes
            where p.enable=true
            and p.exist=true
            order by p.price asc
            limit 6
            """)
    List<Product> find6CheapestProducts();

    @Query("""
            from Product p
            left join fetch p.colors
            left join fetch p.sizes
            where p.enable=true
            and p.exist=true
            order by p.price desc
            limit 6
            """)
    List<Product> find6ExpensiveProducts();
}