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

import com.clinica.demo.models.Especialidad;
import com.clinica.demo.services.EspecialidadService;

@RestController
@RequestMapping("/api/especialidad")
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

}
