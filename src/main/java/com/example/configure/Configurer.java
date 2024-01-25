package com.example.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Configurer {
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((requests)->requests
					.requestMatchers("/products/welcome","/products/new","products/user").permitAll() 
					.requestMatchers("/products/**").hasAnyRole("USER","ADMIN")
			) 
			.formLogin((form)->form.loginPage("/login").permitAll())
			.logout((logout)->logout.permitAll())
			.build();
}
	
	
	

}
