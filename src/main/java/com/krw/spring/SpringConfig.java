package com.krw.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.krw.spring.rest.auth.JwtAuthenticationManager;

/**
 * Spring configuration. 
 * @author rwkim
 */
@Configuration
public class SpringConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
	protected static class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
		// config for authority check 
	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		private static final String REGISTER_ENTRY_POINT = "/users";
		private static final String LOGIN_ENTRY_POINT = "/auth";

		@Autowired
		private JwtAuthenticationManager jwtAuthenticationManager;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.authenticationManager(jwtAuthenticationManager);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.authorizeRequests()
					.antMatchers(HttpMethod.POST, REGISTER_ENTRY_POINT).permitAll()
					.antMatchers(HttpMethod.POST, LOGIN_ENTRY_POINT).permitAll()
					.anyRequest().authenticated();
			// @formatter:on
		}

	}

}