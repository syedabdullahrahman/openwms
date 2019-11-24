package wms.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wms.model.user.Role;
import wms.model.user.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    User findByNameIgnoreCase(String name);
}
