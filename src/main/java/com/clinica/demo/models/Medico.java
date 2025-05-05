package com.clinica.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("MEDICO")
public class Medico extends Usuario {
	 
	    @Column(name = "id_espe", insertable = true, updatable = true)
	    private Integer id_espe;

	    @ManyToOne
	    @JoinColumn(name = "id_espe", insertable = false, updatable = false)
	    private Especialidad especialidad;

	    public Medico() {}

	    public Medico(int id_espe, Especialidad especialidad) {
	        this.id_espe = id_espe;
	        this.especialidad = especialidad;
	    }

	    public Especialidad getEspecialidad() {
	        return especialidad;
	    }

	    public void setEspecialidad(Especialidad especialidad) {
	        this.especialidad = especialidad;
	        if (especialidad != null) {
	            this.id_espe = especialidad.getId_espe();
	        }
	    }

	    public Integer getId_espe() {
	        return id_espe;
	    }

	    public void setId_espe(Integer id_espe) {
	        this.id_espe = id_espe;
	    }
	 

}
