package net.mohamadi.Data_Access.repository.product;


import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
}
