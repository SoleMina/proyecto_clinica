package com.clinica.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.demo.dtos.LoginRequest;
import com.clinica.demo.models.Cita;
import com.clinica.demo.models.Usuario;
import com.clinica.demo.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Usuario data) {
        try {
            Usuario usuarioCreado = servicio.registrarUsuario(data);
            return ResponseEntity.ok(usuarioCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar usuario: " + e);
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
	
	@GetMapping("/{id}") 
	public ResponseEntity<?> obtenerPorId(@PathVariable int id){
		Usuario usaurio = servicio.obtenerPorId(id);
		if (usaurio == null) {
			return ResponseEntity.
			status(HttpStatus.BAD_REQUEST).body("Usuario no existe"); 
		} 
		return ResponseEntity.ok(usaurio);	
	}
	
	
	 @PostMapping("/login")
	 public ResponseEntity<Usuario> login(@RequestBody LoginRequest payload) {
		 
		 
		 System.out.println("Ingrasando login: ");
	        
	        try {
	        	 Usuario usuario = servicio.login(payload.getCorreo(), payload.getPassword());
	        	 System.out.println("usuario login: " + usuario);
	        	 return ResponseEntity.ok(usuario);
				
			} catch (Exception e) {
				System.out.println("Error en logueo: " + e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	  }
	 
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> eliminarPorId(@PathVariable int id){
			System.out.println("Inside eliminar usuario");
			Usuario usuario = servicio.obtenerPorId(id);
			
			if (usuario == null) {
				return ResponseEntity.
				status(HttpStatus.BAD_REQUEST).body("Usuario no existe"); 
			}
			
			try {
				servicio.eliminarPorId(id);
		        return ResponseEntity.noContent().build();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
					body("Error al eliminar: " + e);
			}
	 }
	 
	 @PutMapping("/{id}")
		public ResponseEntity<?> actualizaUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
		    Usuario usuarioExistente = servicio.obtenerPorId(id);
		    
		    System.out.println("Inside putting controller usaurio");

		    if (usuarioExistente == null) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		    }

		    try {
		    	usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
		    	usuarioExistente.setFono_usua(usuarioActualizado.getFono_usua());
		    	usuarioExistente.setFna_usua(usuarioActualizado.getFna_usua());
		    	
		    	
		        Usuario actualizada = servicio.registrarUsuario(usuarioExistente);
		        return ResponseEntity.ok(actualizada);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar: " + e.getMessage());
		    }
		}
	
	
	
}
