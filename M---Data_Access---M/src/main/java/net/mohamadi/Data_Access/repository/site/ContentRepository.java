package net.mohamadi.Data_Access.repository.site;


import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.site.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {


    Optional<Content> findFirstByKeyNameEqualsIgnoreCase(String key);


}
