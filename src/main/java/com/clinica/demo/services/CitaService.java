package com.clinica.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.demo.models.Cita;
import com.clinica.demo.repositories.ICitaRepository;

@Service
public class CitaService {

	@Autowired
	private ICitaRepository repoCita;
	
	public List<Cita> listarTodas() {
		return repoCita.findAll();
	}
	
	public Cita listarPorCodigo(int codigo) {
		return repoCita.findById(codigo).orElse(null);
	}
	
	public Cita agregarCita(Cita cita) {
		return repoCita.save(cita);
	}
	
	public Cita obtenerPorId(int id) {
		return repoCita.findById(id).orElse(null);
	}
	
	public void eliminarPorId(int id) {
		repoCita.deleteById(id);
	}
}
