package ma.ehtp.orm_manytomany.repositories;

import ma.ehtp.orm_manytomany.entities.Role;
import ma.ehtp.orm_manytomany.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //facultatif
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
