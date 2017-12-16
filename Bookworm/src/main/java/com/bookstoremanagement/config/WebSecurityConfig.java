package com.bookstoremanagement.config;

import com.bookstoremanagement.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// For User in database.
		auth.userDetailsService(myDBAauthenticationService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(myDBAauthenticationService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// The pages does not require login
		http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();

		// /userInfo page requires login as USER(buyer, seller, prime) or ADMIN.
		// If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_BUYER','ROLE_SELLER', 'ROLE_PRIME', 'ROLE_ADMIN')");
		// For ADMIN only.
		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		// For ADMIN, BUYER and PRIME only.
		http.authorizeRequests().antMatchers("/buy-book").access("hasAnyRole('ROLE_BUYER', 'ROLE_PRIME', 'ROLE_ADMIN')");
		// For ADMIN, BUYER and PRIME only.
		http.authorizeRequests().antMatchers("/myCart/*").access("hasAnyRole('ROLE_BUYER', 'ROLE_PRIME', 'ROLE_ADMIN')");
		// For ADMIN, BUYER and PRIME only.
		http.authorizeRequests().antMatchers("/viewWishList/*").access("hasAnyRole('ROLE_BUYER', 'ROLE_PRIME', 'ROLE_ADMIN')");
		// For ADMIN, SELLER and PRIME only.
		http.authorizeRequests().antMatchers("/sell-book").access("hasAnyRole('ROLE_SELLER', 'ROLE_PRIME', 'ROLE_ADMIN')");
		// For ADMIN, SELLER and PRIME only.
		

		// When the user has logged in as XX.
		// But access a page that requires role YY,
		// AccessDeniedException will throw.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Config for Login Form
		http.authorizeRequests().and().formLogin()//
		// Submit URL of login page.
		.loginProcessingUrl("/j_spring_security_check") // Submit URL
		.loginPage("/login")//
		.defaultSuccessUrl("/home")//
		.failureUrl("/login?error=true")//
		.usernameParameter("userdetails")//
		.passwordParameter("password")
		// Config for Logout Page
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful").and().sessionManagement().maximumSessions(1);
		//	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).
		//  maximumSessions(1).expiredUrl("/login").and().invalidSessionUrl("/home").sessionFixation().migrateSession();

	}
}