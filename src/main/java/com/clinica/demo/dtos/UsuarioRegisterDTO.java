package com.clinica.demo.dtos;

import java.sql.Date;
import java.time.LocalDateTime;

import com.clinica.demo.models.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UsuarioRegisterDTO {

	private int id_usua;
    private String nom_usua;
    private String fono_usua;
    private String email_usua;
    private String pass_usua;
    
    @JsonProperty("rol_usua")
    private String rol_usua;
    private Date fna_usua;
    
    public UsuarioRegisterDTO() {
    	
    }
    public UsuarioRegisterDTO(Usuario usuario) {
        this.id_usua = usuario.getId_usua();
        this.nom_usua = usuario.getNom_usua();
        this.fono_usua = usuario.getFono_usua();
        this.email_usua = usuario.getCorreo();
        this.pass_usua = usuario.getPass_usua();
        this.fna_usua = usuario.getFna_usua();
        this.rol_usua = usuario.getRol_usua();
    }

    
	public UsuarioRegisterDTO(int id_usua, String nom_usua, String fono_usua, String email_usua, String pass_usua, String rol_usua,
			Date fna_usua, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id_usua = id_usua;
		this.nom_usua = nom_usua;
		this.fono_usua = fono_usua;
		this.email_usua = email_usua;
		this.pass_usua = pass_usua;
		this.fna_usua = fna_usua;
		 this.rol_usua = rol_usua;
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

	public String getEmail_usua() {
		return email_usua;
	}

	public void setEmail_usua(String email_usua) {
		this.email_usua = email_usua;
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
	public String getPass_usua() {
		return pass_usua;
	}
	public void setPass_usua(String pass_usua) {
		this.pass_usua = pass_usua;
	}
	
	

    
}
