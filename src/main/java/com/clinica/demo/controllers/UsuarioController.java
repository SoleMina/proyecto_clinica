package com.clinica.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.demo.dtos.UsuarioRegisterDTO;
import com.clinica.demo.models.Cita;
import com.clinica.demo.models.Usuario;
import com.clinica.demo.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:63252")
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;
	
	
	/*
	 * @PostMapping("/registrar") public ResponseEntity<Usuario>
	 * registrarUsuario(@RequestBody Usuario usuario){ try { Usuario nuevo =
	 * servicio.registrarUsuario(usuario); return
	 * ResponseEntity.status(HttpStatus.CREATED).body(nuevo); } catch (Exception e)
	 * { return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); } }
	 */
	
	@PostMapping("/registrar")
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioRegisterDTO dto){
		 try {
		        System.out.println("DTO recibido: " + dto);
		        Usuario newUser = servicio.registrarUsuario(dto);
		        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		    } catch (Exception e) {
		        e.printStackTrace(); // importante para ver qué excepción ocurre
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		    }
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarTodos(){
		 System.out.println("Entrando a listarTodos");
		 try {
		        List<Usuario> usuario = servicio.listarTodos();

		        if (usuario.isEmpty()) {
		            return ResponseEntity.noContent().build();
		        } else {
		            return ResponseEntity.ok(usuario);
		        }

		    } catch (Exception e) {
		        System.out.println("Error de usuarios: " + e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
	}
	
	
}
