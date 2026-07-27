package net.mohamadi.Data_Access.repository.site;


import net.mohamadi.Data_Access.entity.site.Nav;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface NavRepository extends JpaRepository<Nav, Long> {


    List<Nav> findAllByEnableIsTrueOrderByOrderNumberAsc();


    @Query("""
            
            select orderNumber from Nav order by orderNumber desc limit 1
            
            """)
    Integer findLastOrderNumber();


}
