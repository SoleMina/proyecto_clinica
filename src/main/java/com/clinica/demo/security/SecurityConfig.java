package com.clinica.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
		
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Deshabilita CSRF para la API de prueba
		.authorizeHttpRequests(auth -> auth
			// endpoints con restricción
			//.requestMatchers(HttpMethod.POST, "/api/citas").hasRole("ADMIN")
			
			// endpoints sin restricción
			.requestMatchers(HttpMethod.GET, "/api/citas").hasAnyRole("ADMINISTRADOR", "MEDICO", "PACIENTE")
			.requestMatchers(HttpMethod.POST, "/api/citas").permitAll()
			.requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/usuarios/registrar").permitAll()
				
			//.requestMatchers("/api/productos/**").hasRole("ADMIN") 
			.anyRequest().authenticated())
		 .formLogin()
		    .and()
		 .logout().permitAll();
		// .httpBasic(); // método de autenticación .formLogin()
		return http.build();
	}

	// Usuario en memoria de ejemplo
	// @Bean
	/*
	 * public UserDetailsService userDetailsService() { UserDetails user =
	 * User.builder() .username("admin") .password(passwordEncoder.encode("1234"))
	 * .roles("ADMIN") .build(); return new InMemoryUserDetailsManager(user); }
	 */
	
	
}