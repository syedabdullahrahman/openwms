package wms.repository.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.transaction.annotation.Transactional;

import wms.model.user.User;

@Transactional
public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	UserRepository userRepository;

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAllActiveUsers() {
		Query query = entityManager.createNativeQuery(
		"SELECT " 
		+ "usr.* " 
		+ "FROM " 
		+ "users as usr " 
		+ "WHERE usr.active = ?", User.class);

		query.setParameter(1, true);

		return query.getResultList();
	}

	@Override
	@Transactional
	public User getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User modelUser;
			wms.security.UserPrincipal principal = (wms.security.UserPrincipal) auth.getPrincipal();
			modelUser = userRepository.findByUsernameIgnoreCase(principal.getUsername());

			return modelUser;
			
			
		}
		return null;
	}

}
