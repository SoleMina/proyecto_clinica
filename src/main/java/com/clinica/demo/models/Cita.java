package com.clinica.demo.models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.clinica.demo.utils.EstadoCita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table( name = "tb_citas", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"id_med", "fecha_cita", "hora_cita"})
	})
@Data
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cita;
	
	private int id_usua;
	private int id_med;
	
	@Temporal(TemporalType.DATE)
	private Date fecha_cita;
	
	private Time hora_cita;
	//private String estado_cita;
	
	@CreationTimestamp
    private LocalDateTime createdAt;
	
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	 @Enumerated(EnumType.STRING)
	 @Column(name = "estado_cita", nullable = false)
	 private EstadoCita estado_cita = EstadoCita.PENDIENTE;
	 
	 public Cita() {
		 
	 }

	public Cita(int id_cita, int id_usua, int id_med, Date fecha_cita, Time hora_cita, LocalDateTime createdAt,
			LocalDateTime updatedAt, EstadoCita estado_cita) {
		super();
		this.id_cita = id_cita;
		this.id_usua = id_usua;
		this.id_med = id_med;
		this.fecha_cita = fecha_cita;
		this.hora_cita = hora_cita;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.estado_cita = estado_cita;
	}

	public int getId_cita() {
		return id_cita;
	}

	public void setId_cita(int id_cita) {
		this.id_cita = id_cita;
	}

	public int getId_usua() {
		return id_usua;
	}

	public void setId_usua(int id_usua) {
		this.id_usua = id_usua;
	}

	public int getId_med() {
		return id_med;
	}

	public void setId_med(int id_med) {
		this.id_med = id_med;
	}

	public Date getFecha_cita() {
		return fecha_cita;
	}

	public void setFecha_cita(Date fecha_cita) {
		this.fecha_cita = fecha_cita;
	}

	public Time getHora_cita() {
		return hora_cita;
	}

	public void setHora_cita(Time hora_cita) {
		this.hora_cita = hora_cita;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public EstadoCita getEstado_cita() {
		return estado_cita;
	}

	public void setEstado_cita(EstadoCita estado_cita) {
		this.estado_cita = estado_cita;
	}
	 

}
