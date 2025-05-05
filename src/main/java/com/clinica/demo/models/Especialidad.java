package com.clinica.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table( name = "tb_especialidades")
@Data
public class Especialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_espe;
	
	private String nom_espe;
	
	public Especialidad() {
		
	}

	public Especialidad(int id_espe, String nom_espe) {
		super();
		this.id_espe = id_espe;
		this.nom_espe = nom_espe;
	}

	public int getId_espe() {
		return id_espe;
	}

	public void setId_espe(int id_espe) {
		this.id_espe = id_espe;
	}

	public String getNom_espe() {
		return nom_espe;
	}

	public void setNom_espe(String nom_espe) {
		this.nom_espe = nom_espe;
	}
	
	

}
