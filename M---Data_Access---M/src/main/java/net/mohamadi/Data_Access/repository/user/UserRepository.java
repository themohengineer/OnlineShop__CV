package net.mohamadi.Data_Access.repository.user;

import net.mohamadi.Data_Access.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsernameEqualsIgnoreCaseAndPassword(String username, String password);


    @Query("from User u left join fetch u.roles r left join fetch r.permissions where u.username = :username")
    Optional<User> findFirstByUsername(String username);


}
