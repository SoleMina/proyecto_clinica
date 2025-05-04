package com.clinica.demo.models;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table( name = "tb_disponibilidad")
@Data
public class Disponibilidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_dispo;
	
	private int id_med;
	
	private Date fecha_dispo;
	private Time hora_inicio;
	private Time hora_fin;
	
	public Disponibilidad() {
		
	}

	public Disponibilidad(int id_dispo, int id_med, Date fecha_dispo, Time fecha_inicio, Time fecha_fin) {
		super();
		this.id_dispo = id_dispo;
		this.id_med = id_med;
		this.fecha_dispo = fecha_dispo;
		this.hora_inicio = fecha_inicio;
		this.hora_fin = fecha_fin;
	}

	public int getId_dispo() {
		return id_dispo;
	}

	public void setId_dispo(int id_dispo) {
		this.id_dispo = id_dispo;
	}

	public int getId_med() {
		return id_med;
	}

	public void setId_med(int id_med) {
		this.id_med = id_med;
	}

	public Date getFecha_dispo() {
		return fecha_dispo;
	}

	public void setFecha_dispo(Date fecha_dispo) {
		this.fecha_dispo = fecha_dispo;
	}

	public Time getFecha_inicio() {
		return hora_inicio;
	}

	public void setFecha_inicio(Time fecha_inicio) {
		this.hora_inicio = fecha_inicio;
	}

	public Time getFecha_fin() {
		return hora_fin;
	}

	public void setFecha_fin(Time fecha_fin) {
		this.hora_fin = fecha_fin;
	}
	
	
}
