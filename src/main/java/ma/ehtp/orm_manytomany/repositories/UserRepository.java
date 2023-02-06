package ma.ehtp.orm_manytomany.repositories;

import ma.ehtp.orm_manytomany.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String name);

}
