package net.mohamadi.Data_Access.repository.user;


import net.mohamadi.Data_Access.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

}
