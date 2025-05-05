package com.clinica.demo.models;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table( name = "tb_usuarios")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "rol_usua", discriminatorType = DiscriminatorType.STRING)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usua;
	
	private String nom_usua;
	private String fono_usua;
	
	@Column(name = "email_usua", length = 45, nullable = false, unique = true)
	private String correo;
	
	private String pass_usua;
	
	@Column (name = "rol_usua", insertable = false, updatable = false)
	private String rol_usua = "PACIENTE";
	
	@Temporal(TemporalType.DATE)
	private Date fna_usua;
	
	@CreationTimestamp
    private LocalDateTime createdAt;
	
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	@Transient
	private Integer id_espe;
	
	public Usuario() {
		
	}

	public Usuario(int id_usua, String nom_usua, String fono_usua, String email_usua, String pass_usua, String rol_usua,
			Date fna_usua, LocalDateTime createdAt, LocalDateTime updatedAt) {

		this.id_usua = id_usua;
		this.nom_usua = nom_usua;
		this.fono_usua = fono_usua;
		this.correo = email_usua;
		this.pass_usua = pass_usua;
		this.rol_usua = rol_usua;
		this.fna_usua = fna_usua;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId_usua() {
		return id_usua;
	}

	public void setId_usua(int id_usua) {
		this.id_usua = id_usua;
	}

	public String getNom_usua() {
		return nom_usua;
	}

	public void setNom_usua(String nom_usua) {
		this.nom_usua = nom_usua;
	}

	public String getFono_usua() {
		return fono_usua;
	}

	public void setFono_usua(String fono_usua) {
		this.fono_usua = fono_usua;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPass_usua() {
		return pass_usua;
	}

	public void setPass_usua(String pass_usua) {
		this.pass_usua = pass_usua;
	}

	public String getRol_usua() {
		return rol_usua;
	}

	public void setRol_usua(String rol_usua) {
		this.rol_usua = rol_usua;
	}

	public Date getFna_usua() {
		return fna_usua;
	}

	public void setFna_usua(Date fna_usua) {
		this.fna_usua = fna_usua;
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

	public Integer getId_espe() {
		return id_espe;
	}

	public void setId_espe(Integer id_espe) {
		this.id_espe = id_espe;
	}
	
	
	
	
}
