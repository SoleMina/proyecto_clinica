package com.clinica.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.models.Disponibilidad;

@Repository
public interface IDisponibilidadRepository extends JpaRepository<Disponibilidad, Integer> {

}
