package wms.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserPrincipalDetailsService userPrincipalDetailsService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userPrincipalDetailsService).passwordEncoder(passwordEncoder());
	}
	
	// authorize requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().permitAll()
		
		.and()
		.formLogin()
		.loginProcessingUrl("/login")
		
		.loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 //.logoutSuccessUrl("/login")
		
		.and()
		.rememberMe()
		.tokenValiditySeconds(2592000)
		.key("owms")
		.rememberMeParameter("remember-me")
		
		.and()
		//.exceptionHandling().accessDeniedPage("/noaccess")
		
		
		.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/expired")
		.maxSessionsPreventsLogin(true)
		.sessionRegistry(sessionRegistry())
		;
		
	}

	
    // Register HttpSessionEventPublisher
	 @Bean
	    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	    }
    
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

}



/*

auth.authenticationProvider(authenticationProvider());
 * 
@Bean
DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
	return daoAuthenticationProvider;
}
*/
