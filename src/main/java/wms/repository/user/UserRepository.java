package wms.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wms.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameIgnoreCase(String username);
}
