package net.mohamadi.Data_Access.repository.product;


import net.mohamadi.Data_Access.entity.product.Color;
import net.mohamadi.Data_Access.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
