package com.clinica.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.demo.models.Disponibilidad;
import com.clinica.demo.models.Especialidad;
import com.clinica.demo.repositories.IDisponibilidadRepository;

@Service
public class DisponibilidadService {

	@Autowired
	private IDisponibilidadRepository repoDispo;
	
	public List<Disponibilidad> listarTodos() {
		return repoDispo.findAll();
	}
	
	public Disponibilidad agregarDisponibilidad(Disponibilidad disponibilidad) {
		return repoDispo.save(disponibilidad);
	}
}
