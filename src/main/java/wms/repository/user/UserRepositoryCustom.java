package wms.repository.user;

import java.util.List;

import wms.model.user.User;

public interface UserRepositoryCustom {
    List<User> findAllActiveUsers();
    User getAuthenticatedUser();
}
