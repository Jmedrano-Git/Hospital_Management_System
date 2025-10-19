package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.HistoriaClinica;

public interface HistoriaClinicaService {
    HistoriaClinica crearHistoriaParaPaciente(Integer idPaciente);
    HistoriaClinica obtenerPorPaciente(Integer idPaciente);
}