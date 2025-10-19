package com.medrano.eco02_medrano.repository;

import com.medrano.eco02_medrano.entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {
    HistoriaClinica findByPaciente_IdPaciente(Integer idPaciente);
}