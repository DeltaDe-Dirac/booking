package com.cardlatch.hotel.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(2)
public class PrivilegedUsersConfigurationAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/rooms/couples").authorizeRequests().anyRequest().hasAnyRole("MANAGER", "OWNER").and()
				.httpBasic().authenticationEntryPoint(authPrivilegedEntryPoint());
	}

	@Bean
	public AuthenticationEntryPoint authPrivilegedEntryPoint() {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName("privileged realm");
		return entryPoint;
	}
}