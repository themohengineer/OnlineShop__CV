package net.mohamadi.Data_Access.repository.site;


import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.site.Nav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavRepository extends JpaRepository<Nav, Long> {


    List<Nav> findAllByEnableIsTrueOrderByOrderNumberAsc();




}
