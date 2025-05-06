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

import com.clinica.demo.models.Especialidad;
import com.clinica.demo.models.Usuario;
import com.clinica.demo.services.EspecialidadService;

@RestController
@RequestMapping("/api/especialidad")
@CrossOrigin(origins = "http://localhost:54001")
public class EspecialidadController {
	
	@Autowired
	private EspecialidadService servicio;
	
	@GetMapping
	public ResponseEntity<List<Especialidad>> listarTodos(){
		List<Especialidad> especialidades = servicio.listarTodos();
		if (especialidades.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(especialidades);
		}
	}
	
	@PostMapping
	public ResponseEntity<Especialidad> agregarEspecialidad(@RequestBody Especialidad especialidad){
		try {
			Especialidad nuevo = servicio.agregarEspecialidad(especialidad);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<?> obtenerPorId(@PathVariable int id){
		Especialidad especialidad = servicio.obtenerPorId(id);
		if (especialidad == null) {
			return ResponseEntity.
			status(HttpStatus.BAD_REQUEST).body("Especialidad no existe"); 
		} 
		return ResponseEntity.ok(especialidad);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPorId(@PathVariable int id){
		System.out.println("Inside eliminar especialidad");
		Especialidad especialidad = servicio.obtenerPorId(id);
		
		if (especialidad == null) {
			return ResponseEntity.
			status(HttpStatus.BAD_REQUEST).body("Especialidad no existe"); 
		} 
		try {
			servicio.eliminarPorId(id);
			return ResponseEntity.status(HttpStatus.CREATED).
				body("Especialidad eliminada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
				body("Error al eliminar: " + e);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarEspecialidad(@PathVariable int id, @RequestBody Especialidad especialidadActualizada) {
	    Especialidad especialidadExistente = servicio.obtenerPorId(id);
	    
	    System.out.println("Inside putting controller especialidad");

	    if (especialidadExistente == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidad no encontrada");
	    }

	    try {
	        especialidadExistente.setNom_espe(especialidadActualizada.getNom_espe());
	        
	        Especialidad actualizada = servicio.agregarEspecialidad(especialidadExistente);
	        return ResponseEntity.ok(actualizada);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar: " + e.getMessage());
	    }
	}

}
