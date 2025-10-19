package com.medrano.eco02_medrano.repository;

import com.medrano.eco02_medrano.entity.Cita;
import org.springframework.data.jpa.repository  .JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
}