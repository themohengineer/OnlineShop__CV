package net.mohamadi.Data_Access.repository.site;


import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.site.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
