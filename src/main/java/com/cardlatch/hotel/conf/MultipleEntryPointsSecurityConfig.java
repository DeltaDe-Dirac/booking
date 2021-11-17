package com.cardlatch.hotel.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class MultipleEntryPointsSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.createUser(User.withUsername("user").password(encoder().encode("user")).roles("RECEPTION").build());
		manager.createUser(User.withUsername("admin").password(encoder().encode("admin")).roles("MANAGER").build());
		manager.createUser(User.withUsername("owner").password(encoder().encode("owner")).roles("OWNER").build());

		return manager;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
