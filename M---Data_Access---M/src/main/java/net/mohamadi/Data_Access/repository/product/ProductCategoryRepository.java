package net.mohamadi.Data_Access.repository.product;


import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    List<ProductCategory> findAllByEnableIsTrue();



}
