package com.clinica.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.demo.models.Disponibilidad;
import com.clinica.demo.services.DisponibilidadService;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {

	@Autowired
	private DisponibilidadService servicio;
	
	@GetMapping
	public ResponseEntity<List<Disponibilidad>> listarTodos(){
		List<Disponibilidad> disponibilidades = servicio.listarTodos();
		if (disponibilidades.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(disponibilidades);
		}
	}
	
	@PostMapping
	public ResponseEntity<Disponibilidad> agregarDisponibilidad(@RequestBody Disponibilidad disponibilidad){
		try {
			Disponibilidad nuevo = servicio.agregarDisponibilidad(disponibilidad);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
