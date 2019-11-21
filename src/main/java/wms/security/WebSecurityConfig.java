package wms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserPrincipalDetailsService userPrincipalDetailsService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userPrincipalDetailsService).passwordEncoder(passwordEncoder());
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
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		 //.logoutSuccessUrl("/login")
		
		.and()
		.rememberMe()
		.tokenValiditySeconds(2592000)
		.key("owms")
		.rememberMeParameter("remember-me")
		//.and()
		//.exceptionHandling().accessDeniedPage("/noaccess")
		
		.and()
		.sessionManagement()
        .maximumSessions(1)
		.maxSessionsPreventsLogin(false)
		.expiredUrl("/login?expired")
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
