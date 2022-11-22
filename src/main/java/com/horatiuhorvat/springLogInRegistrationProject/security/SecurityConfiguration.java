package com.horatiuhorvat.springLogInRegistrationProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.horatiuhorvat.springLogInRegistrationProject.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.headers().frameOptions().disable().and()
				.authorizeRequests(customizer -> {
					customizer.antMatchers("/h2-console/**").permitAll();
					customizer.antMatchers("/register").permitAll();
					customizer.antMatchers("/privateAdmin").hasAuthority("ROLE_ADMIN");
					customizer.anyRequest().authenticated();
				})
				.formLogin(Customizer.withDefaults())
				.authenticationProvider(authProvider())
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		authProvider.setUserDetailsService(userDetailsService());
		
		return authProvider;
	}
}
