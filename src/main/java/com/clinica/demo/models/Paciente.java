package com.clinica.demo.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PACIENTE")
public class Paciente extends Usuario {

}
