package com.clinica.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.demo.models.Cita;
import com.clinica.demo.services.CitaService;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:63252")
public class CitaController {

	@Autowired
	private CitaService servicio;
	
	@GetMapping("/hello")
	public String getHello() {
		return "helloo";
	}
	
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
}
