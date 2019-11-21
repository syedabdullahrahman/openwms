package wms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import wms.model.user.User;
import wms.repository.user.UserRepository;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    
	@Autowired
	private UserRepository userRepository;

    public UserPrincipalDetailsService() {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsernameIgnoreCase(s);
        if(user==null) {
        	throw new UsernameNotFoundException("User not found");
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
}
