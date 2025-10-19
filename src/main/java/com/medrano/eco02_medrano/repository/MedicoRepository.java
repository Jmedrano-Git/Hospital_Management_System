package com.medrano.eco02_medrano.repository;

import com.medrano.eco02_medrano.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}