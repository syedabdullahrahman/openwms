package wms.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import wms.model.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        this.user.getRoles().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(r.getName());
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isActive();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserDetails) {
          return this.getUsername().equals( ((UserDetails) obj).getUsername() );
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getUsername() != null ? this.getUsername().hashCode() : 0;
    }
    
}
