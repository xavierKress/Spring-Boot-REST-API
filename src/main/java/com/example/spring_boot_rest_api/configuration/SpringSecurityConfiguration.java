package com.example.spring_boot_rest_api.configuration;

import com.example.spring_boot_rest_api.security.filter.DemoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringSecurityConfiguration {

	private static final String ADMIN_ROLE = "ADMIN";
	private static final String BOOK_API_ENDPOINT = "/api/v1/books";

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		List<UserDetails> usersList = new ArrayList<>();
		usersList.add(User.builder().username("user")
				.password(encoder.encode("user"))
				.authorities("CAN_VIEW_BOOK_LIST", "CAN_VIEW_BOOK_DETAIL").build());
		usersList.add(User.builder().username("admin")
				.password(encoder.encode("admin"))
				.authorities("CAN_DELETE_BOOK", "CAN_UPDATE_BOOK", "CAN_ADD_BOOK").build());

		return new InMemoryUserDetailsManager(usersList);
	}


		@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests(
						requests -> requests
								.requestMatchers(new AntPathRequestMatcher(BOOK_API_ENDPOINT, HttpMethod.POST.name())).hasRole(ADMIN_ROLE)
								.requestMatchers(new AntPathRequestMatcher(BOOK_API_ENDPOINT, HttpMethod.DELETE.name())).hasRole(ADMIN_ROLE)
								.requestMatchers(new AntPathRequestMatcher(BOOK_API_ENDPOINT, HttpMethod.PATCH.name())).hasRole(ADMIN_ROLE)
								.requestMatchers(new AntPathRequestMatcher(BOOK_API_ENDPOINT, HttpMethod.PUT.name())).hasRole(ADMIN_ROLE)
								.anyRequest().authenticated()
				)
				.httpBasic()
				.and().formLogin().and().rememberMe().key("myHashKey").tokenValiditySeconds(60 * 60 * 24 * 30)
				.and().oauth2Login()
				.and()
				.addFilterBefore(new DemoFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
	}



}
