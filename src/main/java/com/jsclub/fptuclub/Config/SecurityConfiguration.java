package com.jsclub.fptuclub.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import  com.example.loginlogout.Service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
	@Autowired
	private UserDetailsService userDetailsServicer;
	@Autowired
	CustomUserDetailService customUserDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth ->
						auth.requestMatchers("/registration**").permitAll()
								.requestMatchers("/admin/**").hasAuthority("ADMIN")
								.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.usernameParameter("username")
						.passwordParameter("password")
						.defaultSuccessUrl("/index")
						.permitAll()
				).logout(
						logout -> logout
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.permitAll()
				)
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}
