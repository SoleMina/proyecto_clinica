package com.clinica.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinica.demo.models.Usuario;
import com.clinica.demo.repositories.IUsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

	    @Autowired
        private  PasswordEncoder passwordEncoder;
	
	    @Autowired
	    private IUsuarioRepository repoUsua;

      CustomUserDetailsService(PasswordEncoder passwordEncoder) {
    	
        this.passwordEncoder = passwordEncoder;
    }

	   
	    
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Usuario usuario = repoUsua.findByCorreo(username)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
	        System.out.println("usuario recibido desde BD: " + usuario);
	        System.out.println("usuario recibido desde BD: " + usuario.getCorreo());
	        // Agrega prefijo ROLE_ manualmente
	        String role = usuario.getRol_usua().toUpperCase();
	        System.out.println("Rol recibido desde BD: " + role);
	        
	        // Add "ROLE_" prefix here
	        String roleWithPrefix = "ROLE_" + usuario.getRol_usua().toUpperCase();
	        System.out.println("roleWithPrefix: " + roleWithPrefix);


	        return User.builder()
	            .username(usuario.getCorreo())
	            .password(usuario.getPass_usua())
	            //.authorities(new SimpleGrantedAuthority(role))
	            .authorities(new SimpleGrantedAuthority(roleWithPrefix))
	            .build();
	    }


}
