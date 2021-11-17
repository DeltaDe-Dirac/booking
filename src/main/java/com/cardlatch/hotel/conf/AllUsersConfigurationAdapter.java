package com.cardlatch.hotel.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(1)
public class AllUsersConfigurationAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/rooms/available", "/guests/families").and().authorizeRequests()
				.anyRequest().hasAnyRole("RECEPTION", "MANAGER", "OWNER").and().httpBasic()
				.authenticationEntryPoint(authAllEntryPoint());
	}

	@Bean
	public AuthenticationEntryPoint authAllEntryPoint() {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName("all realm");
		return entryPoint;
	}
}