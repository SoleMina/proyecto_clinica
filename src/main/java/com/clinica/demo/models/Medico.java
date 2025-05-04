package com.clinica.demo.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("MEDICO")
public class Medico extends Usuario {
	
	 private int id_espe;
	
	 @ManyToOne( fetch = FetchType.LAZY)
	 @JoinColumn(name="id_espe", insertable= false, updatable = false)
	 private Especialidad especialidad;
	 
	 public Medico() {
		 
	 }

	public Medico(int id_espe, Especialidad especialidad) {
		super();
		this.id_espe = id_espe;
		this.especialidad = especialidad;
	}

	public int getId_espe() {
		return id_espe;
	}

	public void setId_espe(int id_espe) {
		this.id_espe = id_espe;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	 
	 

}
