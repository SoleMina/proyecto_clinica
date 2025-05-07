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

import com.clinica.demo.models.Cita;
import com.clinica.demo.models.Especialidad;
import com.clinica.demo.services.CitaService;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CitaController {

	@Autowired
	private CitaService servicio;
	
	@GetMapping
	public ResponseEntity<List<Cita>> listarTodos(){
		 System.out.println("Entrando a listarTodos");
		 try {
		        List<Cita> citas = servicio.listarTodas();
		        System.out.println("Cantidad de citas: " + citas.size());

		        if (citas.isEmpty()) {
		            return ResponseEntity.noContent().build();
		        } else {
		            return ResponseEntity.ok(citas);
		        }

		    } catch (Exception e) {
		        System.out.println("Error de citas: " + e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
	}
	
	@PostMapping
	public ResponseEntity<Cita> agregarCita(@RequestBody Cita cita){
		try {
			Cita nuevo = servicio.agregarCita(cita);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPorId(@PathVariable int id){
		System.out.println("Inside eliminar cita");
		Cita cita = servicio.obtenerPorId(id);
		
		if (cita == null) {
			return ResponseEntity.
			status(HttpStatus.BAD_REQUEST).body("Cita no existe"); 
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
	public ResponseEntity<?> actualizarCita(@PathVariable int id, @RequestBody Cita citaActualizada) {
	    Cita citaExistente = servicio.obtenerPorId(id);
	    
	    System.out.println("Inside putting controller cita");

	    if (citaExistente == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
	    }

	    try {
	    	citaExistente.setId_med(citaActualizada.getId_med());
	    	citaExistente.setFecha_cita(citaActualizada.getFecha_cita());
	    	citaExistente.setHora_cita(citaActualizada.getHora_cita());
	    	citaExistente.setEstado_cita(citaActualizada.getEstado_cita());
	        
	        Cita actualizada = servicio.agregarCita(citaExistente);
	        return ResponseEntity.ok(actualizada);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar: " + e.getMessage());
	    }
	}
}
