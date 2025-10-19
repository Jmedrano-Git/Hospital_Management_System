package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.HistoriaClinica;
import com.medrano.eco02_medrano.entity.Paciente;
import com.medrano.eco02_medrano.repository.HistoriaClinicaRepository;
import com.medrano.eco02_medrano.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaRepo;
    private final PacienteRepository pacienteRepo;

    public HistoriaClinicaServiceImpl(HistoriaClinicaRepository historiaRepo, PacienteRepository pacienteRepo) {
        this.historiaRepo = historiaRepo;
        this.pacienteRepo = pacienteRepo;
    }

    @Override
    public HistoriaClinica crearHistoriaParaPaciente(Integer idPaciente) {
        Paciente paciente = pacienteRepo.findById(idPaciente).orElseThrow();
        HistoriaClinica historia = new HistoriaClinica();
        historia.setPaciente(paciente);
        historiaRepo.save(historia);
        return historia;
    }

    @Override
    public HistoriaClinica obtenerPorPaciente(Integer idPaciente) {
        return historiaRepo.findByPaciente_IdPaciente(idPaciente);
    }
}