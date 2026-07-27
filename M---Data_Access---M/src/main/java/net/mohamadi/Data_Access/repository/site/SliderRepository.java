package net.mohamadi.Data_Access.repository.site;


import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.site.Nav;
import net.mohamadi.Data_Access.entity.site.Slider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SliderRepository extends JpaRepository<Slider, Long> {


    List<Slider> findAllByEnableIsTrueOrderByOrderNumberAsc();

    @Query("""
            
            select orderNumber from Slider order by orderNumber desc limit 1
            
            """)
    Integer findLastOrderNumber();

}
