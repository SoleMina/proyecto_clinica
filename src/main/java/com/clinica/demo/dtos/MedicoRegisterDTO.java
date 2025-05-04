package com.clinica.demo.dtos;

public class MedicoRegisterDTO extends UsuarioRegisterDTO {
	
	 private int id_espe;

	 public MedicoRegisterDTO() {
	 }

	 public int getId_espe() {
	    return id_espe;
	 }

	 public void setId_espe(int id_espe) {
	    this.id_espe = id_espe;
	 }
	
}
