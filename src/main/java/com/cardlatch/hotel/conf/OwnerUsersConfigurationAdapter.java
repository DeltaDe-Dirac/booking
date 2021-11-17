package com.cardlatch.hotel.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@Order(3)
public class OwnerUsersConfigurationAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/status/*", "/topic/sms/**", "/smscallback/**").and().authorizeRequests()
				.anyRequest().hasAnyRole("OWNER").and().httpBasic().authenticationEntryPoint(authOwnerEntryPoint());
	}

	@Bean
	public AuthenticationEntryPoint authOwnerEntryPoint() {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName("owner realm");
		return entryPoint;
	}
}