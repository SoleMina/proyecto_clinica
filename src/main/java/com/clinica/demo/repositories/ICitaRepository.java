package com.clinica.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.demo.models.Cita;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Integer> {

}
