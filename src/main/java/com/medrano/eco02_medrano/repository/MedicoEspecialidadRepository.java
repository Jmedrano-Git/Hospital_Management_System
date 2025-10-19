package com.medrano.eco02_medrano.repository;

import com.medrano.eco02_medrano.entity.Medico;
import com.medrano.eco02_medrano.entity.MedicoEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, Integer> {
    List<MedicoEspecialidad> findByMedico(Medico medico);
    void deleteByMedico(Medico medico);
}