package com.clinica.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.demo.models.Especialidad;
import com.clinica.demo.repositories.IEspecialidadRepository;

@Service
public class EspecialidadService {

	@Autowired
	private IEspecialidadRepository repoEspe;
	
	public List<Especialidad> listarTodos() {
		return repoEspe.findAll();
	}
	
	public Especialidad agregarEspecialidad(Especialidad especialidad) {
		return repoEspe.save(especialidad);
	}
}
